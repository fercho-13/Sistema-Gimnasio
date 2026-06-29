/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trabajopracticointegrador.ConexionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author fermi
 */
public class RolDAO {
    
    private Conexion conn;
    private Connection connection;
    private PreparedStatement stm;
    private ResultSet rs;
    private ArrayList<String> roles;
    private String rol;
    private Integer id;
    
    public RolDAO(Conexion conn) {
        this.conn = conn;
    }
    
    public int obtenerId_rol(String rol) {
        String consulta = "SELECT * FROM Roles WHERE Descripcion = ?";
        
        id = null;
        
        try (Connection connection = conn.getConexion()) {
            PreparedStatement stm = connection.prepareStatement(consulta);
            
            stm.setString(1, rol);
            
            ResultSet rs = stm.executeQuery();
            
            if (rs.next()) {                
                id = rs.getInt("RolId");
            }
        } catch (SQLException ex) {
            System.getLogger(RolDAO.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        
        return id;
    }
    
    public ArrayList<String> obtenerRoles() {
        String consulta = "SELECT * FROM Roles";
        
        roles = new ArrayList<String>();
        
        try (Connection connection = conn.getConexion()) {
            
            PreparedStatement stm = connection.prepareStatement(consulta);
            
            ResultSet rs = stm.executeQuery();
            
            while (rs.next()) {
                rol = rs.getString("Descripcion");
                
                roles.add(rol);
            }
        } catch (SQLException ex) {
            System.getLogger(RolDAO.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        
        return roles;
    }
}
