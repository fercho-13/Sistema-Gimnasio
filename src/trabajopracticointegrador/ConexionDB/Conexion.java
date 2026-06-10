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
    
    private String URL;
    private Connection conn;
    private ResultSet rs;
    private Statement stm;
    
    // CONSTRUCTOR
    public Conexion() {
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            this.URL = "jdbc:mysql://localhost:3307/sistema_gimnasio";
            conn = DriverManager.getConnection(this.URL, "root", "");            
        } catch(SQLException | ClassNotFoundException e) {
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
