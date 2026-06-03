/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trabajopracticointegrador.ConexionDB;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.SQLException;
import trabajopracticointegrador.Socio;

/**
 *
 * @author fermi
 */

// ARCHIVO DESTINADO A CONSULTAS SQL DE SOCIOS (Patron DAO: Data Access Object)
public class SocioDAO {
    
    // ATRIBUTOS
    private Conexion conn;
    private Connection connection;
    private ResultSet rs;
    private Statement stm;
    private Socio socio;
    
    // CONSTRUCTOR
    public SocioDAO() {
        
    }
    
    // CONSULTAR SOCIO POR DNI
    public Socio obtenerSocio(String dni) throws SQLException {
        
            conn = new Conexion("", "", "root", "");
            connection = conn.getConexion();

            stm = connection.createStatement();

            rs = stm.executeQuery("SELECT * FROM Socios WHERE DNI = " + dni);
            
            if (rs.next()) {
                
                socio = new Socio();
                socio.setNombre(rs.getString("nombre"));
                
                // armar base de datos para que retorne directamente el socio
                return socio;
            }
            
            return null;
    }
}