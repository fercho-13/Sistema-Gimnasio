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
import trabajopracticointegrador.Suscripcion;
import trabajopracticointegrador.SuscripcionLibre;
import trabajopracticointegrador.SuscripcionCupo;
import java.lang.Integer;
import java.time.temporal.ChronoUnit;

/**
 *
 * @author fermi
 */
public class SuscripcionDAO {
    
    // ATRIBUTOS
    private Conexion conn;
    private ResultSet rs;
    
    // CONSTRUCTOR
    public SuscripcionDAO(Conexion conn) {
        this.conn = conn;
    }
    
    // CONSULTAR SOCIO POR DNI
    public Suscripcion obtenerSuscripcionActiva(int id_socio) throws SQLException {
        
            String consulta = "SELECT * FROM Suscripciones"
                    + " WHERE SocioId = ? AND Activo = true";
            
            Suscripcion suscripcion = null; // SI DEVUELVE NULL, NO SE PUDIERON ACCEDER A LOS DATOS Y DARA ERROR
            
            try (Connection connection = conn.getConexion()) {
                
                PreparedStatement stm = connection.prepareStatement(consulta);
                
                stm.setInt(1, id_socio);
                
                try (ResultSet rs = stm.executeQuery()) {
                
                    if (rs.next()) {
                        
                        Integer cuposRestantes = null;
                        int cuposLectura = rs.getInt("CuposRestantes");
                        
                        // COMO JDBC NO PUEDE DEVOLVER VALORES NULL, VERIFICA CUAL FUE EL ULTIMO DATO LEIDO DE LA BD
                        if (!rs.wasNull()) {
                            // SI TENIA UN VALOR NO NULO, ENTONCES ES DEL TIPO SUSCRIPCION POR CUPOS
                            cuposRestantes = cuposLectura;
                        }
                        
                        if (cuposRestantes == null) {
                            SuscripcionLibre libre = new SuscripcionLibre();
                            libre.setFechaInicio(rs.getObject("FechaInicio", LocalDate.class));
                            libre.setFechaFin(rs.getObject("FechaFin", LocalDate.class));
                            // CALCULA LOS DIAS RESTANTES EN BASE A LA DIFERENCIA ENTRE FECHA ACTUAL Y FECHA FIN DE LA SUSCRIPCION
                            libre.setDiasRestantes((int) ChronoUnit.DAYS.between(LocalDate.now(), libre.getFechaFin()));
                            
                            suscripcion = libre;
                        } else {
                            SuscripcionCupo cupo = new SuscripcionCupo();
                            cupo.setFechaInicio(rs.getObject("FechaInicio", LocalDate.class));
                            cupo.setFechaFin(rs.getObject("FechaFin", LocalDate.class));
                            cupo.setCuposRestantes(cuposRestantes);
                            
                            suscripcion = cupo;
                        }
                        
                        suscripcion.setId_Suscripcion((rs.getInt("SuscripcionId")));
                        suscripcion.setId_plan(rs.getInt("PlanId"));
                        suscripcion.setActivo(true);
                    } else {
                        
                    }
                return suscripcion;
                }
            }
    }
    
    // AGREGAR METODO PARA MAPEAR TODAS LAS CONSULTAS
}
