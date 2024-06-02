package TiempoEnAndalucia.Controllers;

import TiempoEnAndalucia.Usuario;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JsonHandler {
    private static final Logger logger = Logger.getLogger(JsonHandler.class.getName());
    private static final String nombreArchivo = "valoraciones.json";

    public static void escribirUsuario(Usuario usuario) {
        JSONObject rootObject;

        // Intenta leer el archivo existente o inicializa un nuevo JSONObject si no existe
        try {
            File file = new File(nombreArchivo);
            if (file.exists()) {
                String contenido = new String(Files.readAllBytes(Paths.get(nombreArchivo)));
                if (!contenido.isEmpty()){rootObject = new JSONObject(contenido);}
                else{rootObject = new JSONObject("{}");}

            } else {
                rootObject = new JSONObject();
            }

            JSONObject usuarioObject = new JSONObject();
            usuarioObject.put("Valoracion", usuario.getValoracion());
            usuarioObject.put("Texto", usuario.getComentario());

            // Insertar o actualizar el usuario en el JSONObject raíz
            rootObject.put(usuario.getUsuario(), usuarioObject);

            // Escribir todo de nuevo al archivo JSON
            try (Writer writer = new FileWriter(nombreArchivo, false)) {
                writer.write(rootObject.toString(2));  // Indentación para facilidad de lectura
            }
        } catch (IOException | JSONException e) {
            logger.log(Level.SEVERE, "Error al escribir en el archivo JSON", e);
        }
    }

    public static List<Usuario> leerUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        try {
            String contenido = new String(Files.readAllBytes(Paths.get(nombreArchivo)));
            JSONObject jsonObject = new JSONObject(contenido);

            for (String nombre : jsonObject.keySet()) {
                JSONObject usuarioObject = jsonObject.getJSONObject(nombre);
                int valoracion = usuarioObject.getInt("Valoracion");
                String texto = usuarioObject.getString("Texto");
                usuarios.add(new Usuario(nombre,"", valoracion, texto));
            }
        } catch (IOException | JSONException e) {
            logger.log(Level.SEVERE, "Error al leer el archivo JSON", e);
        }
        return usuarios;
    }
}
