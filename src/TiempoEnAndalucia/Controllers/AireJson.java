package TiempoEnAndalucia.Controllers;

import TiempoEnAndalucia.Modelo.O3;
import TiempoEnAndalucia.Modelo.Calima;
import TiempoEnAndalucia.Modelo.CiudadAire;
import TiempoEnAndalucia.*;
import TiempoEnAndalucia.Modelo.Polen;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AireJson {
    private static final Logger LOGGER = Logger.getLogger(AireJson.class.getName());

    public static List<CiudadAire> leerCalidadAire(String fileName) {
        List<CiudadAire> ciudades = new ArrayList<>();
        try (InputStream is = Files.newInputStream(Paths.get(fileName))) {
            JSONArray jsonArray = new JSONArray(new JSONTokener(is));

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject ciudadJson = jsonArray.getJSONObject(i);

                String nombreCiudad = ciudadJson.getString("ciudad");
                String fecha = ciudadJson.getString("fecha");
                String puntuacion = ciudadJson.getString("puntuacion");

                JSONObject polenJson = ciudadJson.getJSONObject("polen");
                int polenResultado = polenJson.getInt("resultado");
                String polenNivel = polenJson.getString("nivel");

                JSONObject o3Json = ciudadJson.getJSONObject("O3");
                int o3Resultado = o3Json.getInt("resultado");
                String o3Nivel = o3Json.getString("nivel");

                JSONObject calimaJson = ciudadJson.getJSONObject("Calima");
                int calimaResultado = calimaJson.getInt("resultado");
                String calimaNivel = calimaJson.getString("nivel");

                Polen polen = new Polen(polenResultado, polenNivel);
                O3 o3 = new O3(o3Resultado, o3Nivel);
                Calima calima = new Calima(calimaResultado, calimaNivel);

                ciudades.add(new CiudadAire(nombreCiudad, fecha, puntuacion, polen, o3, calima));
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error al leer el archivo JSON", e);
        }
        return ciudades;
    }

    public static void main(String[] args) {
        List<CiudadAire> ciudades = leerCalidadAire("calidad_aire_andalucia.json");
        for (CiudadAire ciudad : ciudades) {
            System.out.println("Ciudad: " + ciudad.getNombre());
            System.out.println("  Fecha: " + ciudad.getFecha());
            System.out.println("  Puntuación: " + ciudad.getPuntuacion());
            System.out.println("  Polen: " + ciudad.getPolen().getResultado() + " (" + ciudad.getPolen().getNivel() + ")");
            System.out.println("  O3: " + ciudad.getO3().getResultado() + " (" + ciudad.getO3().getNivel() + ")");
            System.out.println("  Calima: " + ciudad.getCalima().getResultado() + " (" + ciudad.getCalima().getNivel() + ")");
        }
    }
}







