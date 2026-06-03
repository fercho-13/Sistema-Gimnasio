/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trabajopracticointegrador;

/**
 *
 * @author fermi
 */
public abstract class Persona {
    
    // ATRIBUTOS
    protected String nombre;
    protected String apellido;
    protected String DNI;
    protected String direccion;
    protected String numeroTelefono;
    
    // CONSTRUCTOR VACIO
    
    public Persona() {
        
    }
    
    // CONSTRUCTOR CON PARAMETROS

    public Persona(String nombre, String apellido, String DNI, String direccion, String numeroTelefono) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.DNI = DNI;
        this.direccion = direccion;
        this.numeroTelefono = numeroTelefono;
    }
    
    // GETTERS

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getDNI() {
        return DNI;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getNumeroTelefono() {
        return numeroTelefono;
    }
    
    // SETTERS 

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setNumeroTelefono(String numeroTelefono) {
        this.numeroTelefono = numeroTelefono;
    }
    
}
