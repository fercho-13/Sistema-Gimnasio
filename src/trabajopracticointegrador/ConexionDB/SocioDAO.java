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
import java.util.ArrayList;

/**
 *
 * @author fermi
 */

// ARCHIVO DESTINADO A CONSULTAS SQL DE SOCIOS (Patron DAO: Data Access Object)
public class SocioDAO {
    
    // ATRIBUTOS
    private Conexion conn;
    private Connection connection;
    private PreparedStatement stm;
    private ResultSet rs;
    private Socio socio;
    private SuscripcionDAO suscripcion_dao;
    private Suscripcion suscripcion;
    private ArrayList<Socio> socios;
    
    // CONSTRUCTOR
    public SocioDAO(Conexion conn, SuscripcionDAO suscripcion_dao) {
        this.conn = conn;
        this.suscripcion_dao = suscripcion_dao;
    }
    
    // CONSULTAR SOCIO POR DNI
    public Socio obtenerSocio(String dni) {
        
        try {
            String consulta = "SELECT * FROM Personas p"
                    + " INNER JOIN Socios s ON p.PersonaId = s.PersonaId"
                    + " WHERE DNI = ?";
            
            socio = null;
            
            connection = conn.getConexion();
            
            stm = connection.prepareStatement(consulta);
            
            stm.setString(1, dni);
            
            rs = stm.executeQuery();
            
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
        } catch (SQLException ex) {
            System.getLogger(SocioDAO.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        
        return socio;
    }
    
    public ArrayList<Socio> obtenerSocios() {
        
        try {
            String consulta = "SELECT * FROM Personas p "
                    + "INNER JOIN Socios s ON p.PersonaId = s.PersonaId";
            
            socios = new ArrayList<Socio>();
            
            connection = conn.getConexion();
            
            stm = connection.prepareStatement(consulta);
            
            rs = stm.executeQuery();
            
            while(rs.next()) {
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
                
                socios.add(socio);
            }
        } catch (SQLException ex) {
            System.getLogger(SocioDAO.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        
        return socios;
    }
    
    public boolean insertarSocio(Socio socio) {
        String consulta = "INSERT INTO Personas (Nombre, Apellido, FechaNacimiento, Direccion, DNI, Telefono) VALUES "
                + "(?, ?, ?, ?, ?, ?); ";
        
        try (Connection connection = conn.getConexion()) {
            
            stm = connection.prepareStatement(consulta);
            
            stm.setString(1, socio.getNombre());
            stm.setString(2, socio.getApellido());
            stm.setObject(3, socio.getFechaNacimiento());
            stm.setString(4, socio.getDireccion());
            stm.setString(5, socio.getDNI());
            stm.setString(6, socio.getNumeroTelefono());
            
            int filasAfectadas = stm.executeUpdate();
            
            if (filasAfectadas == 0) {
                return false; // HUBO ERROR AL REGISTRAR
            }
            
            int id = 0;
            
            // OBTENGO EL ULTIMO ID REGISTRADO
            consulta = "SELECT PersonaId FROM Personas ORDER BY PersonaId DESC LIMIT 1";
            
            stm = connection.prepareStatement(consulta);
            
            rs = stm.executeQuery();
            
            if (rs.next()) {
                id = rs.getInt("PersonaId");
            }
            
            if (id != 0) { // ID OBTENIDO DE FORMA EXITOSA
                consulta = "INSERT INTO Socios (PersonaId, FechaAlta) VALUES (?, ?)";
                
                socio.setId_Socio(id);
                
                stm = connection.prepareStatement(consulta);

                stm.setInt(1, id);
                stm.setObject(2, LocalDate.now());
                
                filasAfectadas = stm.executeUpdate();
                
                if (filasAfectadas == 0) {
                    return false;
                }
                
                // OBTENGO EL ULTIMO ID REGISTRADO
                consulta = "SELECT SocioId FROM Socios ORDER BY SocioId DESC LIMIT 1";

                stm = connection.prepareStatement(consulta);

                rs = stm.executeQuery();

                if (rs.next()) {
                    id = rs.getInt("PersonaId");
                }
                
                if (id != 0) {
                    suscripcion_dao = new SuscripcionDAO(conn);
                    
                    // CONTINUAR CON LA INSERCION DE SUSCRIPCION
                } else {
                    
                }
            } else { 
                
            }
        } catch (SQLException ex) {
            System.getLogger(SocioDAO.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        
        return true;
    }
}