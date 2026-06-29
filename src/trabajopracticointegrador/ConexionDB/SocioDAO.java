/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trabajopracticointegrador.ConexionDB;
import java.sql.Statement;
import trabajopracticointegrador.Suscripcion;
import trabajopracticointegrador.SuscripcionCupo;
import trabajopracticointegrador.SuscripcionLibre;
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
    
    public boolean insertarSocio(Socio socio) throws SQLException {
        String consulta = "INSERT INTO Personas (Nombre, Apellido, FechaNacimiento, Direccion, DNI, Telefono) VALUES "
                + "(?, ?, ?, ?, ?, ?); ";
        
        try (Connection connection = conn.getConexion()) {
            
            connection.setAutoCommit(false);
            
            PreparedStatement stmPersona = connection.prepareStatement(consulta);
            
            stmPersona.setString(1, socio.getNombre());
            stmPersona.setString(2, socio.getApellido());
            stmPersona.setObject(3, socio.getFechaNacimiento());
            stmPersona.setString(4, socio.getDireccion());
            stmPersona.setString(5, socio.getDNI());
            stmPersona.setString(6, socio.getNumeroTelefono());
            
            int filasAfectadas = stmPersona.executeUpdate();
            
            if (filasAfectadas == 0) {
                connection.rollback();
                return false; // HUBO ERROR AL REGISTRAR
            }
            
            int idPersona = 0;
            
            consulta = "SELECT * FROM Personas ORDER BY PersonaId DESC LIMIT 1";
            
            // OBTENGO ID REGISTRADO DE PERSONA
            PreparedStatement stmId = connection.prepareStatement(consulta);
            
            ResultSet rs = stmId.executeQuery();
            
            if (rs.next()) {
                idPersona = rs.getInt("PersonaId");
            }
            
            if (idPersona == 0) {
                connection.rollback();
                return false;
            }
            
            // ID OBTENIDO DE FORMA EXITOSA
            consulta = "INSERT INTO Socios (PersonaId, FechaAlta) VALUES (?, ?)";
            
            PreparedStatement stmSocio = connection.prepareStatement(consulta, Statement.RETURN_GENERATED_KEYS);
            
            stmSocio.setInt(1, idPersona);
            stmSocio.setObject(2, LocalDate.now());
            
            filasAfectadas = stmSocio.executeUpdate();
            
            if (filasAfectadas == 0) {
                connection.rollback();;
                return false;
            }
            
            int idSocio = 0;
            
            consulta = "SELECT * FROM Socios ORDER BY SocioId DESC LIMIT 1";
            
            // OBTENGO ID REGISTRADO DE PERSONA
            stmId = connection.prepareStatement(consulta);
            
            rs = stmId.executeQuery();
            
            if (rs.next()) {
                idSocio = rs.getInt("SocioId");
            }
            
            if (idSocio == 0) {
                connection.rollback();
                return false;
            }
            
            suscripcion_dao = new SuscripcionDAO(conn);
            
            boolean exito = false;
            if (socio.getSuscripcion() instanceof SuscripcionCupo) {
                exito = suscripcion_dao.insertarSuscripcionCupo((SuscripcionCupo) socio.getSuscripcion(), idSocio);
            }  else if (socio.getSuscripcion() instanceof SuscripcionLibre) {
                exito = suscripcion_dao.insertarSuscripcionLibre((SuscripcionLibre) socio.getSuscripcion(), idSocio);
            }
            
            if(!exito) {
                connection.rollback();
                return false;
            }
            
            connection.commit();
            return true;
        } catch (SQLException ex) {
            System.getLogger(SocioDAO.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            return false;
        }
    }
    
    // CONSULTAR SOCIO RANDOM PARA LE HILO
    public Socio obtenerSocioRandom() {
        
        try {
            String consulta = "SELECT * FROM Personas p"
                    + " INNER JOIN Socios s ON p.PersonaId = s.PersonaId"
                    + " ORDER BY RAND() LIMIT 1";
            
            socio = null;
            
            connection = conn.getConexion();
            
            stm = connection.prepareStatement(consulta);
                        
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
}