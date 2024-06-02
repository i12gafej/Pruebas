package TiempoEnAndalucia.Controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class InternationalizationManager {
    private static Locale currentLocale = new Locale("es", "ES");
    private static ResourceBundle resourceBundle = ResourceBundle.getBundle("bundle.Bundle", currentLocale);
    private static List<LocaleChangeListener> listeners = new ArrayList<>();

    public static void setLocale(Locale locale) {
        currentLocale = locale;
        resourceBundle = ResourceBundle.getBundle("bundle.Bundle", currentLocale);
        notifyListeners();
    }

    public static Locale getCurrentLocale() {
        return currentLocale;
    }

    public static void setBundle(Locale locale) {
        resourceBundle = ResourceBundle.getBundle("bundle/Bundle", locale);
    }

    public static ResourceBundle getResourceBundle() {
        return resourceBundle;
    }

    public static void addLocaleChangeListener(LocaleChangeListener listener) {
        listeners.add(listener);
    }

    public static void removeLocaleChangeListener(LocaleChangeListener listener) {
        listeners.remove(listener);
    }

    private static void notifyListeners() {
        for (LocaleChangeListener listener : listeners) {
            listener.localeChanged();
        }
    }

    public interface LocaleChangeListener {
        void localeChanged();
    }

    public static ResourceBundle cambiarIdioma() {
        Locale idiomaActual = getCurrentLocale();
        Locale nuevoIdioma = idiomaActual.equals(new Locale("es", "ES")) ? new Locale("en", "GB") : new Locale("es", "ES");
        setLocale(nuevoIdioma);
        return getResourceBundle();
    }
}

