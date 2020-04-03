
import com.mysql.cj.jdbc.MysqlDataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DBConnection {
    private static Connection connection = null;
    private  String dbname;
    private String server;
    private String username;
    private String password;
    private int port;

    public DBConnection(){

    }


    public DBConnection(String dbname, String server, String username, String password, int port){
        this.dbname = dbname;
        this.server = server;
        this.username = username;
        this.password = password;
        this.port = port;
    }

    public Connection getConnection() throws SQLException { //Singleton
        if (connection == null){
            MysqlDataSource mysqlDataSource = new MysqlDataSource();
            mysqlDataSource.setDatabaseName (dbname);
            mysqlDataSource.setServerName (server);
            mysqlDataSource.setPortNumber (port);
            mysqlDataSource.setUser (username);
            mysqlDataSource.setPassword (password);
            mysqlDataSource.setServerTimezone ("Europe/Rome");
            connection = mysqlDataSource.getConnection ();
        }
        return connection;
    }
}
