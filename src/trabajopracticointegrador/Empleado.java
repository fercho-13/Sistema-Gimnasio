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
public class Empleado extends Persona {
    
    // ATRIBUTOS
    private LocalDate fechaIngreso;
    private LocalDate fechaEgreso;
    private boolean activo;
    private String rol;
    private int id_rol;
    
    // CONSTRUCTOR VACIO
    public Empleado() {
        
    }
    
    // CONSTRUCTOR SIN PARAMETROS (SIN CARGO)

    public Empleado(String nombre, String apellido, String DNI, String direccion, String numeroTelefono) {
        super(nombre, apellido, DNI, direccion, numeroTelefono);
        this.fechaIngreso = LocalDate.now();
        this.fechaEgreso = null;
        this.activo = true;
    }
    
    
    // GETTERS
    
    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }

    public LocalDate getFechaEgreso() {
        return fechaEgreso;
    } 

    public String getRol() {
        return rol;
    } 

    public int getId_rol() {
        return id_rol;
    }

    public boolean isActivo() {
        return activo;
    }
    
    // SETTERS
    
    public void setFechaIngreso(LocalDate fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }
    
    public void setFechaEgreso(LocalDate fechaEgreso) {
        this.fechaEgreso = fechaEgreso;
    }
    
    public void setActivo (boolean setActivo) {
        this.activo = activo;
    }
    
    public void setRol (String rol) {
        this.rol = rol;
    } 

    public void setId_rol(int id_rol) {
        this.id_rol = id_rol;
    }
    
    // METODOS
    
    public void desactivar() {
        this.activo = false;
    }
    
    public void activar() {
        this.activo = true;
    }
    
    
}
