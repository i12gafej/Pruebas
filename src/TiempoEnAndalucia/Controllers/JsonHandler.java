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
    private static final String nombreArchivo_valoraciones = "valoraciones.json";
    private static final String nombreArchivo_contactanos = "contactanos.json";


    public static void escribirUsuario(Usuario usuario, int tipo) {
        JSONObject rootObject;
        String nombreArchivo;
        if (tipo == 1){
            nombreArchivo  = nombreArchivo_valoraciones;
        }
        else{nombreArchivo = nombreArchivo_contactanos;}
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
            if (tipo == 1) {usuarioObject.put("Valoracion", usuario.getValoracion());}
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

    public static List<Usuario> leerUsuarios(int tipo) {
        List<Usuario> usuarios = new ArrayList<>();
        String nombreArchivo;
        if (tipo == 1){
            nombreArchivo  = nombreArchivo_valoraciones;
        }
        else{nombreArchivo = nombreArchivo_contactanos;}
        try {
            String contenido = new String(Files.readAllBytes(Paths.get(nombreArchivo)));
            JSONObject jsonObject = new JSONObject(contenido);

            for (String nombre : jsonObject.keySet()) {
                JSONObject usuarioObject = jsonObject.getJSONObject(nombre);
                int valoracion=0;
                if (tipo == 1) {valoracion = usuarioObject.getInt("Valoracion");}

                String texto = usuarioObject.getString("Texto");
                if (tipo == 1){usuarios.add(new Usuario(nombre,"", valoracion, texto));}
                else{usuarios.add(new Usuario(nombre,"", texto));
                }
            }
        } catch (IOException | JSONException e) {
            logger.log(Level.SEVERE, "Error al leer el archivo JSON", e);
        }
        return usuarios;
    }
}
