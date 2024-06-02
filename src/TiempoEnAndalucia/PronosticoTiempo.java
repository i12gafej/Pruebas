package TiempoEnAndalucia;

import TiempoEnAndalucia.Controllers.InternationalizationManager;
import TiempoEnAndalucia.Controllers.TiempoJson;
import TiempoEnAndalucia.Modelo.Ciudad;
import TiempoEnAndalucia.Modelo.Pronostico;
import TiempoEnAndalucia.Modelo.PronosticosHoras;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalTime;
import java.util.List;
import java.util.ResourceBundle;

public class PronosticoTiempo extends JFrame {

    private List<Pronostico> pronosticos;
    private String provincia;
    private JPanel selectedDayPanel;

    public PronosticoTiempo(String provincia) {
        this.provincia = provincia;
        initComponents();
    }

    private void initComponents() {
        ResourceBundle bundle = InternationalizationManager.getResourceBundle();
        // Establecer el tamaño de la ventana
        this.setSize(1438, 797);

        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(Color.white);
        mainPanel.setLayout(null);

        // Panel de título
        JPanel panelTitulo = new JPanel(null);
        panelTitulo.setBackground(Color.white);
        panelTitulo.setBounds(0, 0, 1438, 120);

        JLabel tituloLabel = new JLabel(bundle.getString("ElTiempo") + " " + bundle.getString("En") + " " + this.provincia, JLabel.CENTER);
        tituloLabel.setFont(new Font("Segoe UI", Font.BOLD, 48));
        tituloLabel.setBounds(0, 0, 900, 100);
        panelTitulo.add(tituloLabel);

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
        panelTitulo.add(volverButton);

        mainPanel.add(panelTitulo);

        // Panel de inicio
        JPanel panelInicio = new JPanel(null);
        panelInicio.setBackground(new Color(58, 195, 255));
        panelInicio.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        panelInicio.setBounds(10, 160, 350, 270);

        JLabel cieloLabel = new JLabel(bundle.getString("Soleado"), JLabel.CENTER);
        cieloLabel.setFont(new Font("Segoe UI", Font.PLAIN, 30));
        cieloLabel.setBounds(20, 50, 160, 50);
        panelInicio.add(cieloLabel);

        JLabel tempLabel = new JLabel("23º", JLabel.CENTER);
        tempLabel.setFont(new Font("Segoe UI", Font.PLAIN, 60));
        tempLabel.setBounds(30, 100, 160, 70);
        panelInicio.add(tempLabel);

        JLabel sensacionLabel = new JLabel(bundle.getString("Sensacion") + " " +bundle.getString("De")+" 24º", JLabel.CENTER);
        sensacionLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        sensacionLabel.setBounds(20, 180, 160, 30);
        panelInicio.add(sensacionLabel);

        JLabel iconoViento = new JLabel(); // Ícono real del viento
        iconoViento.setFont(new Font("Segoe UI", Font.PLAIN, 60));
        iconoViento.setBounds(230, 80, 160, 50);
        panelInicio.add(iconoViento);

        JLabel direccionVientoLabel = new JLabel("", JLabel.CENTER);
        direccionVientoLabel.setFont(new Font("Segoe UI", Font.BOLD, 12));
        direccionVientoLabel.setBounds(180, 160, 160, 30);
        panelInicio.add(direccionVientoLabel);

        JLabel velocidadVientoLabel = new JLabel("9-20 km/h", JLabel.CENTER);
        velocidadVientoLabel.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        velocidadVientoLabel.setBounds(180, 140, 160, 30);
        panelInicio.add(velocidadVientoLabel);

        mainPanel.add(panelInicio);

        // Panel de días
        JPanel panelDias = new JPanel(null);
        panelDias.setBounds(370, 150, 1050, 300);
        panelDias.setBackground(Color.white);

        for (int i = 0; i < 7; i++) {
            JPanel diaPanel = new JPanel(null);
            if(i == 0) {
                diaPanel.setBackground(new Color(0xC9, 0xFC, 0xF5));
                selectedDayPanel = diaPanel; // El primer día está seleccionado inicialmente
            } else {
                diaPanel.setBackground(Color.white);
            }
            diaPanel.setBorder(BorderFactory.createLineBorder(Color.black, 2));
            diaPanel.setBounds(i * 150, 10, 140, 270);

            JLabel diaLabel = new JLabel("", JLabel.CENTER);
            diaLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
            diaLabel.setBounds(10, 10, 120, 30);
            diaPanel.add(diaLabel);

            JLabel fechaLabel = new JLabel("", JLabel.CENTER);
            fechaLabel.setFont(new Font("Segoe UI", Font.BOLD, 12));
            fechaLabel.setBounds(10, 50, 120, 30);
            diaPanel.add(fechaLabel);

            JLabel iconoCielo = new JLabel("", JLabel.CENTER);
            iconoCielo.setBounds(10, 90, 120, 50);
            diaPanel.add(iconoCielo);

            JLabel tempDiaLabel = new JLabel("", JLabel.CENTER);
            tempDiaLabel.setBounds(10, 130, 120, 30);
            diaPanel.add(tempDiaLabel);

            JLabel iconoVientoDia = new JLabel("", JLabel.CENTER);
            iconoVientoDia.setBounds(10, 170, 120, 50);
            diaPanel.add(iconoVientoDia);

            JLabel velocidadLabelDia = new JLabel("", JLabel.CENTER);
            velocidadLabelDia.setBounds(10, 210, 120, 30);
            diaPanel.add(velocidadLabelDia);

            diaPanel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    // Cambiar el fondo del día anteriormente seleccionado a blanco
                    if (selectedDayPanel != null) {
                        selectedDayPanel.setBackground(Color.white);
                    }

                    // Establecer el nuevo día seleccionado
                    selectedDayPanel = diaPanel;
                    diaPanel.setBackground(new Color(0xC9, 0xFC, 0xF5));
                    int index = panelDias.getComponentZOrder(diaPanel);
                    if (index >= 0 && index < pronosticos.size()) {
                        cargarPronosticosHoras(pronosticos.get(index).getPronosticosHoras());
                    }
                }
            });

            panelDias.add(diaPanel);
        }

        mainPanel.add(panelDias);

        // Panel de horas
        JPanel panelHoras = new JPanel(null);
        panelHoras.setBounds(5, 440, 1418, 300);
        panelHoras.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));  // Añadir margen inferior
        panelHoras.setBackground(Color.white);

        int panelWidth = 160;
        int gap = (1418 - (panelWidth * 8)) / 9; // Cálculo del espacio entre los paneles y los laterales

        for (int i = 0; i < 8; i++) {
            JPanel horaPanel = new JPanel(null);
            horaPanel.setBackground(Color.white);
            horaPanel.setBorder(BorderFactory.createLineBorder(Color.black, 2));
            horaPanel.setBounds(gap + i * (panelWidth + gap), 10, panelWidth, 280);

            JLabel horaLabel = new JLabel("", JLabel.CENTER);
            horaLabel.setFont(new Font("Segoe UI", Font.BOLD, 12));
            horaLabel.setBounds(10, 10, panelWidth - 20, 24);
            horaPanel.add(horaLabel);

            JLabel iconoCieloHora = new JLabel("", JLabel.CENTER);
            iconoCieloHora.setBounds(10, 40, panelWidth - 20, 50);
            horaPanel.add(iconoCieloHora);

            JLabel cieloHoraLabel = new JLabel("", JLabel.CENTER);
            cieloHoraLabel.setBounds(10, 80, panelWidth - 20, 30);
            horaPanel.add(cieloHoraLabel);

            JLabel tempHoraLabel = new JLabel("", JLabel.CENTER);
            tempHoraLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
            tempHoraLabel.setBounds(10, 110, panelWidth - 20, 24);
            horaPanel.add(tempHoraLabel);

            JLabel sensacionHoraLabel = new JLabel("", JLabel.CENTER);
            sensacionHoraLabel.setBounds(10, 140, panelWidth - 20, 24);
            horaPanel.add(sensacionHoraLabel);

            JLabel iconoVientoHora = new JLabel("", JLabel.CENTER);
            iconoVientoHora.setBounds(10, 170, panelWidth - 20, 50);
            horaPanel.add(iconoVientoHora);

            JLabel velocidadHoraLabel = new JLabel("", JLabel.CENTER);
            velocidadHoraLabel.setBounds(10, 220, panelWidth - 20, 24);
            horaPanel.add(velocidadHoraLabel);

            JLabel lluviaHoraLabel = new JLabel("", JLabel.CENTER);
            lluviaHoraLabel.setBounds(10, 240, panelWidth - 20, 24);
            horaPanel.add(lluviaHoraLabel);

            JLabel probLluviaHoraLabel = new JLabel("", JLabel.CENTER);
            probLluviaHoraLabel.setBounds(10, 270, panelWidth - 20, 24);
            horaPanel.add(probLluviaHoraLabel);

            panelHoras.add(horaPanel);
        }

        mainPanel.add(panelHoras);

        // Configurar JFrame
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.setLocationRelativeTo(null);

        // Cargar datos desde el JSON y actualizar la interfaz
        cargarDatos();
    }

    private void cargarDatos() {
        ResourceBundle bundle = InternationalizationManager.getResourceBundle();
        List<Ciudad> ciudades = TiempoJson.leerPronostico("pronostico_andalucia.json");
        for (Ciudad ciudad : ciudades) {
            if (ciudad.getNombre().equalsIgnoreCase(this.provincia)) {
                pronosticos = ciudad.getPronosticos();
                JPanel panelDias = (JPanel) this.getContentPane().getComponent(2);
                for (int i = 0; i < pronosticos.size() && i < 7; i++) {
                    Pronostico pronostico = pronosticos.get(i);
                    JPanel diaPanel = (JPanel) panelDias.getComponent(i);

                    JLabel diaLabel = (JLabel) diaPanel.getComponent(0);
                    diaLabel.setText(bundle.getString(pronostico.getDiaSemana()));

                    JLabel fechaLabel = (JLabel) diaPanel.getComponent(1);
                    fechaLabel.setText(pronostico.getNumeroDia() + " " + bundle.getString(pronostico.getMes()));

                    JLabel iconoCielo = (JLabel) diaPanel.getComponent(2);
                    iconoCielo.setIcon(new ImageIcon("src/TiempoEnAndalucia/imagenes/" + pronostico.getCielo()));

                    JLabel tempDiaLabel = (JLabel) diaPanel.getComponent(3);
                    tempDiaLabel.setText(pronostico.getMaximaT() + "º / " + pronostico.getMinimaT() + "º");

                    JLabel iconoVientoDia = (JLabel) diaPanel.getComponent(4);
                    iconoVientoDia.setIcon(new ImageIcon("src/TiempoEnAndalucia/imagenes/" + pronostico.getViento())); // Ícono real del viento

                    JLabel velocidadLabelDia = (JLabel) diaPanel.getComponent(5);
                    velocidadLabelDia.setText(pronostico.getRangoVelocidadViento());
                }

                // Obtener la hora actual y cargar los datos correspondientes
                LocalTime horaActual = LocalTime.now();
                int horaActualInt = horaActual.getHour();
                if (!pronosticos.isEmpty()) {
                    List<PronosticosHoras> pronosticosHoras = pronosticos.get(0).getPronosticosHoras();
                    for (PronosticosHoras pronosticoHora : pronosticosHoras) {
                        int horaPronosticoInt = Integer.parseInt(pronosticoHora.getHora().split(":")[0]);
                        if (horaActualInt >= horaPronosticoInt && horaActualInt < horaPronosticoInt + 3) {
                            cargarDatosPanelInicio(pronosticoHora);
                            break;
                        }
                    }
                    cargarPronosticosHoras(pronosticosHoras);
                }
            }
        }
    }

    private void cargarDatosPanelInicio(PronosticosHoras pronosticoHora) {
        ResourceBundle bundle = InternationalizationManager.getResourceBundle();
        JPanel panelInicio = (JPanel) this.getContentPane().getComponent(1);

        JLabel cieloLabel = (JLabel) panelInicio.getComponent(0);
        cieloLabel.setText(bundle.getString(pronosticoHora.getDescripcionCielo()));

        JLabel tempLabel = (JLabel) panelInicio.getComponent(1);
        tempLabel.setText(pronosticoHora.getSensacionTermica() + "º");

        JLabel sensacionLabel = (JLabel) panelInicio.getComponent(2);
        sensacionLabel.setText(bundle.getString("Sensacion")+" "+bundle.getString("De")+ " " + pronosticoHora.getSensacionTermica() + "º");

        JLabel iconoViento = (JLabel) panelInicio.getComponent(3);
        iconoViento.setIcon(new ImageIcon("src/TiempoEnAndalucia/imagenes/" + pronosticoHora.getViento())); // Ícono real del viento

        JLabel direccionVientoLabel = (JLabel) panelInicio.getComponent(4);
        direccionVientoLabel.setText(pronosticoHora.getViento());

        JLabel velocidadVientoLabel = (JLabel) panelInicio.getComponent(5);
        velocidadVientoLabel.setText(pronosticoHora.getVelocidadViento() + " km/h");
    }

    private void cargarPronosticosHoras(List<PronosticosHoras> pronosticosHoras) {
        ResourceBundle bundle = InternationalizationManager.getResourceBundle();
        JPanel panelHoras = (JPanel) this.getContentPane().getComponent(3);
        for (int i = 0; i < pronosticosHoras.size() && i < 8; i++) {
            PronosticosHoras pronosticoHora = pronosticosHoras.get(i);
            JPanel horaPanel = (JPanel) panelHoras.getComponent(i);

            JLabel horaLabel = (JLabel) horaPanel.getComponent(0);
            horaLabel.setText(pronosticoHora.getHora());

            JLabel iconoCieloHora = (JLabel) horaPanel.getComponent(1);
            iconoCieloHora.setIcon(new ImageIcon("src/TiempoEnAndalucia/imagenes/" + pronosticoHora.getCielo()));

            JLabel cieloHoraLabel = (JLabel) horaPanel.getComponent(2);
            cieloHoraLabel.setText(bundle.getString(pronosticoHora.getDescripcionCielo()));

            JLabel tempHoraLabel = (JLabel) horaPanel.getComponent(3);
            tempHoraLabel.setText(pronosticoHora.getSensacionTermica() + "º");

            JLabel sensacionHoraLabel = (JLabel) horaPanel.getComponent(4);
            sensacionHoraLabel.setText(bundle.getString("Sensacion")+": " + pronosticoHora.getSensacionTermica() + "º");

            JLabel iconoVientoHora = (JLabel) horaPanel.getComponent(5);
            iconoVientoHora.setIcon(new ImageIcon("src/TiempoEnAndalucia/imagenes/" + pronosticoHora.getViento())); // Ícono real del viento

            JLabel velocidadHoraLabel = (JLabel) horaPanel.getComponent(6);
            velocidadHoraLabel.setText(pronosticoHora.getVelocidadViento() + " km/h");

            JLabel lluviaHoraLabel = (JLabel) horaPanel.getComponent(7);
            lluviaHoraLabel.setText(pronosticoHora.getMmLluvia() + " mm");

            JLabel probLluviaHoraLabel = (JLabel) horaPanel.getComponent(8);
            probLluviaHoraLabel.setText(pronosticoHora.getProbLluvia() + "%");
        }
    }
}
