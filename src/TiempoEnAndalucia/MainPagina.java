package TiempoEnAndalucia;

import TiempoEnAndalucia.Controllers.InternationalizationManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

public class MainPagina {

    private static String tituloBanner;

    public static void main(String[] args) {

        InternationalizationManager.setLocale(new Locale("es", "ES"));

        ResourceBundle bundle = InternationalizationManager.getResourceBundle();

        SwingUtilities.invokeLater(() -> createAndShowGUI(bundle,bundle.getString("TituloBanner"),bundle.getString("SubtituloBanner")));
    }

    public static void createAndShowGUI(ResourceBundle bundle, String titulo, String subtitulo) {
        tituloBanner = titulo;

        JFrame frame = new JFrame(titulo);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1483, 797);
        frame.setMinimumSize(new Dimension(1483, 797));
        frame.setResizable(false); // Hacer que la ventana no sea redimensionable
        frame.setLocationRelativeTo(null); // Centrar el JFrame

        frame.setIconImage(new ImageIcon(MainPagina.class.getResource("/TiempoEnAndalucia/imagenes/LOGO-195.png")).getImage());

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;

        int windowWidth = 1483; // Ancho de la ventana
        int windowHeight = 797; // Alto de la ventana

        int x = (screenWidth - windowWidth) / 2; // Coordenada x para centrar la ventana
        int y = (screenHeight - windowHeight) / 2; // Coordenada y para centrar la ventana

        frame.setLocation(x, y);

        // Pasar el título al constructor de Banner
        Banner banner = new Banner(frame, titulo);
        JPanel bannerPanel = banner.getBanner();

        frame.add(bannerPanel, BorderLayout.NORTH);

        JPanel panelWhite = new JPanel();
        panelWhite.setBackground(Color.WHITE);
        panelWhite.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;

        JLabel andaluciaLabel = new JLabel(new ImageIcon(MainPagina.class.getResource("/TiempoEnAndalucia/imagenes/andalucia_557.png")));
        JLayeredPane andaluciaPane = new JLayeredPane();
        andaluciaPane.setLayout(null);
        andaluciaPane.setPreferredSize(new Dimension(983, 557));
        andaluciaLabel.setBounds(0, 0, 983, 557);
        andaluciaPane.add(andaluciaLabel, JLayeredPane.DEFAULT_LAYER);

        int[][] posiciones = {
                {350, 130, 200, 50}, // Córdoba
                {200, 230, 200, 50}, // Sevilla
                {550, 300, 200, 50}, // Granada
                {390, 370, 200, 50}, // Málaga
                {750, 320, 200, 50}, // Almería
                {180, 400, 200, 50}, // Cádiz
                {550, 130, 200, 50}, // Jaén
                {15, 210, 200, 50}  // Huelva
        };
        String[] provincias = {"CÓRDOBA", "SEVILLA", "GRANADA", "MÁLAGA", "ALMERÍA", "CÁDIZ", "JAÉN", "HUELVA"};
        ArrayList<JLabel> labels = new ArrayList<>();
        for (int i = 0; i < provincias.length; i++) {
            String minus = provincias[i].toLowerCase();
            String capital = minus.substring(0, 1).toUpperCase() + minus.substring(1);
            JLabel label = new JLabel(provincias[i], JLabel.CENTER);
            label.setFont(new Font("Arial", Font.BOLD, 30));
            label.setForeground(Color.BLACK);
            label.setBounds(posiciones[i][0], posiciones[i][1], posiciones[i][2], posiciones[i][3]);
            label.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    abrirPronosticoTiempo(capital, frame);
                }
            });
            andaluciaPane.add(label, JLayeredPane.PALETTE_LAYER);
        }

        panelWhite.add(andaluciaPane, gbc);

        frame.add(panelWhite, BorderLayout.CENTER);

        frame.setVisible(true);
    }

    private static void abrirPronosticoTiempo(String provincia, JFrame frameActual) {
        ResourceBundle bundle = InternationalizationManager.getResourceBundle();
        frameActual.dispose(); // Cerrar la ventana actual
        if ((bundle.getString("TituloAire")).equals(tituloBanner)) {
            SwingUtilities.invokeLater(() -> new Aire(provincia).setVisible(true)); // Muestra la calidad del aire
        } else if((bundle.getString("TituloTiempo")).equals(tituloBanner))  {
            SwingUtilities.invokeLater(() -> new PronosticoTiempo(provincia).setVisible(true)); // Muestra el pronóstico del tiempo
        } else {
            System.out.println("Nada");
        }
    }
}
