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

/**
 *
 * @author fermi
 */
public class ControlAcceso {
    
    // ATRIBUTOS
    private final ControlAccesoInterfaz listener;
    private final SocioDAO socio_dao;
    private final SuscripcionDAO suscripcion_dao;
    private SocioIngresadoSimulador hilo;
    
    // CONSTRUCTOR 
    public ControlAcceso(ControlAccesoInterfaz listener, Conexion conn) {
        this.listener = listener;
        this.suscripcion_dao = new SuscripcionDAO(conn);
        this.socio_dao = new SocioDAO(conn, suscripcion_dao);
    }
    
    // METODO
    public void procesarIngreso(String dni) {
            
        Socio socio = socio_dao.obtenerSocio(dni);
        if (socio != null) {
            if (socio.getSuscripcion() != null) {
                if (socio.getSuscripcion().isActivo()) {
                    listener.accesoConcedido(socio);
                } else {
                    listener.accesoParcial(socio);
                }
                
                hilo = new SocioIngresadoSimulador(listener, socio);
                hilo.start();
                
            } else {
                // EN CASO DE QUE EL SOCIO NUNCA HAYA REGISTRADO UNA SUSCRIPCION
            }
        } else {
            listener.accesoDenegado("DNI no registrado");
        }
    }
    
    
    
}
