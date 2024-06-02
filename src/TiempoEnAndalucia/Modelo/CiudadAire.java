/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TiempoEnAndalucia.Modelo;

/**
 *
 * @author USUARIO
 */
public class CiudadAire {
    private String nombre;
    private String fecha;
    private String puntuacion;
    private Polen polen;
    private O3 o3;
    private Calima calima;

    public CiudadAire(String nombre, String fecha, String puntuacion, Polen polen, O3 o3, Calima calima) {
        this.nombre = nombre;
        this.fecha = fecha;
        this.puntuacion = puntuacion;
        this.polen = polen;
        this.o3 = o3;
        this.calima = calima;
    }

    public String getNombre() {
        return nombre;
    }

    public String getFecha() {
        return fecha;
    }

    public String getPuntuacion() {
        return puntuacion;
    }

    public Polen getPolen() {
        return polen;
    }

    public O3 getO3() {
        return o3;
    }

    public Calima getCalima() {
        return calima;
    }
}

