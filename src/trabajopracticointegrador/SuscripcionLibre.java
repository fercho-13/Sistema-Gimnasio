/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trabajopracticointegrador;

/**
 *
 * @author fermi
 */
public class SuscripcionLibre extends Suscripcion {
    
    // ATRIBUTOS
    private String nombre;
    private int diasRestantes;
    
    // CONSTRUCTOR VACIO
    
    public SuscripcionLibre() {
    }
    
    // CONSTRUCTOR CON PARAMETROS
    
    public SuscripcionLibre(double valor, int diasRestantes) {
        super("Suscripcion libre", valor);
        this.diasRestantes = diasRestantes;
    }
    
    // GETTER Y SETTER
    
    public String getNombre() {
        return nombre;
    }
}
