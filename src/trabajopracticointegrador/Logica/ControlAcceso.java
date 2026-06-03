/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trabajopracticointegrador.Logica;
import trabajopracticointegrador.ConexionDB.SocioDAO;
import trabajopracticointegrador.Logica.ControlAccesoInterfaz;
import trabajopracticointegrador.Socio;
import java.sql.SQLException;

/**
 *
 * @author fermi
 */
public class ControlAcceso {
    
    // ATRIBUTOS
    private final ControlAccesoInterfaz listener;
    private final SocioDAO repositorio;
    
    // CONSTRUCTOR 
    public ControlAcceso(ControlAccesoInterfaz listener) {
        this.listener = listener;
        this.repositorio = new SocioDAO();
    }
    
    // METODO
    public void procesarIngreso(String dni) {
            
        try {
            // da error ya que todavia no arme la base de datos
            Socio socio = repositorio.obtenerSocio(dni);

            if (socio != null) {
                if (socio.getSuscripcion() != null && socio.getSuscripcion().isActivo()) {
                    listener.accesoConcedido(socio);
                } else {
                    listener.accesoParcial(socio);
                }
            } else {
                listener.accesoDenegado("DNI no registrado");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            listener.accesoDenegado("Error de Sistema: no se pudo conectar con la Base de Datos");
        }
    }
    
    
    
}
