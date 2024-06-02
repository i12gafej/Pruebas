package TiempoEnAndalucia;

import TiempoEnAndalucia.Controllers.InternationalizationManager;

import javax.swing.*;
import java.awt.*;
import java.util.Locale;
import java.util.ResourceBundle;

public class Banner {

    private JPopupMenu popupMenu;
    private JFrame parentFrame;
    private String tituloBanner;


    public Banner(JFrame parentFrame, String tituloBanner) {
        this.parentFrame = parentFrame;
        this.tituloBanner = tituloBanner;
        initPopupMenu();
    }

    private void initPopupMenu() {
        popupMenu = new JPopupMenu();
        ResourceBundle bundle = InternationalizationManager.getResourceBundle();
        String[] opciones = { bundle.getString("InicioSesion"), bundle.getString("ElTiempo"), bundle.getString("CalidadAire"), bundle.getString("Contacto"), bundle.getString("CambiarIdioma") };
        for (String opcion : opciones) {
            JMenuItem menuItem = new JMenuItem(opcion);
            menuItem.setFont(new Font("Arial", Font.BOLD, 18));
            menuItem.setForeground(Color.WHITE);
            menuItem.setBackground(new Color(0, 102, 188));
            menuItem.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    menuItem.setForeground(Color.WHITE);
                }

                @Override
                public void mouseExited(java.awt.event.MouseEvent evt) {
                    menuItem.setForeground(Color.WHITE);
                }
            });

            menuItem.addActionListener(e -> {
                String command = e.getActionCommand();
                ResourceBundle currentBundle = InternationalizationManager.getResourceBundle();
                if (command.equals(currentBundle.getString("ElTiempo"))) {
                    parentFrame.dispose();
                    SwingUtilities.invokeLater(() -> MainPagina.createAndShowGUI(currentBundle, currentBundle.getString("TituloTiempo"), currentBundle.getString("SubtituloBanner")));
                } else if (command.equals(currentBundle.getString("CalidadAire"))) {
                    parentFrame.dispose();
                    SwingUtilities.invokeLater(() -> MainPagina.createAndShowGUI(currentBundle, currentBundle.getString("TituloAire"), currentBundle.getString("SubtituloBanner")));
                } else if (command.equals(currentBundle.getString("Contacto"))) {
                    parentFrame.dispose();
                    Contacto.main(new String[0]);
                } else if (command.equals(currentBundle.getString("InicioSesion"))) {
                    parentFrame.dispose();
                    PaginaInicioSesionRegistro.main(new String[0]);
                } else if (command.equals(currentBundle.getString("CambiarIdioma"))) {
                    ResourceBundle newBundle = InternationalizationManager.cambiarIdioma();
                    parentFrame.dispose();
                    SwingUtilities.invokeLater(() -> MainPagina.createAndShowGUI(newBundle, newBundle.getString("TituloBanner"), newBundle.getString("SubtituloBanner"))); // Ajusta los parámetros según sea necesario
                }
            });

            popupMenu.add(menuItem);
        }

        popupMenu.setBackground(new Color(0, 102, 188));
    }


    public JPanel getBanner() {
        ResourceBundle currentBundle = InternationalizationManager.getResourceBundle();

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setLayout(new BorderLayout());

        // Panel Azul
        JPanel panelBlue = new JPanel();
        panelBlue.setBackground(new Color(0, 102, 188));
        panelBlue.setLayout(new BorderLayout());

        // Panel Títulos
        JPanel panelTitulos = new JPanel();
        panelTitulos.setLayout(new BoxLayout(panelTitulos, BoxLayout.Y_AXIS)); // Usar BoxLayout en Y_AXIS para evitar huecos
        panelTitulos.setOpaque(false);
        panelTitulos.add(Box.createVerticalGlue()); // Añadir espacio flexible antes del título
        
        // Cambiar el título del banner según el títuloBanner
        String titulo = tituloBanner;
        JLabel tituloLabel = new JLabel(titulo, JLabel.CENTER);
        tituloLabel.setFont(new Font("Arial", Font.BOLD, 36));
        tituloLabel.setForeground(Color.WHITE);
        tituloLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Centrar el texto
        panelTitulos.add(tituloLabel);

        JLabel subtituloLabel = new JLabel(currentBundle.getString("SubtituloBanner"), JLabel.CENTER);
        subtituloLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        subtituloLabel.setForeground(Color.WHITE);
        subtituloLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Centrar el texto
        panelTitulos.add(subtituloLabel);
        panelTitulos.add(Box.createVerticalGlue());

        // Logo
        JLabel logoLabel = new JLabel(new ImageIcon(getClass().getResource("/TiempoEnAndalucia/imagenes/LOGO-195.png")));
        JPanel logoPanel = new JPanel(new BorderLayout());
        logoPanel.setOpaque(false);
        logoPanel.add(logoLabel, BorderLayout.CENTER);

        // Menú
        JLabel menuLabel = new JLabel(currentBundle.getString("BotonMenu"), JLabel.CENTER);
        menuLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        menuLabel.setForeground(Color.WHITE);
        JPanel menuPanel = new JPanel(new BorderLayout());
        menuPanel.setPreferredSize(new Dimension(150, 80)); // Hacer el panel de menú más ancho
        menuPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 0));
        menuPanel.setOpaque(false);
        menuPanel.add(menuLabel, BorderLayout.CENTER);
        menuPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                popupMenu.show(menuPanel, 0, menuPanel.getHeight());
            }
        });

        // Añadir elementos al panel azul
        panelBlue.add(logoPanel, BorderLayout.WEST);
        panelBlue.add(panelTitulos, BorderLayout.CENTER);
        panelBlue.add(menuPanel, BorderLayout.EAST);

        return panelBlue;
    }
}
