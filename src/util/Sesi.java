package util;

import model.User;

public class Sesi {
    private static User currentUser;

    private Sesi() {
        // Private constructor to prevent instantiation
    }

    public static void setCurrentUser(User user) {
        currentUser = user;
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void clear() {
        currentUser = null;
    }
}
