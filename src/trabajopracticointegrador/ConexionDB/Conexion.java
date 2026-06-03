/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trabajopracticointegrador.ConexionDB;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;

/**
 *
 * @author fermi
 */
public class Conexion {
    
    // ATRIBUTOS
    Connection conector = null;
    
    private String servidor;
    private String database;
    private String user;
    private String password;
    private String URL;
    private Connection conn;
    private ResultSet rs;
    private Statement stm;
    
    // CONSTRUCTOR
    public Conexion() {
        
    }
    
    public Conexion(String servidor, String database, String user, String password) {
        
        try {
            this.servidor = servidor;
            this.database = database;
            this.user = user;
            this.password = password;
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            this.URL = "jdbc:mysql://"+servidor+":3307/"+database;
            conn = DriverManager.getConnection(this.URL, user, password);            
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
    
    // METODOS
    
    public Connection getConexion() {
        return conn;
    }
    
    public Connection cerrarConexion() {
        try {
            conn.close();
        } catch (SQLException e) {
            System.out.println(e);
        } 
        conn = null;
        return conn;
    }
    
    public ResultSet consultar (String consulta) throws SQLException {
        stm = conn.createStatement();
        rs = stm.executeQuery(consulta);
        return rs;
    }
    
    public void insertar(String sql) {
        try {
            stm = conn.createStatement();
            stm.executeUpdate(sql);
            System.out.println(sql);
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    
}
