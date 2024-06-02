package TiempoEnAndalucia.Controllers;

import TiempoEnAndalucia.Usuario;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

//Librerías de expresiones regulares
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class JSONManager {
    private static final String ARCHIVO_USUARIOS = "usuarios.json";
    private static ArrayList<Usuario> usuariosRegistrados;
    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    private static final Pattern pattern = Pattern.compile(EMAIL_PATTERN);

    // Método para validar el formato del correo electrónico
    public static boolean esCorreoValido(String correo) {
        Matcher matcher = pattern.matcher(correo);
        return matcher.matches();
    }
    public static ArrayList<Usuario> getUsuariosRegistrados() {
        // Cargar usuarios solo si la lista aún no ha sido cargada
        if (usuariosRegistrados == null) {
            usuariosRegistrados = cargarUsuariosDesdeJSON();
        }
        return usuariosRegistrados;
    }

    public static boolean verificarCredenciales(String nombre, String contrasena) {
        ArrayList<Usuario> usuarios = getUsuariosRegistrados();
        for (Usuario usuario : usuarios) {
            if (usuario.getUsuario().equals(nombre) && usuario.getContrasena().equals(contrasena)) {
                return true;
            }
        }
        return false;
    }

    public static boolean registrarUsuario(String nombre, String correo, String contrasena) {
        ArrayList<Usuario> usuarios = getUsuariosRegistrados();
        for (Usuario usuario : usuarios) {
            if (usuario.getUsuario().equals(nombre)) {
                return false;
            }
        }

        Usuario nuevoUsuario = new Usuario(nombre, correo, contrasena);
        usuarios.add(nuevoUsuario);
        guardarUsuariosEnJSON();
        return true;
    }

    public static ArrayList<Usuario> cargarUsuariosDesdeJSON() {
        ArrayList<Usuario> usuarios = new ArrayList<>();

        try {
            String contenido = new String(Files.readAllBytes(Paths.get(ARCHIVO_USUARIOS)));
            JSONArray jsonArray = new JSONArray(contenido);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String nombre = jsonObject.getString("nombre");
                String correo = jsonObject.getString("correo");
                String contrasena = jsonObject.getString("contrasena");

                Usuario usuario = new Usuario(nombre, correo, contrasena);
                usuarios.add(usuario);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return usuarios;
    }

    private static void guardarUsuariosEnJSON() {
        JSONArray jsonArray = new JSONArray();

        for (Usuario usuario : usuariosRegistrados) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("nombre", usuario.getUsuario());
            jsonObject.put("correo", usuario.getCorreo());
            jsonObject.put("contrasena", usuario.getContrasena());

            jsonArray.put(jsonObject);
        }

        try {
            Files.write(Paths.get(ARCHIVO_USUARIOS), jsonArray.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

