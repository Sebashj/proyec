package Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
        Connection cx = null;
        public Connection conectar() {
        try {
         //   Class.forName("org.sqlite.JDBC");
       // cx =DriverManager.getConnection("jdbc:sqlite:BaseDeDatos.db");
        Class.forName("com.mysql.cj.jdbc.Driver");
        cx =DriverManager.getConnection("jdbc:mysql://localhost:3306/basededatos","root","");
        System.out.println("Conexion existosa ");
        } catch (ClassNotFoundException | SQLException e) {
           e.printStackTrace();
        } 
           return cx;
        }
public static void  main (String[] args) {
	Conexion cx=new Conexion();
	cx.conectar();
}
}
        
        
    
