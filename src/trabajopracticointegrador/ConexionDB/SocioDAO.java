/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trabajopracticointegrador.ConexionDB;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;
import trabajopracticointegrador.Socio;
import java.time.LocalDate;

/**
 *
 * @author fermi
 */

// ARCHIVO DESTINADO A CONSULTAS SQL DE SOCIOS (Patron DAO: Data Access Object)
public class SocioDAO {
    
    // ATRIBUTOS
    private Conexion conn;
    private ResultSet rs;
    private Socio socio;
    
    // CONSTRUCTOR
    public SocioDAO() {
        
    }
    
    // CONSULTAR SOCIO POR DNI
    public Socio obtenerSocio(String dni) throws SQLException {
        
            String consulta = "SELECT * FROM Personas p"
                    + " INNER JOIN Socios s ON p.PersonaId = s.PersonaId"
                    + " WHERE DNI = ?";
            
            Socio socio = null;
            
            try (Connection connection = conn.getConexion()) {
                
                PreparedStatement stm = connection.prepareStatement(consulta);
                
                stm.setString(1, dni);
                
                try (ResultSet rs = stm.executeQuery()) {
                
                    if (rs.next()) {
                        socio = new Socio();
                        socio.setNombre(rs.getString("Nombre"));
                        socio.setApellido(rs.getString("Apellido"));
                        socio.setDNI(rs.getString("DNI"));
                        socio.setDireccion(rs.getString("Direccion"));
                        socio.setNumeroTelefono(rs.getString("Telefono"));
                        socio.setFechaNacimiento(rs.getObject("FechaNacimiento", LocalDate.class));
                        socio.setFechaAlta(rs.getObject("FechaAlta", LocalDate.class));
                        socio.setActivo(rs.getBoolean("Activo"));
                    }
                
                }
            }
            return socio;
    }
}