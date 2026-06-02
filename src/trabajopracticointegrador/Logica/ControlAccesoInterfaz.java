/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package trabajopracticointegrador.Logica;
import trabajopracticointegrador.Socio;

/**
 *
 * @author fermi
 */
public interface ControlAccesoInterfaz {
    
    // METODOS
    
    // SOCIO INGRESA CON EXITO
    void accesoConcedido(Socio socio);
    
    // SOCIO NO PUEDE INGRESAR POR ACCESO DENEGADO
    void accesoDenegado(String mensajeError);
    
    // SOCIO PUEDE INGRESAR PERO DEBE ABONAR LA SUSCRIPCION
    void accesoParcial(Socio socio);
}
