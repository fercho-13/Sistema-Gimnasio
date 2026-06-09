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
public abstract class Suscripcion {
    
    // ATRIBUTOS
    private int id_suscripcion;
    private int id_plan;
    private String descripcion;
    private double valor;
    private boolean activo;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;

    
    // CONSTRUCTOR VACIO
    
    public Suscripcion() {
        
    }
    
    // CONSTRUCTOR CON PARAMETROS
    
    public Suscripcion(String descripcion, double valor) {
        this.descripcion = descripcion;
        this.valor = valor;
        this.activo = true;
        this.fechaInicio = LocalDate.now();
        this.fechaFin = null;
    }
    
    // GETTERS
    
    public int getId_Suscripcion() {
        return id_suscripcion;
    }

    public int getId_plan() {
        return id_plan;
    }
    
    public String getDescripcion() {
        return descripcion;
    }

    public double getValor() {
        return valor;
    }

    public boolean isActivo() {
        return activo;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }
    
    // SETTERS
    
    public void setId_Suscripcion(int id_suscripcion) {
        this.id_suscripcion = id_suscripcion;
    }

    public void setId_plan(int id_plan) {
        this.id_plan = id_plan;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
    
    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }
    
    public void setActivo (boolean activo) {
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
