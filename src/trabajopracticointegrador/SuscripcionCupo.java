/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package trabajopracticointegrador;

/**
 *
 * @author fermi
 */
public class SuscripcionCupo extends Suscripcion {
    
    // ATRIBUTOS
    private int cuposRestantes;
    
    // CONSTRUCTOR VACIO
    
    public SuscripcionCupo() {
    }
    
    // CONSTRUCTOR CON PARAMETROS
    
    public SuscripcionCupo(double valor, int cuposRestantes) {
        super("Suscripcion por cupos", valor);
        this.cuposRestantes = cuposRestantes;
    }
}
