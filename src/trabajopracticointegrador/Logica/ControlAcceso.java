/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trabajopracticointegrador.Logica;
import trabajopracticointegrador.ConexionDB.SocioDAO;
import trabajopracticointegrador.Socio;
import trabajopracticointegrador.ConexionDB.SuscripcionDAO;
import trabajopracticointegrador.ConexionDB.Conexion;
import java.sql.SQLException;
import trabajopracticointegrador.ConexionDB.IngresoDAO;
import trabajopracticointegrador.Ingreso;

/**
 *
 * @author fermi
 */
public class ControlAcceso {
    
    // ATRIBUTOS
    private final ControlAccesoInterfaz listener;
    private final SocioDAO socio_dao;
    private final SuscripcionDAO suscripcion_dao;
    private final IngresoDAO ingreso_dao;
    private Ingreso ingreso;
    private SocioEntrenandoThread hilo;
    
    // CONSTRUCTOR 
    public ControlAcceso(ControlAccesoInterfaz listener, Conexion conn) {
        this.listener = listener;
        this.suscripcion_dao = new SuscripcionDAO(conn);
        this.socio_dao = new SocioDAO(conn, suscripcion_dao);
        this.ingreso_dao = new IngresoDAO(conn);
    }
    
    // METODO
    public void procesarIngreso(String dni) {
            
        Socio socio = socio_dao.obtenerSocio(dni);
        if (socio != null) {
            if (socio.getSuscripcion() != null) {
                if (socio.getSuscripcion().isActivo()) {
                    ingreso = listener.accesoConcedido(socio);
                } else {
                    ingreso = listener.accesoParcial(socio);
                }
                
                ingreso_dao.insertarIngreso(ingreso);
                
                hilo = new SocioEntrenandoThread(socio, listener);
                hilo.start();
                
            } else {
                // EN CASO DE QUE EL SOCIO NUNCA HAYA REGISTRADO UNA SUSCRIPCION
            }
        } else {
            listener.accesoDenegado("DNI no registrado");
        }
    }
    
    
    
}
