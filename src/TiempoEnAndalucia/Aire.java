package TiempoEnAndalucia;

import TiempoEnAndalucia.Controllers.AireJson;
import TiempoEnAndalucia.Controllers.InternationalizationManager;
import TiempoEnAndalucia.Modelo.CiudadAire;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.ResourceBundle;

public class Aire extends JFrame {

    private JLabel tituloLabel;
    private JLabel labelFecha;
    private JLabel labelPuntuacionResultado;
    private JLabel labelResultadoPolen;
    private JLabel labelNivelPolen;
    private JLabel labelPolenes;
    private JLabel labelResultadoO3;
    private JLabel labelNivelO3;
    private JLabel labelResultadoCalima;
    private JLabel labelNivelCalima;
    private JPanel panelPuntuacionColor;
    private JPanel panelPuntuacionColorPolen;
    private JPanel panelPuntuacionColorO3;
    private JPanel panelPuntuacionColorCalima;
    private String provincia;

    public Aire(String provincia) {
        this.provincia = provincia;
        initComponents();
        cargarDatos();
    }

    private void initComponents() {
        ResourceBundle bundle = InternationalizationManager.getResourceBundle();
        // Configurar la ventana principal
        setTitle(bundle.getString("TituloAire"));
        setSize(1483, 797);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        // Panel principal
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setLayout(null);
        mainPanel.setBounds(0, 0, 1483, 797);

        // Panel superior
        JPanel panelArriba = new JPanel();
        panelArriba.setBackground(Color.white);
        panelArriba.setLayout(null);
        panelArriba.setBounds(0, 0, 1483, 148);

        tituloLabel = new JLabel(bundle.getString("TituloAire") + " " + bundle.getString("En") + " " + provincia, JLabel.LEFT);
        tituloLabel.setFont(new Font("Segoe UI", Font.BOLD, 48));
        tituloLabel.setBounds(30, 20, 900, 100);
        panelArriba.add(tituloLabel);
        
        JButton volverButton = new JButton(bundle.getString("BotonVolver"));
        volverButton.setFont(new Font("Segoe UI", Font.BOLD, 24));
        volverButton.setBackground(new Color(204, 204, 204));
        volverButton.setForeground(Color.white);
        volverButton.setBounds(1050, 30, 200, 50);
        volverButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose(); // Cerrar la ventana actual
                SwingUtilities.invokeLater(() -> MainPagina.createAndShowGUI(InternationalizationManager.getResourceBundle(), (InternationalizationManager.getResourceBundle()).getString("TituloAire"), (InternationalizationManager.getResourceBundle()).getString("SubtituloBanner")));
            }
        });
        panelArriba.add(volverButton);

        mainPanel.add(panelArriba);

        // Panel inicial
        JPanel panelInicial = new JPanel();
        panelInicial.setLayout(null);
        panelInicial.setBackground(new Color(0, 153, 188));
        panelInicial.setBounds(85, 200, 493, 342);

        labelFecha = new JLabel("FECHA DE HOY", JLabel.CENTER);
        labelFecha.setFont(new Font("Segoe UI", Font.BOLD, 38));
        labelFecha.setForeground(Color.WHITE);
        labelFecha.setBounds(145, 36, 204, 66);
        panelInicial.add(labelFecha);

        JLabel labelPuntuacion = new JLabel(bundle.getString("Puntuacion"), JLabel.CENTER);
        labelPuntuacion.setFont(new Font("Segoe UI", Font.PLAIN, 23));
        labelPuntuacion.setForeground(Color.WHITE);
        labelPuntuacion.setBounds(178, 108, 136, 36);
        panelInicial.add(labelPuntuacion);

        panelPuntuacionColor = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(getBackground());
                g2d.fillOval(0, 0, getWidth(), getHeight());
            }

            @Override
            protected void paintBorder(Graphics g) {
                super.paintBorder(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(Color.white);
                int borderThickness = 3; // Cambia este valor para ajustar el grosor del borde
                g2d.setStroke(new BasicStroke(borderThickness));
                g2d.drawOval(borderThickness / 2, borderThickness / 2, getWidth() - borderThickness, getHeight() - borderThickness);
            }

            @Override
            public Dimension getPreferredSize() {
                return new Dimension(113, 80); // Aumentando la altura a 80 para hacerlo menos achatado
            }
        };
        panelPuntuacionColor.setOpaque(false);
        panelPuntuacionColor.setBackground(new Color(102, 255, 102));
        panelPuntuacionColor.setBounds(178, 162, 136, 54);
        panelInicial.add(panelPuntuacionColor);

        labelPuntuacionResultado = new JLabel(bundle.getString("AireBueno"), JLabel.CENTER);
        labelPuntuacionResultado.setFont(new Font("Segoe UI", Font.PLAIN, 30));
        labelPuntuacionResultado.setForeground(Color.WHITE);
        labelPuntuacionResultado.setBounds(71, 236, 350, 52);
        panelInicial.add(labelPuntuacionResultado);

        mainPanel.add(panelInicial);

        // Panel de resultados
        JPanel panelResultados = new JPanel();
        panelResultados.setBackground(Color.white);
        panelResultados.setLayout(null);
        panelResultados.setBounds(648, 200, 750, 500);

        // Panel de polen
        JPanel panelPolen = new JPanel();
        panelPolen.setLayout(null);
        panelPolen.setBackground(new Color(0, 153, 188));
        panelPolen.setBounds(0, 0, 750, 100);

        JLabel labelPolen = new JLabel(bundle.getString("Polen"), JLabel.LEFT);
        labelPolen.setFont(new Font("Segoe UI", Font.BOLD, 28));
        labelPolen.setForeground(Color.WHITE);
        labelPolen.setBounds(75, 20, 150, 34);
        panelPolen.add(labelPolen);

        panelPuntuacionColorPolen = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(getBackground());
                g2d.fillOval(0, 0, getWidth(), getHeight());
            }

            @Override
            protected void paintBorder(Graphics g) {
                super.paintBorder(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(Color.white);
                int borderThickness = 3; // Cambia este valor para ajustar el grosor del borde
                g2d.setStroke(new BasicStroke(borderThickness));
                g2d.drawOval(borderThickness / 2, borderThickness / 2, getWidth() - borderThickness, getHeight() - borderThickness);
            }

            @Override
            public Dimension getPreferredSize() {
                return new Dimension(113, 80); // Aumentando la altura a 80 para hacerlo menos achatado
            }
        };
        panelPuntuacionColorPolen.setOpaque(false);
        panelPuntuacionColorPolen.setBackground(new Color(255, 51, 51));
        panelPuntuacionColorPolen.setBounds(315, 20, 113, 54);
        panelPolen.add(panelPuntuacionColorPolen);

        labelResultadoPolen = new JLabel(bundle.getString("Extremo"), JLabel.LEFT);
        labelResultadoPolen.setFont(new Font("Segoe UI", Font.PLAIN, 23));
        labelResultadoPolen.setForeground(Color.WHITE);
        labelResultadoPolen.setBounds(490, 20, 150, 34);
        panelPolen.add(labelResultadoPolen);

        labelNivelPolen = new JLabel("100 %", JLabel.LEFT);
        labelNivelPolen.setFont(new Font("Segoe UI", Font.BOLD, 12));
        labelNivelPolen.setForeground(Color.WHITE);
        labelNivelPolen.setBounds(490, 60, 150, 16);
        panelPolen.add(labelNivelPolen);

        labelPolenes = new JLabel(bundle.getString("Olivo"), JLabel.LEFT);
        labelPolenes.setFont(new Font("Segoe UI", Font.BOLD, 12));
        labelPolenes.setForeground(Color.WHITE);
        labelPolenes.setBounds(75, 60, 150, 16);
        panelPolen.add(labelPolenes);

        panelResultados.add(panelPolen);

        // Panel de O3
        JPanel panelO3 = new JPanel();
        panelO3.setLayout(null);
        panelO3.setBackground(new Color(0, 153, 188));
        panelO3.setBounds(0, 200, 750, 100);

        JLabel labelO3 = new JLabel("O3", JLabel.LEFT);
        labelO3.setFont(new Font("Segoe UI", Font.BOLD, 28));
        labelO3.setForeground(Color.WHITE);
        labelO3.setBounds(75, 30, 150, 34);
        panelO3.add(labelO3);

        panelPuntuacionColorO3 = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(getBackground());
                g2d.fillOval(0, 0, getWidth(), getHeight());
            }

            @Override
            protected void paintBorder(Graphics g) {
                super.paintBorder(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(Color.white);
                int borderThickness = 3; // Cambia este valor para ajustar el grosor del borde
                g2d.setStroke(new BasicStroke(borderThickness));
                g2d.drawOval(borderThickness / 2, borderThickness / 2, getWidth() - borderThickness, getHeight() - borderThickness);
            }

            @Override
            public Dimension getPreferredSize() {
                return new Dimension(113, 80); // Aumentando la altura a 80 para hacerlo menos achatado
            }
        };
        panelPuntuacionColorO3.setOpaque(false);
        panelPuntuacionColorO3.setBackground(new Color(255, 51, 51));
        panelPuntuacionColorO3.setBounds(315, 20, 113, 54);
        panelO3.add(panelPuntuacionColorO3);

        labelResultadoO3 = new JLabel(bundle.getString("Extremo"), JLabel.LEFT);
        labelResultadoO3.setFont(new Font("Segoe UI", Font.PLAIN, 23));
        labelResultadoO3.setForeground(Color.WHITE);
        labelResultadoO3.setBounds(490, 20, 150, 34);
        panelO3.add(labelResultadoO3);

        labelNivelO3 = new JLabel("100 %", JLabel.LEFT);
        labelNivelO3.setFont(new Font("Segoe UI", Font.BOLD, 12));
        labelNivelO3.setForeground(Color.WHITE);
        labelNivelO3.setBounds(490, 60, 150, 16);
        panelO3.add(labelNivelO3);

        panelResultados.add(panelO3);

        // Panel de calima
        JPanel panelCalima = new JPanel();
        panelCalima.setLayout(null);
        panelCalima.setBackground(new Color(0, 153, 188));
        panelCalima.setBounds(0, 400, 750, 100);

        JLabel labelCalima = new JLabel(bundle.getString("Calima"), JLabel.LEFT);
        labelCalima.setFont(new Font("Segoe UI", Font.BOLD, 28));
        labelCalima.setForeground(Color.WHITE);
        labelCalima.setBounds(75, 30, 150, 34);
        panelCalima.add(labelCalima);

        panelPuntuacionColorCalima = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(getBackground());
                g2d.fillOval(0, 0, getWidth(), getHeight());
            }

            @Override
            protected void paintBorder(Graphics g) {
                super.paintBorder(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(Color.white);
                int borderThickness = 3; // Cambia este valor para ajustar el grosor del borde
                g2d.setStroke(new BasicStroke(borderThickness));
                g2d.drawOval(borderThickness / 2, borderThickness / 2, getWidth() - borderThickness, getHeight() - borderThickness);
            }

            @Override
            public Dimension getPreferredSize() {
                return new Dimension(113, 80); // Aumentando la altura a 80 para hacerlo menos achatado
            }
        };
        panelPuntuacionColorCalima.setOpaque(false);
        panelPuntuacionColorCalima.setBackground(new Color(255, 51, 51));
        panelPuntuacionColorCalima.setBounds(315, 20, 113, 54);
        panelCalima.add(panelPuntuacionColorCalima);

        labelResultadoCalima = new JLabel(bundle.getString("Extremo"), JLabel.LEFT);
        labelResultadoCalima.setFont(new Font("Segoe UI", Font.PLAIN, 23));
        labelResultadoCalima.setForeground(Color.WHITE);
        labelResultadoCalima.setBounds(490, 20, 150, 34);
        panelCalima.add(labelResultadoCalima);

        labelNivelCalima = new JLabel("100 %", JLabel.LEFT);
        labelNivelCalima.setFont(new Font("Segoe UI", Font.BOLD, 12));
        labelNivelCalima.setForeground(Color.WHITE);
        labelNivelCalima.setBounds(490, 60, 150, 16);
        panelCalima.add(labelNivelCalima);

        panelResultados.add(panelCalima);

        mainPanel.add(panelResultados);

        add(mainPanel);
    }

    private void cargarDatos() {
        ResourceBundle bundle = InternationalizationManager.getResourceBundle();
        List<CiudadAire> ciudades = AireJson.leerCalidadAire("calidad_aire_andalucia.json");
        for (CiudadAire ciudad : ciudades) {
            if (ciudad.getNombre().equalsIgnoreCase(provincia)) {
                tituloLabel.setText(bundle.getString("TituloAire")+ " " + bundle.getString("En")+ " " + ciudad.getNombre());
                labelFecha.setText(ciudad.getFecha());
                labelPuntuacionResultado.setText(bundle.getString(ciudad.getPuntuacion()));

                // Cambiar color del panel inicial según la puntuación
                switch (ciudad.getPuntuacion().toLowerCase()) {
                    case "aire de buena calidad":
                        panelPuntuacionColor.setBackground(new Color(102, 255, 102)); // Verde
                        break;
                    case "aire de calidad regular":
                        panelPuntuacionColor.setBackground(new Color(255, 165, 0)); // Naranja
                        break;
                    case "aire de peor calidad":
                        panelPuntuacionColor.setBackground(new Color(255, 51, 51)); // Rojo
                        break;
                }

                labelResultadoPolen.setText(bundle.getString(ciudad.getPolen().getNivel()));
                labelNivelPolen.setText(ciudad.getPolen().getResultado() + " %");
                labelPolenes.setText(bundle.getString("Olivo")); // Puedes ajustar esto según sea necesario

                // Cambiar color del panel de polen según el nivel
                switch (ciudad.getPolen().getNivel().toLowerCase()) {
                    case "bajo":
                        panelPuntuacionColorPolen.setBackground(new Color(102, 255, 102)); // Verde
                        break;
                    case "moderado":
                        panelPuntuacionColorPolen.setBackground(new Color(255, 255, 0)); // Amarillo
                        break;
                    case "alto":
                        panelPuntuacionColorPolen.setBackground(new Color(255, 165, 0)); // Naranja
                        break;
                    case "extremo":
                        panelPuntuacionColorPolen.setBackground(new Color(255, 51, 51)); // Rojo
                        break;
                }

                labelResultadoO3.setText(bundle.getString(ciudad.getO3().getNivel()));
                labelNivelO3.setText(ciudad.getO3().getResultado() + " %");

                // Cambiar color del panel de O3 según el nivel
                switch (ciudad.getO3().getNivel().toLowerCase()) {
                    case "bajo":
                        panelPuntuacionColorO3.setBackground(new Color(102, 255, 102)); // Verde
                        break;
                    case "moderado":
                        panelPuntuacionColorO3.setBackground(new Color(255, 255, 0)); // Amarillo
                        break;
                    case "alto":
                        panelPuntuacionColorO3.setBackground(new Color(255, 165, 0)); // Naranja
                        break;
                    case "extremo":
                        panelPuntuacionColorO3.setBackground(new Color(255, 51, 51)); // Rojo
                        break;
                }

                labelResultadoCalima.setText(bundle.getString(ciudad.getCalima().getNivel()));
                labelNivelCalima.setText(ciudad.getCalima().getResultado() + " %");

                // Cambiar color del panel de calima según el nivel
                switch (ciudad.getCalima().getNivel().toLowerCase()) {
                    case "bajo":
                        panelPuntuacionColorCalima.setBackground(new Color(102, 255, 102)); // Verde
                        break;
                    case "moderado":
                        panelPuntuacionColorCalima.setBackground(new Color(255, 255, 0)); // Amarillo
                        break;
                    case "alto":
                        panelPuntuacionColorCalima.setBackground(new Color(255, 165, 0)); // Naranja
                        break;
                    case "extremo":
                        panelPuntuacionColorCalima.setBackground(new Color(255, 51, 51)); // Rojo
                        break;
                }
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Aire("Córdoba").setVisible(true));
    }
}
