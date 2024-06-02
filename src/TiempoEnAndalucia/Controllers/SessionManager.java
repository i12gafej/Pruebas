package TiempoEnAndalucia.Controllers;
//Patron Singleton

import TiempoEnAndalucia.Usuario;

public class SessionManager {
    // Variable estática que contiene la única instancia de SessionManager
    private static SessionManager instance;

    // Variable que almacena el usuario que ha iniciado sesión
    private Usuario loggedInUser;

    // Constructor privado para evitar instanciación
    private SessionManager() {}

    // Método para obtener la instancia única de SessionManager
    public static SessionManager getInstance() {
        if (instance == null) {
            instance = new SessionManager();
        }
        return instance;
    }

    // Método para establecer el usuario que ha iniciado sesión
    public void login(Usuario user) {
        loggedInUser = user;
    }

    // Método para cerrar la sesión
    public void logout() {
        loggedInUser = null;
    }

    // Método para obtener el usuario que ha iniciado sesión
    public Usuario getLoggedInUser() {
        return loggedInUser;
    }

    // Método para verificar si hay un usuario conectado
    public boolean isLoggedIn() {
        return loggedInUser != null;
    }
}
