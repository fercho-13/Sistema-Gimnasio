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
    private LocalDate fechaMatriculaVencimiento;
    
    // CONSTRUCTOR VACIO
    public Entrenador() {
        
    }
    
    // CONSTRUCTOR CON PARAMETROS

    public Entrenador(String matricula, LocalDate fechaMatriculaVencimiento, Cargo cargo, String nombre, String apellido, int DNI, String direccion, String numeroTelefono) {
        super(cargo, nombre, apellido, DNI, direccion, numeroTelefono);
        this.matricula = matricula;
        this.fechaMatriculaVencimiento = fechaMatriculaVencimiento;
    }

    // GETTERS

    public String getMatricula() {
        return matricula;
    }

    public LocalDate getFechaMatriculaVencimiento() {
        return fechaMatriculaVencimiento;
    }
    
    // SETTERS

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public void setFechaMatriculaVencimiento(LocalDate fechaMatriculaVencimiento) {
        this.fechaMatriculaVencimiento = fechaMatriculaVencimiento;
    }
    
    
}
