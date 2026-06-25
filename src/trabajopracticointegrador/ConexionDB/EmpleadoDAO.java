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
                    entrenador.setCargo(rs.getString("r.Descripcion"));
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
                    empleado.setCargo(rs.getString("r.Descripcion"));
                    
                    empleados.add(empleado);
                }                
            }
        } catch (SQLException ex) {
            System.getLogger(SocioDAO.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        
        return empleados;
    }
}
