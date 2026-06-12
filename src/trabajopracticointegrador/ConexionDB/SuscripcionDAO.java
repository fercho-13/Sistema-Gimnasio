/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trabajopracticointegrador.ConexionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import trabajopracticointegrador.Socio;
import trabajopracticointegrador.Suscripcion;
import trabajopracticointegrador.SuscripcionLibre;
import trabajopracticointegrador.SuscripcionCupo;
import java.lang.Integer;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

/**
 *
 * @author fermi
 */
public class SuscripcionDAO {
    
    // ATRIBUTOS
    private Conexion conn;
    private Connection connection;
    private ResultSet rs;
    private Suscripcion suscripcion;
    private ArrayList<Suscripcion> suscripciones;
    
    // CONSTRUCTOR
    public SuscripcionDAO(Conexion conn) {
        this.conn = conn;
    }
    
    public SuscripcionDAO(Connection connection) {
        this.connection = connection;
    }
    
    // CONSULTAR SOCIO POR DNI
    public Suscripcion obtenerSuscripcionActiva(int id_socio) throws SQLException {
        
            String consulta = "SELECT * FROM Suscripciones s"
                    + " INNER JOIN Planes p ON s.PlanId = p.PlanId"
                    + " WHERE s.SocioId = ? AND s.Activo = true";
            
            Suscripcion suscripcion = null; // SI DEVUELVE NULL, NO SE PUDIERON ACCEDER A LOS DATOS Y DARA ERROR
            
            try (Connection connection = conn.getConexion()) {
                
                PreparedStatement stm = connection.prepareStatement(consulta);
                
                stm.setInt(1, id_socio);
                
                try (ResultSet rs = stm.executeQuery()) {
                
                    if (rs.next()) {
                        
                        Integer cuposRestantes = null;
                        int cuposLectura = rs.getInt("s.CuposRestantes");
                        
                        // COMO JDBC NO PUEDE DEVOLVER VALORES NULL, VERIFICA CUAL FUE EL ULTIMO DATO LEIDO DE LA BD
                        if (!rs.wasNull()) {
                            // SI TENIA UN VALOR NO NULO, ENTONCES ES DEL TIPO SUSCRIPCION POR CUPOS
                            cuposRestantes = cuposLectura;
                        }
                        
                        if (cuposRestantes == null) {
                            SuscripcionLibre libre = new SuscripcionLibre();
                            libre.setFechaInicio(rs.getObject("s.FechaInicio", LocalDate.class));
                            libre.setFechaFin(rs.getObject("s.FechaFin", LocalDate.class));
                            // CALCULA LOS DIAS RESTANTES EN BASE A LA DIFERENCIA ENTRE FECHA ACTUAL Y FECHA FIN DE LA SUSCRIPCION
                            libre.setDiasRestantes((int) ChronoUnit.DAYS.between(LocalDate.now(), libre.getFechaFin()));
                            
                            suscripcion = libre;
                        } else {
                            SuscripcionCupo cupo = new SuscripcionCupo();
                            cupo.setFechaInicio(rs.getObject("s.FechaInicio", LocalDate.class));
                            cupo.setFechaFin(rs.getObject("s.FechaFin", LocalDate.class));
                            cupo.setCuposRestantes(cuposRestantes);
                            
                            suscripcion = cupo;
                        }
                        suscripcion.setDescripcion(rs.getString("p.Descripcion"));
                        suscripcion.setId_Suscripcion((rs.getInt("s.SuscripcionId")));
                        suscripcion.setId_plan(rs.getInt("p.PlanId"));
                        suscripcion.setActivo(true);
                    }
                return suscripcion;
                }
            }
    }
    
    // CONSULTAR TODOS LOS PLANES
    
    public ArrayList<Suscripcion> obtenerPlanes() {
        String consulta = "SELECT * FROM Planes WHERE Activo = true";
        
        suscripciones = new ArrayList<Suscripcion>();
        
        try (Connection connection = conn.getConexion()) {
            
            PreparedStatement stm = connection.prepareStatement(consulta);
            
            rs = stm.executeQuery();
            
            while (rs.next()) {
                suscripcion = new Suscripcion();
                
                suscripcion.setId_plan(rs.getInt("PlanId"));
                suscripcion.setDescripcion(rs.getString("Descripcion"));
                suscripcion.setValor(rs.getDouble("Precio"));
                suscripcion.setCuposTotal(rs.getInt("CantidadCupos"));
                suscripcion.setDiasTotal(rs.getInt("CantidadDias"));
                
                suscripciones.add(suscripcion);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        return suscripciones;
    }
    
    // AGREGAR METODO INSERTAR SUSCRIPCION
    
    public boolean insertarSuscripcionLibre(SuscripcionLibre suscripcion, int id_socio) throws SQLException {
        String consulta = "INSERT INTO Suscripciones (SocioId, PlanId, FechaInicio, FechaFin) VALUES "
                + "(?, ?, ?, ?)";
        
        try (Connection connection = conn.getConexion()) {
            
            PreparedStatement stm = connection.prepareStatement(consulta);
            
            stm.setInt(1, id_socio);
            stm.setInt(2, suscripcion.getId_plan());
            stm.setObject(3, suscripcion.getFechaInicio());
            stm.setObject(4, suscripcion.getFechaFin());
            
            int filasAfectadas = stm.executeUpdate();
            
            if (filasAfectadas > 0) {
                return true;
            }
        }
        
        return false;
    }
    
    public boolean insertarSuscripcionCupo(SuscripcionCupo suscripcion, int id_socio) throws SQLException {
        String consulta = "INSERT INTO Suscripciones (SocioId, PlanId, FechaInicio, FechaFin, CuposRestantes) VALUES "
                + "(?, ?, ?, ?, ?)";
        
        try (Connection connection = conn.getConexion()) {
            
            PreparedStatement stm = connection.prepareStatement(consulta);
            
            stm.setInt(1, id_socio);
            stm.setInt(2, suscripcion.getId_plan());
            stm.setObject(3, suscripcion.getFechaInicio());
            stm.setObject(4, suscripcion.getFechaFin());
            stm.setInt(5, suscripcion.getCuposRestantes());
            
            int filasAfectadas = stm.executeUpdate();
            
            if (filasAfectadas > 0) {
                return true;
            }
        }
        
        return false;
    }
    
    public boolean tieneSuscripcionActiva(int id_socio) throws SQLException{
        String consulta = "SELECT * FROM Suscripciones WHERE SocioId = ? AND Activo = true";
        
        try (Connection connection = conn.getConexion()) {
            PreparedStatement stm = connection.prepareStatement(consulta);
            
            stm.setInt(1, id_socio);
            
            ResultSet rs = stm.executeQuery();
            
            if (rs.next()) {
                return true;
            }
            
            return false;
        }
    }
}
