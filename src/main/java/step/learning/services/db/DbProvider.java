package step.learning.services.db;

import com.google.inject.Singleton;


public interface DbProvider {
    java.sql.Connection getConnection() ;
}
