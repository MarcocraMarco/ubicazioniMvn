package it.alfasoft.cespidiMvn.dao;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public abstract class DaoSuper<T,I> implements Dao<T,I> {
    public Connection getConnection(){
        Connection con=null;
    try(
            InputStream input = getClass().getClassLoader().getResourceAsStream("configuration.properties")){
            Properties prop = new Properties();
            prop.load(input);
            String dbdriver = prop.getProperty("driver");
            String dburl = prop.getProperty("url");
            String dbuser = prop.getProperty("userName");
            String dbpwd = prop.getProperty("password");
            Class.forName(dbdriver);
            con= DriverManager.getConnection(dburl,dbuser,dbpwd);
        }catch(Exception e) {
            e.printStackTrace();
            System.out.println("It does not return a connection");
        }
    return con;
    }
    public PreparedStatement getPrepStatement(Connection c, String s)throws SQLException {
        PreparedStatement pr=null;
        try{
            pr=	c.prepareStatement(s, Statement.RETURN_GENERATED_KEYS);
        }catch(Exception e) {
            e.printStackTrace();
            System.out.println("It does not return a prepstatement");
        }
        return pr;
    }

}
