/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trabajopracticointegrador;
import java.time.LocalDate;

/**
 *
 * @author fermi
 */
public class Entrenador extends Empleado {
    
    // ATRIBUTOS
    private String matricula;
    
    // CONSTRUCTOR VACIO
    public Entrenador() {
        
    }
    
    // CONSTRUCTOR CON PARAMETROS

    public Entrenador(String matricula, String nombre, String apellido, String DNI, String direccion, String numeroTelefono) {
        super(nombre, apellido, DNI, direccion, numeroTelefono);
        this.matricula = matricula;
    }

    // GETTERS

    public String getMatricula() {
        return matricula;
    }
    
    // SETTERS

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
    
}
