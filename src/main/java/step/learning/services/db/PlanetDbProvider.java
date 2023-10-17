package step.learning.services.db;

import com.google.inject.Singleton;

import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.google.gson.*;

@Singleton
public class PlanetDbProvider implements DbProvider {
    private Connection connection;
    @Override
    public Connection getConnection() {

        if(connection == null)
        {

            JsonObject config;
            try(InputStreamReader reader = new InputStreamReader( this.getClass().getClassLoader().getResourceAsStream("db_config.json") ))
            {
                config = JsonParser.parseReader(reader).getAsJsonObject();
            } catch (IOException ex)
            {
                throw new RuntimeException(ex);
            }
            catch (NullPointerException ex)
            {
                throw new RuntimeException("resouce not found");
            }
            JsonObject planetScale = config
                    .get("DataProviders")
                    .getAsJsonObject()
                    .get("PlanetScale")
                    .getAsJsonObject() ;
            try {
                // реєстрація драйвера для протоколу jdbc:mysql://  (два варіанти)
                // Class.forName("com.mysql.cj.jdbc.Driver");
                DriverManager.registerDriver( new com.mysql.cj.jdbc.Driver() ) ;
                // підключаємось
                this.connection = DriverManager.getConnection(
                        planetScale.get("url").getAsString(),
                        planetScale.get("user").getAsString(),
                        planetScale.get("password").getAsString() ) ;
            }
            catch( SQLException ex ) {
                throw new RuntimeException( ex ) ;
            }
        }
        return connection;
    }
}
/*
Д.З. Відновити паролі від БД (взяти зі старих проєктів)
Налаштувати файл конфігурації таким чином, щоб він не передавався до репозиторію
Додати залежність <!-- https://mvnrepository.com/artifact/com.mysql/mysql-connector-j -->
Завершити із завантаженням файлів-аватарок, додати скріншот папки з ними.
 */