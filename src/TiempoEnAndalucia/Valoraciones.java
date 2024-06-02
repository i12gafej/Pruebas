package TiempoEnAndalucia;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Locale;
import java.util.ResourceBundle;

import TiempoEnAndalucia.Controllers.InternationalizationManager;
import TiempoEnAndalucia.Controllers.JsonHandler;
import TiempoEnAndalucia.Controllers.SessionManager;

public class Valoraciones extends JFrame {
    private int valoracion = 0; // Valor inicial de la valoración

    public Valoraciones() {
        initComponents();
    }

    private void initComponents() {
        ResourceBundle bundle = InternationalizationManager.getResourceBundle();
        setTitle(bundle.getString("Valoraciones"));
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

        JLabel labelTitulo = new JLabel(bundle.getString("TituloValoraciones"), JLabel.CENTER);
        labelTitulo.setFont(new Font("Segoe UI", Font.BOLD, 64));
        labelTitulo.setBounds(505, 20, 500, 80);
        panelArriba.add(labelTitulo);

        JLayeredPane panelVolver = new JLayeredPane();
        panelVolver.setBounds(1300, 20, 200, 100);
        panelVolver.setBackground(new Color(204, 204, 204));
        panelVolver.setOpaque(true);

        JButton volverButton = new JButton(bundle.getString("BotonVolver"));
        volverButton.setFont(new Font("Segoe UI", Font.BOLD, 24));
        volverButton.setBackground(new Color(204, 204, 204));
        volverButton.setForeground(Color.white);
        volverButton.setBounds(1220, 30, 200, 50);
        volverButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose(); // Cerrar la ventana actual
                Contacto.main(new String[0]);
            }
        });
        panelArriba.add(volverButton);

        // Panel izquierda
        JLayeredPane panelIzquierda = new JLayeredPane();
        panelIzquierda.setBounds(125, 170, 1300, 574);
        panelIzquierda.setOpaque(false);

        JLabel labelTelef = new JLabel(bundle.getString("Nota"), JLabel.LEFT);
        labelTelef.setFont(new Font("Segoe UI", Font.BOLD, 29));
        labelTelef.setBounds(0, 0, 506, 45);
        panelIzquierda.add(labelTelef);

        JLayeredPane panelValoraciones = new JLayeredPane();
        panelValoraciones.setBounds(30, 50, 400, 60);
        panelIzquierda.add(panelValoraciones);

        // Añadir estrellas al panel de valoraciones
        for (int i = 0; i < 5; i++) {
            JLabel estrellaLabel = new JLabel(new ImageIcon(getClass().getResource("/TiempoEnAndalucia/imagenes/estrella_vacia.png")));
            estrellaLabel.setBounds(i * 80, 0, 60, 60);
            int valorEstrella = i + 1;
            estrellaLabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    setValoracion(valorEstrella, panelValoraciones);
                }
            });
            panelValoraciones.add(estrellaLabel);
        }

        JLabel labelCorreo = new JLabel(bundle.getString("Coment"), JLabel.LEFT);
        labelCorreo.setFont(new Font("Segoe UI", Font.BOLD, 29));
        labelCorreo.setBounds(0, 150, 506, 45);
        panelIzquierda.add(labelCorreo);

        JTextArea textAreaComentario = new JTextArea();
        textAreaComentario.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        textAreaComentario.setText(bundle.getString("EscribaComent"));
        JScrollPane scrollComentario = new JScrollPane(textAreaComentario);
        scrollComentario.setBounds(0, 220, 506, 195);
        scrollComentario.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollComentario.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        panelIzquierda.add(scrollComentario);

        JButton botonEnviar = new JButton(bundle.getString("BotonEnviar"));
        botonEnviar.setFont(new Font("Segoe UI", Font.BOLD, 24));
        botonEnviar.setBackground(new Color(243, 243, 243));
        botonEnviar.setBounds(0, 430, 206, 61);
        panelIzquierda.add(botonEnviar);
        botonEnviar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Usuario loggedInUser = null;
                String comentario = textAreaComentario.getText();
                if (TiempoEnAndalucia.SessionManager.getInstance().isLoggedIn()) {
                    loggedInUser = SessionManager.getInstance().getLoggedInUser();
                } else {
                    JOptionPane.showMessageDialog(null, bundle.getString("ErrorLogueado"), "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (comentario.isEmpty()) {
                    JOptionPane.showMessageDialog(null, bundle.getString("ErrorIngresaComent"), "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                // Crear un nuevo objeto Usuario con la valoración y el comentario
                Usuario usuario = new Usuario(loggedInUser.getUsuario(), loggedInUser.getUsuario(), valoracion, comentario);
                valoracion = 0;
                // Escribir el usuario en el archivo JSON
                JsonHandler.escribirUsuario(usuario);
                JOptionPane.showMessageDialog(null, bundle.getString("ExitoValoracion")+ usuario.getUsuario());
            }
        });

        // Panel derecha
        JLayeredPane panelDerecha = new JLayeredPane();
        panelDerecha.setBounds(700, 170, 700, 500);
        panelDerecha.setOpaque(false);

        JScrollPane scrollPanelDerecha = new JScrollPane(crearPanelTextoNoEditable());
        scrollPanelDerecha.setBounds(0, 0, 700, 490);
        scrollPanelDerecha.setBorder(null);
        scrollPanelDerecha.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPanelDerecha.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPanelDerecha.setBackground(Color.WHITE);
        panelDerecha.add(scrollPanelDerecha);

        mainPanel.add(panelArriba);
        mainPanel.add(panelIzquierda);
        mainPanel.add(panelDerecha);

        add(mainPanel);
    }

    private void setValoracion(int valor, JLayeredPane panelValoraciones) {
        this.valoracion = valor;
        for (int i = 0; i < 5; i++) {
            JLabel estrellaLabel = (JLabel) panelValoraciones.getComponent(i);
            if (i < valor) {
                estrellaLabel.setIcon(new ImageIcon(getClass().getResource("/TiempoEnAndalucia/imagenes/estrella_llena.png")));
            } else {
                estrellaLabel.setIcon(new ImageIcon(getClass().getResource("/TiempoEnAndalucia/imagenes/estrella_vacia.png")));
            }
        }
    }

    private JPanel crearPanelTextoNoEditable() {
        ResourceBundle bundle = InternationalizationManager.getResourceBundle();
        String estrellas = bundle.getString("Estrellas");
        JPanel panelTextoNoEditable = new JPanel(new GridBagLayout());
        panelTextoNoEditable.setBorder(null); // Eliminar borde del panel

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Crear un área de texto no editable
        JTextPane textPane = new JTextPane();
        textPane.setContentType("text/html");
        textPane.setOpaque(false);
        textPane.setEditable(false);
        textPane.setBorder(null); // Eliminar borde del JTextPane

        // Leer usuarios y añadir al texto
        StringBuilder texto = new StringBuilder("<html><body style='font-family: Liberation Sans; font-size:18px;'>");
        for (Usuario usuario : JsonHandler.leerUsuarios()) {
            texto.append("<b>").append(usuario.getUsuario()).append(", ")
                    .append(usuario.getValoracion()).append(" ").append(estrellas).append("</b><br/><br/>");
            texto.append(usuario.getComentario()).append("<br/><br/>");
        }
        texto.append("</body></html>");
        textPane.setText(texto.toString());

        // Crear JScrollPane y eliminar su borde
        JScrollPane scrollPane = new JScrollPane(textPane);
        scrollPane.setBorder(null); // Eliminar borde del JScrollPane

        // Añadir el JScrollPane al panel
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        panelTextoNoEditable.add(scrollPane, gbc);

        return panelTextoNoEditable;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Valoraciones().setVisible(true));
    }
}

