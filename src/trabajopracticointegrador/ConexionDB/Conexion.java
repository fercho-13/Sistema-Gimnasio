/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trabajopracticointegrador.ConexionDB;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author fermi
 */
public class Conexion {
    
    // ATRIBUTOS
    private String URL;
    private Connection conn;
    
    // CONSTRUCTOR
    public Conexion() {
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            this.URL = "jdbc:mysql://localhost:3307/sistema_gimnasio";
        } catch(ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
    
    // METODOS
    
    public Connection getConexion() throws SQLException {
        return DriverManager.getConnection(URL, "root", "");
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
}
