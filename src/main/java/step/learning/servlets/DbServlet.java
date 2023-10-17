package step.learning.servlets;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import step.learning.services.db.DbProvider;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.sql.Statement;

@Singleton
public class DbServlet extends HttpServlet {
    private final DbProvider dbProvider ;
    private final String dbPrefix ;

    @Inject
    public DbServlet(DbProvider dbProvider, @Named("db-prefix") String dbPrefix) {
        this.dbProvider = dbProvider;
        this.dbPrefix = dbPrefix;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String connectionStatus ;
        try {
            dbProvider.getConnection() ;
            connectionStatus = "Connection OK" ;
        }
        catch( RuntimeException ex ) {
            connectionStatus = "Connection error: " + ex.getMessage() ;
        }
        req.setAttribute( "connectionStatus", connectionStatus ) ;

        req.setAttribute( "page-body", "db.jsp" ) ;
        req.getRequestDispatcher( "WEB-INF/_layout.jsp" ).forward( req, resp ) ;
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Реакція на кнопку "create" - створюємо таблицю БД "замовлення дзвінків"
        String status ;
        String message ;
        String sql = "CREATE TABLE " + dbPrefix + "call_me (" +
                "id      BIGINT       PRIMARY KEY ," +
                "name    VARCHAR(64)  NULL," +
                "phone   CHAR(13)     NOT NULL COMMENT '+38 098 765 43 21'," +
                "moment  DATETIME     DEFAULT CURRENT_TIMESTAMP" +
                ") ENGINE = InnoDB DEFAULT CHARSET = UTF8";
        try( Statement statement = dbProvider.getConnection().createStatement() ) {
            statement.executeUpdate( sql ) ;
            status = "OK";
            message = "Table created" ;
        }
        catch( SQLException ex ) {
            status = "error";
            message = ex.getMessage() ;
        }
        JsonObject result = new JsonObject() ;
        result.addProperty("status", status ) ;
        result.addProperty("message", message ) ;
        resp.getWriter().print( result.toString() ) ;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // insert - додати дані до БД. Дані передаються як JSON у тілі запиту
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        byte[] buffer = new byte[4096];
        int len ;
        String json ;
        JsonObject result = new JsonObject() ;
        try( InputStream body = req.getInputStream() ) {
            while( ( len = body.read( buffer ) ) > 0 ) {
                bytes.write( buffer, 0, len ) ;
            }
            json = bytes.toString( StandardCharsets.UTF_8.name() ) ;
            JsonObject data = JsonParser.parseString( json ).getAsJsonObject() ;
            result.addProperty("name", data.get("name").getAsString() ) ;
            result.addProperty("phone", data.get("phone").getAsString() ) ;
        }
        catch( IOException ex ) {
            result.addProperty("message", ex.getMessage() ) ;
        }
        resp.getWriter().print( result.toString() ) ;
    }
}
/*
Д.З. Реалізувати перевірку даних, що приходять у запиті (json), на наявність
полів "name" та "phone". У разі відсутності повертати status:"validation error"
з повідомленням про поле, якого не вистачає.
перевірити поля на порожні значення, так само видати помилки валідації
* перевірити поля на регулярні вирази, розширити перелік помилок валідації
 */