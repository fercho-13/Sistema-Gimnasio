/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trabajopracticointegrador.ConexionDB;
import trabajopracticointegrador.Suscripcion;
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
    private SuscripcionDAO suscripcion_dao;
    private Suscripcion suscripcion;
    
    // CONSTRUCTOR
    public SocioDAO(Conexion conn, SuscripcionDAO suscripcion_dao) {
        this.conn = conn;
        this.suscripcion_dao = suscripcion_dao;
    }
    
    // CONSULTAR SOCIO POR DNI
    public Socio obtenerSocio(String dni) throws SQLException {
        
            String consulta = "SELECT * FROM Personas p"
                    + " INNER JOIN Socios s ON p.PersonaId = s.PersonaId"
                    + " WHERE DNI = ?";
            
            socio = null;
            
            try (Connection connection = conn.getConexion()) {
                
                PreparedStatement stm = connection.prepareStatement(consulta);
                
                stm.setString(1, dni);
                
                try (ResultSet rs = stm.executeQuery()) {
                
                    if (rs.next()) {
                        socio = new Socio();
                        socio.setId_Socio(rs.getInt("SocioId"));
                        socio.setNombre(rs.getString("Nombre"));
                        socio.setApellido(rs.getString("Apellido"));
                        socio.setDNI(rs.getString("DNI"));
                        socio.setDireccion(rs.getString("Direccion"));
                        socio.setNumeroTelefono(rs.getString("Telefono"));
                        socio.setFechaNacimiento(rs.getObject("FechaNacimiento", LocalDate.class));
                        socio.setFechaAlta(rs.getObject("FechaAlta", LocalDate.class));
                        socio.setActivo(rs.getBoolean("Activo"));
                        
                        Suscripcion suscripcion = suscripcion_dao.obtenerSuscripcionActiva(socio.getId_Socio());
                        socio.setSuscripcion(suscripcion);
                    }
                
                }
            }
            return socio;
    }
}