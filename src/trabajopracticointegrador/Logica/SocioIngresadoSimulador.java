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
public class SocioIngresadoSimulador extends Thread {
   
    // ATRIBUTOS 
    private ControlAccesoInterfaz listener;
    private Socio socio;
    
    // CONSTRUCTOR
    
    public SocioIngresadoSimulador(ControlAccesoInterfaz listener, Socio socio) {
        this.listener = listener;
        this.socio = socio;
    }
    
    @Override
    public void run() {
        
        try {
            listener.ingresarSocio(socio);
            
            // ESPERA UNA CANTIDAD ALEATORIA DE TIEMPO, ENTRE 45 Y 90 MINUTOS (SEGUNDOS)
            int segundos = (int) (Math.random() * (90 - 45 + 1) + 45);
            sleep(segundos * 1000);
            
            listener.retirarSocio(socio);
        } catch (InterruptedException ex) {
            System.getLogger(SocioIngresadoSimulador.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        
    }
}
