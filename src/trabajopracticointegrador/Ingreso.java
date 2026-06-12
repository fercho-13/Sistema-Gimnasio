/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trabajopracticointegrador;
import java.time.LocalDateTime;

/**
 *
 * @author fermi
 */
public class Ingreso {
    
    // ATRIBUTOS
    private int id_ingreso;
    private int id_socio;
    private LocalDateTime fecha_hora;
    private String acceso;
    
    // CONSTRUCTOR

    public Ingreso() {
    }
    
    // GETTERS 

    public int getId_ingreso() {
        return id_ingreso;
    }

    public int getId_socio() {
        return id_socio;
    }

    public LocalDateTime getFecha_hora() {
        return fecha_hora;
    }

    public String getAcceso() {
        return acceso;
    }
    
    // SETTERS 

    public void setId_ingreso(int id_ingreso) {
        this.id_ingreso = id_ingreso;
    }

    public void setId_socio(int id_socio) {
        this.id_socio = id_socio;
    }

    public void setFecha_hora(LocalDateTime fecha_hora) {
        this.fecha_hora = fecha_hora;
    }

    public void setAcceso(String acceso) {
        this.acceso = acceso;
    }
}
