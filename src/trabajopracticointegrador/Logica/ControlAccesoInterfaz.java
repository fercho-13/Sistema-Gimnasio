/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package trabajopracticointegrador.Logica;
import java.util.Vector;
import trabajopracticointegrador.Ingreso;
import trabajopracticointegrador.Socio;

/**
 *
 * @author fermi
 */
public interface ControlAccesoInterfaz {
    
    // METODOS
    
    // SOCIO INGRESA CON EXITO
    Ingreso accesoConcedido(Socio socio);
    
    // SOCIO NO PUEDE INGRESAR POR ACCESO DENEGADO
    void accesoDenegado(String mensajeError);
    
    // SOCIO PUEDE INGRESAR PERO DEBE ABONAR LA SUSCRIPCION
    Ingreso accesoParcial(Socio socio);
    
    // CARGA LOS DATOS DEL SOCIO EN LA LISTA DE SOCIOS DENTRO DEL GIMNASIO
    void ingresarSocio(SocioEntrenandoThread hilo);
    
    // RETIRA AL SOCIO DE LA LISTA DE SOCIOS DENTRO DEL GIMNASIO
    void retirarSocio(SocioEntrenandoThread hilo);
    
    // OBTENER LA LISTA DE HILOS
    Vector<SocioEntrenandoThread> getHilosActivos();
}
