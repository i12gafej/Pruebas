/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TiempoEnAndalucia.Modelo;

import java.util.List;

/**
 *
 * @author USUARIO
 */

public class Ciudad {
    private String nombre;
    private List<Pronostico> pronosticos;

    public Ciudad(String nombre, List<Pronostico> pronosticos) {
        this.nombre = nombre;
        this.pronosticos = pronosticos;
    }

    public String getNombre() {
        return nombre;
    }

    public List<Pronostico> getPronosticos() {
        return pronosticos;
    }
}

