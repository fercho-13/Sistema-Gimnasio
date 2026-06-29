/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trabajopracticointegrador.Logica;

import trabajopracticointegrador.ConexionDB.Conexion;
import trabajopracticointegrador.ConexionDB.SocioDAO;
import trabajopracticointegrador.ConexionDB.SuscripcionDAO;
import trabajopracticointegrador.Socio;

/**
 *
 * @author fermi
 */
public class IngresoRandomThread extends Thread {
    
    private SocioDAO socio_dao;
    private SuscripcionDAO suscripcion_dao;
    private Socio socioRandom;
    private SocioEntrenandoThread hilo;
    private ControlAccesoInterfaz listener;
    private boolean ejecutando = true;

    public IngresoRandomThread(Conexion conexion, ControlAccesoInterfaz listener) {
        suscripcion_dao = new SuscripcionDAO(conexion);
        socio_dao = new SocioDAO(conexion, suscripcion_dao);
        this.listener = listener;
    }
    
    public void run() {
        while (ejecutando) { 
            
            try {
                ingresarSocioRandom();
                
                sleep(2500);
            } catch (InterruptedException ex) {
                System.getLogger(IngresoRandomThread.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                ejecutando = false;
            }
        }
    }
    
    private void ingresarSocioRandom() {
        socioRandom = socio_dao.obtenerSocioRandom();
        if (socioRandom != null) {
            if (socioRandom.getSuscripcion() != null) {
                if (socioRandom.getSuscripcion().isActivo()) {
                    listener.accesoConcedido(socioRandom);
                } else {
                    listener.accesoParcial(socioRandom);
                }
                
                hilo = new SocioEntrenandoThread(socioRandom, listener);
                hilo.start();
                
            } else {
                // EN CASO DE QUE EL SOCIO NUNCA HAYA REGISTRADO UNA SUSCRIPCION
            }
        } else {
            listener.accesoDenegado("DNI no registrado");
        }
    }
    
    public void detener() {
        this.ejecutando = false;
    } 
}
