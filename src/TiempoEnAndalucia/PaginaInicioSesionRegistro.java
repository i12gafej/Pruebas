package TiempoEnAndalucia;

import TiempoEnAndalucia.Controllers.InternationalizationManager;
import TiempoEnAndalucia.Controllers.JSONManager;
import TiempoEnAndalucia.Controllers.SessionManager;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class PaginaInicioSesionRegistro {
    private JFrame frame;
    private ArrayList<Usuario> usuariosRegistrados;

    // Ruta del archivo JSON que almacenará los datos de los usuarios
    private static final String ARCHIVO_USUARIOS = "usuarios.json";

    public static void main(String[] args) {
        PaginaInicioSesionRegistro app = new PaginaInicioSesionRegistro();
        app.iniciarInterfaz();
    }

    public void iniciarInterfaz() {
        ResourceBundle bundle = InternationalizationManager.getResourceBundle();
        // Inicializar la lista de usuarios registrados desde el archivo JSON
        usuariosRegistrados = JSONManager.cargarUsuariosDesdeJSON();

        frame = new JFrame(bundle.getString("TituloSesion"));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1483, 797);
        frame.setMinimumSize(new Dimension(1483, 797));
        frame.setLayout(new BorderLayout());
        frame.setResizable(false);

        frame.setIconImage(new ImageIcon(MainPagina.class.getResource("/TiempoEnAndalucia/imagenes/LOGO-195.png")).getImage());

        //Centrar la ventana en el centro de la pantalla
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;

        int windowWidth = 1483; // Ancho de la ventana
        int windowHeight = 797; // Alto de la ventana

        int x = (screenWidth - windowWidth) / 2; // Coordenada x para centrar la ventana
        int y = (screenHeight - windowHeight) / 2; // Coordenada y para centrar la ventana

        frame.setLocation(x, y);

        // Crear una instancia de Banner y obtener el panel del banner
        Banner banner = new Banner(frame, bundle.getString("InicioSesion"));
        JPanel bannerPanel = banner.getBanner();

        // Añadir el banner a la parte superior del marco
        frame.add(bannerPanel, BorderLayout.NORTH);

        // Panel principal (puedes añadir más contenido aquí)
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(2, 2, 2, 2);

        // Verificar si hay un usuario conectado
        if (SessionManager.getInstance().isLoggedIn()) {
            String loggedInUser = SessionManager.getInstance().getLoggedInUser().getUsuario();
            //mainPanel.add(new JLabel("Bienvenido, " + loggedInUser, JLabel.CENTER), BorderLayout.CENTER);
        } else {
            //mainPanel.add(new JLabel("No has iniciado sesión", JLabel.CENTER), BorderLayout.CENTER);
        }

        // Configurar banner dentro del panel principal
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.weightx = 1.0;
        gbc.weighty = 0.25;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(0, 0, 0, 0);
        mainPanel.add(bannerPanel, gbc);

        // Crear panel de inicio de sesión
        JPanel panelInicioSesion = crearPanelInicioSesion();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.weightx = 0.5;
        gbc.weighty = 0.75;
        gbc.fill = GridBagConstraints.BOTH;
        mainPanel.add(panelInicioSesion, gbc);

        // Crear panel de registro
        JPanel panelRegistro = crearPanelRegistro();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.weightx = 0.5;
        gbc.weighty = 0.75;
        gbc.fill = GridBagConstraints.BOTH;
        mainPanel.add(panelRegistro, gbc);

        // Mostrar el frame
        frame.add(mainPanel, BorderLayout.CENTER);
        frame.setVisible(true);

    }

    private JPanel crearPanelInicioSesion() {
        ResourceBundle bundle = InternationalizationManager.getResourceBundle();
        JPanel panelInicioSesion = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel inicioSesionLabel = new JLabel(bundle.getString("InicioSesion"));
        inicioSesionLabel.setFont(new Font("Liberation Sans", Font.BOLD, 32));

        // Crear componentes para inicio de sesión
        JLabel usuarioLabel = new JLabel(bundle.getString("Usuario")+":");
        usuarioLabel.setFont(new Font("Liberation Sans", Font.PLAIN, 22));
        JTextField usuarioField = new JTextField(24);
        JLabel contrasenaLabel = new JLabel(bundle.getString("Contrasena")+":");
        contrasenaLabel.setFont(new Font("Liberation Sans", Font.PLAIN, 22));
        JPasswordField contrasenaField = new JPasswordField(24);
        JButton enviarButton = new JButton(bundle.getString("BotonEnviar"));

        // Agregar ActionListener al botón de enviar para iniciar sesión
        enviarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usuario = usuarioField.getText();
                String contrasena = new String(contrasenaField.getPassword());

                // Lógica de verificación de usuario y contraseña
                if (JSONManager.verificarCredenciales(usuario, contrasena)) {
                    Usuario aux = new Usuario(usuario, contrasena);
                    SessionManager.getInstance().login(aux);
                    JOptionPane.showMessageDialog(frame, bundle.getString("InicioSesion") + bundle.getString("ExitosoComo") + " " + usuario);

                    // Redirigir a la siguiente página
                    frame.dispose(); // Cierra la ventana actual
                    SwingUtilities.invokeLater(() -> MainPagina.createAndShowGUI(bundle, bundle.getString("TituloTiempo"), bundle.getString("SubtituloBanner")));
                    /*MainPagina siguientePagina = new MainPagina(); // Crea una instancia de la siguiente página o clase
                    String[] args = new String[0];
                    siguientePagina.main(args); // Muestra la siguiente página o clase*/
                } else {
                    JOptionPane.showMessageDialog(frame, bundle.getString("ErrorUsuarioContra"));
                }
            }
        });

        // Configurar componentes dentro del panel
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.NORTH;
        panelInicioSesion.add(inicioSesionLabel, gbc);

        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panelInicioSesion.add(usuarioLabel, gbc);

        gbc.gridy = 2;
        panelInicioSesion.add(usuarioField, gbc);

        gbc.gridy = 3;
        panelInicioSesion.add(contrasenaLabel, gbc);

        gbc.gridy = 4;
        panelInicioSesion.add(contrasenaField, gbc);

        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.CENTER;
        panelInicioSesion.add(enviarButton, gbc);

        // Cargar las imágenes para el radio button
        Icon seleccionadoIcon = new ImageIcon("/TiempoEnAndalucia/imagenes/ojo.png");
        Icon noSeleccionadoIcon = new ImageIcon("/TiempoEnAndalucia/imagenes/ojocerrado.png");

        // Crear un JRadioButton con el icono
        JRadioButton radioButton = new JRadioButton();
        //radioButton.setIcon(noSeleccionadoIcon);
        //radioButton.setSelectedIcon(seleccionadoIcon);

        // Asignar el comportamiento para alternar entre las imágenes
        radioButton.addActionListener(e -> {
            if (radioButton.isSelected()) {
                //radioButton.setIcon(seleccionadoIcon);
                contrasenaField.setEchoChar((char) 0); // Mostrar texto
            } else {
                //radioButton.setIcon(noSeleccionadoIcon);
                contrasenaField.setEchoChar('\u2022'); // Ocultar texto (carácter de viñeta)
            }
        });

        // Añadir el JRadioButton al panel después del cuadro de texto de contraseña
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.WEST;
        panelInicioSesion.add(radioButton, gbc);

        return panelInicioSesion;
    }

    private JPanel crearPanelRegistro() {
        ResourceBundle bundle = InternationalizationManager.getResourceBundle();
        JPanel panelRegistro = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel registroLabel = new JLabel(bundle.getString("Registro"));
        registroLabel.setFont(new Font("Liberation Sans", Font.BOLD, 32));

        // Crear componentes para registro
        JLabel usuarioLabel = new JLabel(bundle.getString("Usuario")+":");
        usuarioLabel.setFont(new Font("Liberation Sans", Font.PLAIN, 22));
        JTextField usuarioField = new JTextField(24);
        JLabel correoLabel = new JLabel(bundle.getString("Correo")+":");
        correoLabel.setFont(new Font("Liberation Sans", Font.PLAIN, 22));
        JTextField correoField = new JTextField(24);
        JLabel contrasenaLabel = new JLabel(bundle.getString("Contrasena")+":");
        contrasenaLabel.setFont(new Font("Liberation Sans", Font.PLAIN, 22));
        JPasswordField contrasenaField = new JPasswordField(24);
        JLabel confirmarContrasenaLabel = new JLabel(bundle.getString("ConfContrasena")+":");
        confirmarContrasenaLabel.setFont(new Font("Liberation Sans", Font.PLAIN, 22));
        JPasswordField confirmarContrasenaField = new JPasswordField(24);
        JButton enviarButton = new JButton(bundle.getString("BotonRegistrar"));

        enviarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usuario = usuarioField.getText();
                String correo = correoField.getText(); // Obtener el correo electrónico ingresado
                String contrasena = new String(contrasenaField.getPassword());
                String confirmarContrasena = new String(confirmarContrasenaField.getPassword());

                // Lógica de verificación de los datos de registro
                if (contrasena.equals(confirmarContrasena) && !usuario.isEmpty() && !correo.isEmpty()) {
                    // Verificar si el correo electrónico tiene el formato correcto
                    if (JSONManager.esCorreoValido(correo)) {
                        if (JSONManager.registrarUsuario(usuario, correo, contrasena)) {
                            JOptionPane.showMessageDialog(frame, bundle.getString("RegistroExito"));
                        } else {
                            JOptionPane.showMessageDialog(frame, bundle.getString("ErrorUsuarioReg"));
                        }
                    } else {
                        JOptionPane.showMessageDialog(frame, bundle.getString("ErrorCorreo"));
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, bundle.getString("ErrorContrasenas"));
                }
            }
        });

        // Configurar componentes dentro del panel
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.NORTH;
        panelRegistro.add(registroLabel, gbc);

        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panelRegistro.add(usuarioLabel, gbc);

        gbc.gridy = 2;
        panelRegistro.add(usuarioField, gbc);

        gbc.gridy = 3;
        panelRegistro.add(correoLabel, gbc);

        gbc.gridy = 4;
        panelRegistro.add(correoField, gbc);

        gbc.gridy = 5;
        panelRegistro.add(contrasenaLabel, gbc);

        gbc.gridy = 6;
        panelRegistro.add(contrasenaField, gbc);

        gbc.gridy = 7;
        panelRegistro.add(confirmarContrasenaLabel, gbc);

        gbc.gridy = 8;
        panelRegistro.add(confirmarContrasenaField, gbc);

        gbc.gridy = 9;
        gbc.anchor = GridBagConstraints.CENTER;
        panelRegistro.add(enviarButton, gbc);

        // Cargar las imágenes para el radio button
        Icon seleccionadoIcon = new ImageIcon("/TiempoEnAndalucia/imagenes/ojo.png");
        Icon noSeleccionadoIcon = new ImageIcon("/TiempoEnAndalucia/imagenes/ojocerrado.png");

        // Crear un JRadioButton con el icono
        JRadioButton radioButton = new JRadioButton();
        //radioButton.setIcon(noSeleccionadoIcon);
        //radioButton.setSelectedIcon(seleccionadoIcon);

        // Asignar el comportamiento para alternar entre las imágenes
        radioButton.addActionListener(e -> {
            if (radioButton.isSelected()) {
                //radioButton.setIcon(seleccionadoIcon);
                contrasenaField.setEchoChar((char) 0); // Mostrar texto
            } else {
                //radioButton.setIcon(noSeleccionadoIcon);
                contrasenaField.setEchoChar('\u2022'); // Ocultar texto (carácter de viñeta)
            }
        });

        JRadioButton radioButton2 = new JRadioButton();
        //radioButton2.setIcon(noSeleccionadoIcon);
        //radioButton2.setSelectedIcon(seleccionadoIcon)

        radioButton2.addActionListener(e -> {
            if (radioButton2.isSelected()) {
                //radioButton2.setIcon(seleccionadoIcon);
                confirmarContrasenaField.setEchoChar((char) 0); // Mostrar texto
            } else {
                //radioButton2.setIcon(noSeleccionadoIcon);
                confirmarContrasenaField.setEchoChar('\u2022'); // Ocultar texto (carácter de viñeta)
            }
        });

        gbc.gridx = 1;
        gbc.gridy = 6;
        panelRegistro.add(radioButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 8;
        panelRegistro.add(radioButton2, gbc);

        return panelRegistro;
    }
}
