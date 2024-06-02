package TiempoEnAndalucia.Controllers;

import TiempoEnAndalucia.Modelo.Pronostico;
import TiempoEnAndalucia.Modelo.Ciudad;
import TiempoEnAndalucia.Modelo.PronosticosHoras;
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

public class TiempoJson {
    private static final Logger LOGGER = Logger.getLogger(TiempoJson.class.getName());

    public static List<Ciudad> leerPronostico(String fileName) {
        List<Ciudad> ciudades = new ArrayList<>();
        try (InputStream is = Files.newInputStream(Paths.get(fileName))) {
            JSONObject json = new JSONObject(new JSONTokener(is));

            for (String ciudadNombre : json.keySet()) {
                JSONObject ciudadJson = json.getJSONObject(ciudadNombre);
                String nombreCiudad = ciudadJson.getString("ciudad");
                JSONArray pronosticoArray = ciudadJson.getJSONArray("pronostico");
                List<Pronostico> pronosticos = new ArrayList<>();

                for (int i = 0; i < pronosticoArray.length(); i++) {
                    JSONObject pronosticoJson = pronosticoArray.getJSONObject(i);

                    String diaSemana = pronosticoJson.getString("dia_semana");
                    int numeroDia = pronosticoJson.getInt("numero_dia");
                    String mes = pronosticoJson.getString("mes");
                    String cielo = pronosticoJson.getString("cielo");
                    int maximaT = pronosticoJson.getInt("maxima_t");
                    int minimaT = pronosticoJson.getInt("minima_t");
                    String viento = pronosticoJson.getString("viento");
                    String rangoVelocidadViento = pronosticoJson.getString("rango_velocidad_viento");

                    JSONArray diaPronosticoArray = pronosticoJson.getJSONArray("dia_pronostico");
                    List<PronosticosHoras> rangos = new ArrayList<>();
                    for (int j = 0; j < diaPronosticoArray.length(); j++) {
                        JSONObject rangoJson = diaPronosticoArray.getJSONObject(j);

                        String hora = rangoJson.getString("hora");
                        String cieloHora = rangoJson.getString("cielo");
                        String descripcionCielo = rangoJson.getString("descripcion_cielo");
                        int sensacionTermica = rangoJson.getInt("sensacion_termica");
                        String vientoHora = rangoJson.getString("viento");
                        int velocidadViento = rangoJson.getInt("velocidad_viento");
                        double mmLluvia = rangoJson.getDouble("mm_lluvia");
                        int probLluvia = rangoJson.getInt("prob_lluvia");

                        rangos.add(new PronosticosHoras(hora, cieloHora, descripcionCielo, sensacionTermica, vientoHora, velocidadViento, mmLluvia, probLluvia));
                    }

                    pronosticos.add(new Pronostico(diaSemana, numeroDia, mes, cielo, maximaT, minimaT, viento, rangoVelocidadViento, rangos));
                }

                ciudades.add(new Ciudad(nombreCiudad, pronosticos));
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error al leer el archivo JSON", e);
        }
        return ciudades;
    }

    public static void main(String[] args) {
        List<Ciudad> ciudades = leerPronostico("pronostico_andalucia.json");
        for (Ciudad ciudad : ciudades) {
            System.out.println("Ciudad: " + ciudad.getNombre());
            for (Pronostico pronostico : ciudad.getPronosticos()) {
                System.out.println("  Día: " + pronostico.getDiaSemana() + ", " + pronostico.getNumeroDia() + " de " + pronostico.getMes());
                System.out.println("  Cielo: " + pronostico.getCielo() + ", Máxima: " + pronostico.getMaximaT() + "º, Mínima: " + pronostico.getMinimaT() + "º, Viento: " + pronostico.getViento() + ", Rango Velocidad Viento: " + pronostico.getRangoVelocidadViento());
                for (PronosticosHoras rango : pronostico.getPronosticosHoras()) {
                    System.out.println("    Hora: " + rango.getHora() + ", Cielo: " + rango.getCielo() + " (" + rango.getDescripcionCielo() + "), Sensación Térmica: " + rango.getSensacionTermica() + "º");
                    System.out.println("    Viento: " + rango.getViento() + " a " + rango.getVelocidadViento() + " km/h, Lluvia: " + rango.getMmLluvia() + " mm, Probabilidad de lluvia: " + rango.getProbLluvia() + "%");
                }
            }
        }
    }
}

