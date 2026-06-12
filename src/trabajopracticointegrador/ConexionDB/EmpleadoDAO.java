/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trabajopracticointegrador.ConexionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import trabajopracticointegrador.Empleado;
import trabajopracticointegrador.Entrenador;
import java.time.LocalDate;
import java.sql.SQLException;

/**
 *
 * @author fermi
 */

// ARCHIVO DESTINADO A LAS CONSULTAS SQL DE EMPLEADOS (Patron DAO: Data Access Object)
public class EmpleadoDAO {
    
    // ATRIBUTOS
    private Conexion conn;
    private Connection connection;
    private PreparedStatement stm;
    private ResultSet rs;
    private Empleado empleado;
    private Entrenador entrenador;
    private ArrayList<Empleado> empleados;
    
    // CONSTRUCTOR
    public EmpleadoDAO(Conexion conn) {
        this.conn = conn;
    }
    
    
    
    public ArrayList<Empleado> obtenerEmpleados() {
        
        try {
            String consulta = "SELECT * FROM Personas p "
                    + "INNER JOIN Empleados e ON p.PersonaId = e.PersonaId "
                    + "INNER JOIN Roles r ON e.RolId = r.RolId";
            
            empleados = new ArrayList<Empleado>();
            
            connection = conn.getConexion();
            
            stm = connection.prepareStatement(consulta);
            
            rs = stm.executeQuery();
            
            while(rs.next()) {
                String matricula = rs.getString("e.Matricula");
                
                if (matricula != null) { // MATRICULA NO ES NULO -> TIENE MATRICULA DE ENTRENADOR, PERTENECE A
                                         // LA CLASE ENTRENADOR
                    entrenador = new Entrenador();
                    
                    entrenador.setNombre(rs.getString("p.Nombre"));
                    entrenador.setApellido(rs.getString("p.Apellido"));
                    entrenador.setDNI(rs.getString("p.DNI"));
                    entrenador.setDireccion(rs.getString("p.Direccion"));
                    entrenador.setNumeroTelefono(rs.getString("p.Telefono"));
                    entrenador.setFechaNacimiento(rs.getObject("p.FechaNacimiento", LocalDate.class));
                    entrenador.setFechaIngreso(rs.getObject("e.FechaIngreso", LocalDate.class));
                    entrenador.setFechaEgreso(rs.getObject("e.FechaEgreso", LocalDate.class));
                    entrenador.setActivo(rs.getBoolean("e.Activo"));
                    entrenador.setRol(rs.getString("r.Descripcion"));
                    entrenador.setMatricula(matricula);
                    
                    empleados.add(entrenador);
                } else { // MATRICULA ES NULO -> NO ES ENTRENADOR, PERTENECE A LA CLASE EMPLEADO
                    empleado = new Empleado();
                
                    empleado.setNombre(rs.getString("p.Nombre"));
                    empleado.setApellido(rs.getString("p.Apellido"));
                    empleado.setDNI(rs.getString("p.DNI"));
                    empleado.setDireccion(rs.getString("p.Direccion"));
                    empleado.setNumeroTelefono(rs.getString("p.Telefono"));
                    empleado.setFechaNacimiento(rs.getObject("p.FechaNacimiento", LocalDate.class));
                    empleado.setFechaIngreso(rs.getObject("e.FechaIngreso", LocalDate.class));
                    empleado.setFechaEgreso(rs.getObject("e.FechaEgreso", LocalDate.class));
                    empleado.setActivo(rs.getBoolean("e.Activo"));
                    empleado.setRol(rs.getString("r.Descripcion"));
                    
                    empleados.add(empleado);
                }                
            }
        } catch (SQLException ex) {
            System.getLogger(SocioDAO.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        
        return empleados;
    }
    
    public boolean insertarEmpleado(Empleado empleado) {
        String consulta = "INSERT INTO Personas (Nombre, Apellido, FechaNacimiento, Direccion, DNI, Telefono) VALUES "
                + "(?, ?, ?, ?, ?, ?); ";
        
        try (Connection connection = conn.getConexion()) {
            
            connection.setAutoCommit(false);
            
            PreparedStatement stmPersona = connection.prepareStatement(consulta);
            
            stmPersona.setString(1, empleado.getNombre());
            stmPersona.setString(2, empleado.getApellido());
            stmPersona.setObject(3, empleado.getFechaNacimiento());
            stmPersona.setString(4, empleado.getDireccion());
            stmPersona.setString(5, empleado.getDNI());
            stmPersona.setString(6, empleado.getNumeroTelefono());
            
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
            consulta = "INSERT INTO Empleados (PersonaId, RolId, FechaIngreso) VALUES (?, ?, ?)";
            
            PreparedStatement stmEmpleado = connection.prepareStatement(consulta);
            
            stmEmpleado.setInt(1, idPersona);
            stmEmpleado.setInt(2, empleado.getId_rol());
            stmEmpleado.setObject(3, LocalDate.now());
            
            filasAfectadas = stmEmpleado.executeUpdate();
            
            if (filasAfectadas == 0) {
                connection.rollback();
                return false;
            }
            
            connection.commit();
        } catch (SQLException ex) {
            System.getLogger(EmpleadoDAO.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        return true;
    }
    
    public boolean insertarEntrenador(Entrenador entrenador) {
        String consulta = "INSERT INTO Personas (Nombre, Apellido, FechaNacimiento, Direccion, DNI, Telefono) VALUES "
                + "(?, ?, ?, ?, ?, ?); ";
        
        try (Connection connection = conn.getConexion()) {
            
            connection.setAutoCommit(false);
            
            PreparedStatement stmPersona = connection.prepareStatement(consulta);
            
            stmPersona.setString(1, entrenador.getNombre());
            stmPersona.setString(2, entrenador.getApellido());
            stmPersona.setObject(3, entrenador.getFechaNacimiento());
            stmPersona.setString(4, entrenador.getDireccion());
            stmPersona.setString(5, entrenador.getDNI());
            stmPersona.setString(6, entrenador.getNumeroTelefono());
            
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
            consulta = "INSERT INTO Empleados (PersonaId, RolId, FechaIngreso, Matricula) VALUES (?, ?, ?)";
            
            PreparedStatement stmEntrenador = connection.prepareStatement(consulta);
            
            stmEntrenador.setInt(1, idPersona);
            stmEntrenador.setInt(2, entrenador.getId_rol());
            stmEntrenador.setObject(3, LocalDate.now());
            stmEntrenador.setString(4, entrenador.getMatricula());
            
            filasAfectadas = stmEntrenador.executeUpdate();
            
            if (filasAfectadas == 0) {
                connection.rollback();
                return false;
            }
            
            connection.commit();
        } catch (SQLException ex) { 
            System.getLogger(EmpleadoDAO.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        
        return true;
    }
    
}