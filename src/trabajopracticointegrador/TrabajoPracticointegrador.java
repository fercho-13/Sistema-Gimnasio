/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package trabajopracticointegrador;
import trabajopracticointegrador.Logica.ControlAcceso;
import trabajopracticointegrador.Logica.ControlAccesoInterfaz;
import trabajopracticointegrador.Logica.probar_consola;
import trabajopracticointegrador.ConexionDB.Conexion;


/**
 *
 * @author fermi
 */
public class TrabajoPracticointegrador {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        probar_consola listener = new probar_consola();
        
        Conexion conexion = new Conexion();
        ControlAcceso control = new ControlAcceso(listener, conexion);
        
        control.procesarIngreso("45234112");
    }
    
}
