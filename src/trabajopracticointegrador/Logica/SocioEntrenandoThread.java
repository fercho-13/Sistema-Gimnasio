/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trabajopracticointegrador.Logica;
import trabajopracticointegrador.Socio;
import java.util.Random;
import trabajopracticointegrador.ConexionDB.SocioDAO;
import trabajopracticointegrador.Logica.ControlAccesoInterfaz;

/**
 *
 * @author fermi
 */
public class SocioEntrenandoThread extends Thread {
    
    private final Random random;
    private final ControlAccesoInterfaz listener;
    private Socio socio;
    
    // CONSTRUCTOR 
    
    public SocioEntrenandoThread(Socio socio, ControlAccesoInterfaz listener) {
        this.listener = listener;
        this.random = new Random();
        this.socio = socio;
    } 
    
    @Override
    public void run () {
        
        try {
            sleep(1000);
            
            listener.ingresarSocio(this);

            // ESPERA UNA CANTIDAD ALEATORIA DE TIEMPO, ENTRE 45 Y 90 MINUTOS (SEGUNDOS)
            int segundos = random.nextInt(45, 91);
            sleep(segundos * 1000);
            
            listener.retirarSocio(this);
        
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println(e.getMessage());
        }
    }
    
    // METODO PARA DETENER HILO DE FORMA MANUAL
    public void detener() {
        interrupt();
    }
    
    public Socio getSocio() {
        return socio;
    }
}
