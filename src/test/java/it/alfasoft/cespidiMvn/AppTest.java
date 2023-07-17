package it.alfasoft.cespidiMvn;


import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.Properties;

/**
 * Unit test for simple App.
 */
public class AppTest 

{
    protected static String creaStringa(){

        return String.valueOf(new Date().getTime());
    }

    protected static String init() {
        String dB=null;
        try( InputStream input = AppTest.class.getClassLoader().getResourceAsStream("configuration.properties")){
            Properties prop = new Properties();
            prop.load(input);
            String dbdriver = prop.getProperty("driver");
            String dburl = prop.getProperty("url");
            String dbuser = prop.getProperty("userName");
            String dbpwd = prop.getProperty("password");
            Class.forName(dbdriver);

            try(Connection con= DriverManager.getConnection(dburl,dbuser,dbpwd )){
                dB="Test"+AppTest.creaStringa();
                String createQuery="create database "+dB;
                PreparedStatement st=con.prepareStatement(createQuery);
                st.executeUpdate();
                String createTable="create table "+dB+".libreria (isbn int not null,titolo varchar(100),autore varchar(100),editore varchar(100),img varchar(100),primary key (isbn));";
                PreparedStatement stTable=con.prepareStatement(createTable);
                stTable.executeUpdate();
                String addRow1="insert into "+dB+".libreria (isbn,titolo,autore,editore,img) values(1,'titolo1','autore1','editore1','percorso1')";
                String addRow2="insert into "+dB+".libreria (isbn,titolo,autore,editore,img) values(2,'titolo2','autore2','editore2','percorso2')";
                PreparedStatement stRaw=con.prepareStatement(addRow1);
                stRaw.executeUpdate();
                PreparedStatement stRaww=con.prepareStatement(addRow2);
                stRaww.executeUpdate();
                String createProcedure="CREATE DEFINER=`root`@`localhost` PROCEDURE '"+dB+".trovaLibri`(in testo varchar(50)) BEGIN select * from libreria"+
                        " where titolo like testo or autore like testo or editore like testo;END";

            }catch(SQLException e){
                e.printStackTrace();
                System.out.println("Errore di creazione database");
            }
        }catch(IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return dB;
    }
    protected static void deleteDatbase(String d){
        String dropQuery="drop database "+d;
        try( InputStream input = AppTest.class.getClassLoader().getResourceAsStream("configuration.properties")){
            Properties prop = new Properties();
            prop.load(input);
            String dbdriver = prop.getProperty("driver");
            String dburl = prop.getProperty("url");
            String dbuser = prop.getProperty("userName");
            String dbpwd = prop.getProperty("password");
            try(Connection con= DriverManager.getConnection(dburl,dbuser,dbpwd );){
                PreparedStatement st=con.prepareStatement(dropQuery);
                st.executeUpdate();
            }catch(SQLException e){
                e.printStackTrace();
                System.out.println("Errore di delete database");
            }
        }catch(IOException e) {
            e.printStackTrace();
        }
    }
}
