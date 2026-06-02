/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trabajopracticointegrador.Logica;
import trabajopracticointegrador.Socio;
import java.util.Random;
import trabajopracticointegrador.Logica.ControlAccesoInterfaz;

/**
 *
 * @author fermi
 */
public class ControlAccesoThread extends Thread {
    
    private final Socio[] socios;
    private boolean activo;
    private final Random random;
    private final ControlAccesoInterfaz listener;
    
    // CONSTRUCTOR 
    
    public ControlAccesoThread(Socio[] socios, ControlAccesoInterfaz listener) {
        this.socios = socios;
        this.activo = true;
        this.listener = listener;
        this.random = new Random();
    } 
    
    @Override
    public void run () {
        while (activo) {
            try {
                int duracionSleep = 3000;
                Thread.sleep(duracionSleep);
                
                if (!activo) {
                    break;
                }
                
                if(random.nextInt(9) == 0 || socios.length == 0) {
                    listener.accesoDenegado("DNI no registrado");
                } else {
                    int indiceRandom = random.nextInt(socios.length);
                    Socio socioRandom = socios[indiceRandom];
                    if (socioRandom.getSuscripcion() != null && socioRandom.getSuscripcion().isActivo()) {
                        listener.accesoConcedido(socioRandom);
                    } else {
                        listener.accesoDenegado(socioRandom.getNombre() + " " + socioRandom.getApellido() + " debe abonar la suscripcion");
                    }
                }
            } catch (InterruptedException e) {
                System.out.println("Simulador interrumpido");
                activo = false;
            }
        }
    }
    
    // METODO PARA DETENER HILO DE FORMA MANUAL
    public void detener() {
        this.activo = false;
        this.interrupt();
    }
}
