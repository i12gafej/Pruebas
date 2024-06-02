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
public class Pronostico {
    private String diaSemana;
    private int numeroDia;
    private String mes;
    private String cielo;
    private int maximaT;
    private int minimaT;
    private String viento;
    private String rangoVelocidadViento;
    private List<PronosticosHoras> rangos;

    public Pronostico(String diaSemana, int numeroDia, String mes, String cielo, int maximaT, int minimaT, String viento, String rangoVelocidadViento, List<PronosticosHoras> rangos) {
        this.diaSemana = diaSemana;
        this.numeroDia = numeroDia;
        this.mes = mes;
        this.cielo = cielo;
        this.maximaT = maximaT;
        this.minimaT = minimaT;
        this.viento = viento;
        this.rangoVelocidadViento = rangoVelocidadViento;
        this.rangos = rangos;
    }

    public String getDiaSemana() {
        return diaSemana;
    }

    public int getNumeroDia() {
        return numeroDia;
    }

    public String getMes() {
        return mes;
    }

    public String getCielo() {
        return cielo;
    }

    public int getMaximaT() {
        return maximaT;
    }

    public int getMinimaT() {
        return minimaT;
    }

    public String getViento() {
        return viento;
    }

    public String getRangoVelocidadViento() {
        return rangoVelocidadViento;
    }

    public List<PronosticosHoras> getPronosticosHoras() {
        return rangos;
    }
}

