package work.utakatanet.addressmanager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class DatabaseManager {
    //Singletonパターンを使用する
    private static DatabaseManager Instance = null;
    private String host;

    private String port;
    private String dataBaseName;

    private String user;

    private String password;
    private String url;
    private Connection connection;
    private Statement statement;

    private DatabaseManager() {
        ;
    }

    private Gson gson;

    private String[] tableNames={"AddressDivision"};
    public static DatabaseManager getInstance() {
        if (Instance == null) {
            Instance = new DatabaseManager();
        }
        return Instance;
    }

    public void Setup() throws SQLException {
        connection = DriverManager.getConnection(url, user, password);
        statement = connection.createStatement();
        gson = new Gson();
        if (!isDatabaseExists(dataBaseName)) {
            executeUpdate(String.format("CREATE DATABASE %s",dataBaseName));
        }
        executeUpdate("USE "+dataBaseName);
        if(!isTableExists(tableNames[0])){
            executeUpdate(String.format(
                    "CREATE TABLE %s (id INT AUTO_INCREMENT UNIQUE, date DATE," +
                    " ExecutorPlayerUUID TEXT, StringPositionList TEXT,prefecture TEXT,town TEXT,Chome TEXT,PRIMARY KEY (id))"
                    ,tableNames[0]));
        }
        execute(String.format("select * from %s.%s",dataBaseName,tableNames[0]));
    }
    public void setAddress(){

    }
    public ResultSet executeQuery(String sqlSentence) throws SQLException {
        return statement.executeQuery(sqlSentence);
    }

    public void executeUpdate(String sqlSentence) throws SQLException {
        statement.executeUpdate(sqlSentence);
    }
    public void execute(String sqlSentence) throws SQLException {
        statement.execute(sqlSentence);
    }


    public boolean isDatabaseExists(String dataBaseName) throws SQLException {
        String sql = String.format("SHOW DATABASES LIKE '%s'", dataBaseName);
        try (ResultSet resultSet = executeQuery(sql);
        ) {
            return resultSet.next();
        }
    }
    public boolean isTableExists(String tableName) throws SQLException {
        String sql = String.format("SHOW TABLES LIKE '%s'", tableName);
        try (ResultSet resultSet = executeQuery(sql);
        ) {
            return resultSet.next();
        }
    }

    public String arrayListToString(ArrayList<ArrayList<String>> targetArrayList) {

        ArrayList<ArrayList<String>> arrList = targetArrayList;
        String arrStr = gson.toJson(arrList);
        return arrStr;
    }

    public ArrayList<ArrayList<String>> StringToArrayList(String targetString) {
        Type listType = new TypeToken<ArrayList<ArrayList<String>>>() {
        }.getType();
        ArrayList<ArrayList<String>> arrList = gson.fromJson(targetString, listType);
        return arrList;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getDataBaseName() {
        return dataBaseName;
    }

    public void setDataBaseName(String dataBaseName) {
        this.dataBaseName = dataBaseName;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
