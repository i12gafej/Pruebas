/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TiempoEnAndalucia;

import TiempoEnAndalucia.Controllers.InternationalizationManager;

import javax.swing.*;
import java.awt.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ResourceBundle;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

public class Contacto extends JFrame {

    public Contacto() {
        initComponents();
    }

    private void initComponents() {
        ResourceBundle bundle = InternationalizationManager.getResourceBundle();
        setTitle(bundle.getString("Contacto"));
        setSize(1483, 797);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);

        // Panel principal
        JLayeredPane mainPanel = new JLayeredPane();
        mainPanel.setBounds(0, 0, 1483, 797);
        mainPanel.setOpaque(true);
        mainPanel.setBackground(Color.WHITE);

        // Panel arriba
        JLayeredPane panelArriba = new JLayeredPane();
        panelArriba.setBounds(0, 0, 1483, 150);
        panelArriba.setOpaque(false);

        JLabel labelTitulo = new JLabel(bundle.getString("TituloContacto"), JLabel.CENTER);
        labelTitulo.setFont(new Font("Segoe UI", Font.BOLD, 64));
        labelTitulo.setBounds(505, 20, 472, 80);
        panelArriba.add(labelTitulo);

        JButton volverButton = new JButton(bundle.getString("BotonVolver"));
        volverButton.setFont(new Font("Segoe UI", Font.BOLD, 24));
        volverButton.setBackground(new Color(204, 204, 204));
        volverButton.setForeground(Color.white);
        volverButton.setBounds(1050, 30, 200, 50);
        volverButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose(); // Cerrar la ventana actual
                SwingUtilities.invokeLater(() -> MainPagina.createAndShowGUI(InternationalizationManager.getResourceBundle(), (InternationalizationManager.getResourceBundle()).getString("TituloTiempo"), (InternationalizationManager.getResourceBundle()).getString("SubtituloBanner")));
            }
        });
        panelArriba.add(volverButton);

        // Panel izquierda
        JLayeredPane panelIzquierda = new JLayeredPane();
        panelIzquierda.setBounds(136, 170, 506, 574);
        panelIzquierda.setOpaque(false);

        JLabel labelTelef = new JLabel(bundle.getString("Tlfn"), JLabel.LEFT);
        labelTelef.setFont(new Font("Segoe UI", Font.BOLD, 24));
        labelTelef.setBounds(0, 0, 506, 45);
        panelIzquierda.add(labelTelef);

        JLabel labelTelefNum = new JLabel("999 777 999", JLabel.LEFT);
        labelTelefNum.setFont(new Font("Segoe UI", Font.PLAIN, 24));
        labelTelefNum.setBounds(0, 65, 506, 48);
        panelIzquierda.add(labelTelefNum);

        JLabel labelCorreo = new JLabel(bundle.getString("Correo"), JLabel.LEFT);
        labelCorreo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        labelCorreo.setBounds(0, 140, 506, 45);
        panelIzquierda.add(labelCorreo);

        JLabel labelCorreoE = new JLabel("tiempoenadalucia@info.es", JLabel.LEFT);
        labelCorreoE.setFont(new Font("Segoe UI", Font.PLAIN, 24));
        labelCorreoE.setBounds(0, 205, 506, 58);
        panelIzquierda.add(labelCorreoE);

        JLabel labelDir = new JLabel(bundle.getString("Direc"), JLabel.LEFT);
        labelDir.setFont(new Font("Segoe UI", Font.BOLD, 24));
        labelDir.setBounds(0, 290, 506, 45);
        panelIzquierda.add(labelDir);

        JLabel labelDireccion = new JLabel("Av. de las Avenidas 5, 14001, Córdoba", JLabel.LEFT);
        labelDireccion.setFont(new Font("Segoe UI", Font.PLAIN, 24));
        labelDireccion.setBounds(0, 355, 506, 58);
        panelIzquierda.add(labelDireccion);

        JLabel labelRRSS = new JLabel(bundle.getString("RedesSociales")+" (X, Instagram, Facebook)", JLabel.LEFT);
        labelRRSS.setFont(new Font("Segoe UI", Font.BOLD, 24));
        labelRRSS.setBounds(0, 440, 506, 45);
        panelIzquierda.add(labelRRSS);

        JLabel labelArroba = new JLabel("@tiempoenandalucia", JLabel.LEFT);
        labelArroba.setFont(new Font("Segoe UI", Font.PLAIN, 24));
        labelArroba.setBounds(0, 515, 506, 44);
        panelIzquierda.add(labelArroba);

        // Panel derecha
        JLayeredPane panelDerecha = new JLayeredPane();
        panelDerecha.setBounds(660, 170, 700, 574);
        panelDerecha.setOpaque(false);

        JLabel labelDuda = new JLabel(bundle.getString("Duda"), JLabel.LEFT);
        labelDuda.setFont(new Font("Segoe UI", Font.BOLD, 24));
        labelDuda.setBounds(0, 0, 800, 45);
        panelDerecha.add(labelDuda);

        JTextArea textAreaDuda = new JTextArea();
        textAreaDuda.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        JScrollPane scrollDuda = new JScrollPane(textAreaDuda);
        scrollDuda.setBounds(0, 65, 700, 200);
        panelDerecha.add(scrollDuda);

        JButton botonEnviar = new JButton(bundle.getString("BotonEnviar"));
        botonEnviar.setFont(new Font("Segoe UI", Font.BOLD, 18));
        botonEnviar.setBackground(new Color(243, 243, 243));
        botonEnviar.setBounds(490, 277, 206, 61);
        panelDerecha.add(botonEnviar);

        JLabel labelValorar1 = new JLabel(bundle.getString("PreguntaExp"), JLabel.LEFT);
        labelValorar1.setFont(new Font("Segoe UI", Font.BOLD, 24));
        labelValorar1.setBounds(0, 330, 700, 45);
        panelDerecha.add(labelValorar1);

        JLabel labelValorar2 = new JLabel(bundle.getString("PreguntaExpSub"), JLabel.LEFT);
        labelValorar2.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        labelValorar2.setBounds(0, 375, 500, 30);
        panelDerecha.add(labelValorar2);

        JButton botonValorar = new JButton(bundle.getString("ValorarMiExp"));
        botonValorar.setFont(new Font("Segoe UI", Font.BOLD, 18));
        botonValorar.setBackground(new Color(243, 243, 243));
        botonValorar.setBounds(230, 440, 250, 60);
        panelDerecha.add(botonValorar);

        botonValorar.addActionListener(e -> {

            dispose(); // Cerrar la ventana actual

            // Crear y mostrar la nueva ventana de Valoraciones
            SwingUtilities.invokeLater(() -> {
                Valoraciones valoraciones = new Valoraciones();
                valoraciones.setVisible(true); // Asegúrate de que el constructor de Valoraciones configure la visibilidad
            });
        });

        mainPanel.add(panelArriba);
        mainPanel.add(panelIzquierda);
        mainPanel.add(panelDerecha);

        add(mainPanel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Contacto().setVisible(true));
    }
}
