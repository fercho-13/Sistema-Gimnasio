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
    
    public SuscripcionCupo(String descripcion, TipoPlan tipoPlan, double valor, int cuposRestantes) {
        super(descripcion, valor, tipoPlan);
        this.cuposRestantes = cuposRestantes;
    }
}
