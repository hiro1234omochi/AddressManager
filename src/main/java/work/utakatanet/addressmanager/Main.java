package work.utakatanet.addressmanager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.mysql.jdbc.Driver;

public class Main {
    public static void main(String[] args) {
        try {
            DatabaseManager databaseManager=DatabaseManager.getInstance();
            // Plugin startup logic
            String host = "localhost";

            String port = "3306";
            String dataBaseName = "AddressManager";

            String user = "user";

            String password = "password";

            String url = String.format("jdbc:mysql://%s:%s", host, port);

            databaseManager.setHost(host);

            databaseManager.setPort(port);
            databaseManager.setUser(user);
            databaseManager.setPassword(password);
            databaseManager.setDataBaseName(dataBaseName);
            databaseManager.setUrl(url);

            //String url =  String.format("jdbc:mysql://%s:%s/%s",host,port,dataBaseName);

            ;
            databaseManager.Setup();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
