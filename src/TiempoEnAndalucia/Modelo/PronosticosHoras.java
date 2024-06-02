/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TiempoEnAndalucia.Modelo;

/**
 *
 * @author USUARIO
 */
public class PronosticosHoras {
    private String hora;
    private String cielo;
    private String descripcionCielo;
    private int sensacionTermica;
    private String viento;
    private int velocidadViento;
    private double mmLluvia;
    private int probLluvia;

    public PronosticosHoras(String hora, String cielo, String descripcionCielo, int sensacionTermica, String viento, int velocidadViento, double mmLluvia, int probLluvia) {
        this.hora = hora;
        this.cielo = cielo;
        this.descripcionCielo = descripcionCielo;
        this.sensacionTermica = sensacionTermica;
        this.viento = viento;
        this.velocidadViento = velocidadViento;
        this.mmLluvia = mmLluvia;
        this.probLluvia = probLluvia;
    }

    public String getHora() {
        return hora;
    }

    public String getCielo() {
        return cielo;
    }

    public String getDescripcionCielo() {
        return descripcionCielo;
    }

    public int getSensacionTermica() {
        return sensacionTermica;
    }

    public String getViento() {
        return viento;
    }

    public int getVelocidadViento() {
        return velocidadViento;
    }

    public double getMmLluvia() {
        return mmLluvia;
    }

    public int getProbLluvia() {
        return probLluvia;
    }
}

