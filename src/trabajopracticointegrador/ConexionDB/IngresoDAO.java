/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trabajopracticointegrador.ConexionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;
import trabajopracticointegrador.Ingreso;

/**
 *
 * @author fermi
 */
public class IngresoDAO {
    private Conexion conn;
    private Ingreso ingreso;
    
    public IngresoDAO(Conexion conn) {
        this.conn = conn;
    }
    
    // REGISTRAR INGRESO
    
    public boolean insertarIngreso(Ingreso ingreso) {
        String consulta = "INSERT INTO Ingresos (SocioId, FechaHora, Acceso) VALUES "
                + "(?, ?, ?)";
        
        try(Connection connection = conn.getConexion()) {
            PreparedStatement stm = connection.prepareStatement(consulta);
            
            stm.setInt(1, ingreso.getId_socio());
            stm.setObject(2, ingreso.getFecha_hora());
            stm.setString(3, ingreso.getAcceso());
            
            int filasAfectadas = stm.executeUpdate();
            
            if (filasAfectadas > 0) {
                return true;
            }
        } catch (SQLException ex) {
            System.getLogger(IngresoDAO.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        
        return false;
    }
    
    // OBTENER TODOS LOS INGRESOS DE UN SOCIO
    public ArrayList<Ingreso> obtenerIngresosSocio(int id_socio) {
        String consulta = "SELECT * FROM Ingresos WHERE SocioId = ?";
        
        ArrayList<Ingreso> ingresos = new ArrayList<Ingreso>();
        Ingreso ingreso;
        
        try (Connection connection = conn.getConexion()) {
            PreparedStatement stm = connection.prepareStatement(consulta);
            
            stm.setInt(1, id_socio);
            
            ResultSet rs = stm.executeQuery();
            
            while (rs.next()) {
                ingreso = new Ingreso();
                
                ingreso.setId_ingreso(rs.getInt("IngresoId"));
                ingreso.setFecha_hora(rs.getObject("FechaHora", LocalDateTime.class));
                ingreso.setAcceso(rs.getString("Acceso"));
                
                ingresos.add(ingreso);
            }
        } catch (SQLException ex) {
            System.getLogger(IngresoDAO.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        
        return ingresos;
    }
    
    // OBTENER TODOS LOS INGRESOS
    public ArrayList<Ingreso> obtenerIngresos(){
        String consulta = "SELECT * FROM Ingresos";
        
        ArrayList<Ingreso> ingresos = new ArrayList<Ingreso>();
        Ingreso ingreso;
        
        try (Connection connection = conn.getConexion()) {
            PreparedStatement stm = connection.prepareStatement(consulta);
                        
            ResultSet rs = stm.executeQuery();
            
            while (rs.next()) {
                ingreso = new Ingreso();
                
                ingreso.setId_ingreso(rs.getInt("IngresoId"));
                ingreso.setId_socio(rs.getInt("SocioId"));
                ingreso.setFecha_hora(rs.getObject("FechaHora", LocalDateTime.class));
                ingreso.setAcceso(rs.getString("Acceso"));
                
                ingresos.add(ingreso);
            }
        } catch (SQLException ex) {
            System.getLogger(IngresoDAO.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        
        return ingresos;
    }
}
