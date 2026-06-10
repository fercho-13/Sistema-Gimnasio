/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trabajopracticointegrador.Logica;
import trabajopracticointegrador.Socio;

/**
 *
 * @author fermi
 */
public class probar_consola implements ControlAccesoInterfaz {
    
    // CONSTRUCTOR
    public probar_consola() {
        
    }
    
    @Override
    public void accesoConcedido(Socio socio) {
        System.out.println("Acceso del socio " + socio.getNombre() + " concedido");
    }
    
    @Override
    public void accesoParcial(Socio socio) {
        System.out.println("Socio " + socio.getNombre() + " debe abonar la suscripcion");
    }
    
    @Override 
    public void accesoDenegado(String mensajeError) {
        System.out.println("No se pudo encontrar el dni");
    }
}
