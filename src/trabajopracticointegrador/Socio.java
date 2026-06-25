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
public class Socio extends Persona {
    
    // ATRIBUTOS
    private int id_socio;
    private LocalDate fechaAlta;
    private Suscripcion suscripcion;
    private boolean activo;
    
    // CONSTRUCTOR VACIO
    public Socio() {
        
    }
    
    // CONSTRUCTOR CON PARAMETROS (SIN SUSCRIPCION)
    
    public Socio(String nombre, String apellido, String DNI, String direccion, String numeroTelefono) {
        super(nombre, apellido, DNI, direccion, numeroTelefono);
        this.activo = true;
        this.fechaAlta = LocalDate.now();
    }
    
    // CONSTRUCTOR CON PARAMETROS (CON SUSCRIPCION)
    
    public Socio(String nombre, String apellido, String DNI, String direccion, String numeroTelefono, Suscripcion suscripcion) {
        super(nombre, apellido, DNI, direccion, numeroTelefono);
        this.suscripcion = suscripcion;
        this.activo = true;
        this.fechaAlta = LocalDate.now();
    }
    
    // GETTERS 
    
    public int getId_Socio() {
        return id_socio;
    }

    public LocalDate getFechaAlta() {
        return fechaAlta;
    }

    public Suscripcion getSuscripcion() {
        return suscripcion;
    }

    public boolean isActivo() {
        return activo;
    }
    
    // SETTERS

    public void setId_Socio(int id_socio) {
        this.id_socio = id_socio;
    }
    
    public void setFechaAlta(LocalDate fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public void setSuscripcion(Suscripcion suscripcion) {
        this.suscripcion = suscripcion;
    }
    
    public void setActivo(boolean activo) {
        this.activo = activo;
    }
    
    // METODOS
    
    public void desactivar() {
        this.activo = false;
    }
    
    public void activar() {
        this.activo = true;
    }
    
}
