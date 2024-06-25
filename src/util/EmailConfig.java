package util;

import java.util.Properties;

public class EmailConfig {
    private static final String HOST = "smtp.gmail.com";
    private static final String PORT = "587"; // Port yang umum digunakan untuk SMTP
    private static final String USERNAME = "";
    private static final String PASSWORD = "";

    public static Properties getEmailProperties() {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth","true");
        properties.put("mail.smtp.starttls.enable","true");
        properties.put("mail.smtp.host",HOST);
        properties.put("mail.smtp.port",PORT);
        return properties;
    }

    public static String getUsername() {
        return USERNAME;
    }

    public static String getPassword() {
        return PASSWORD;
    }
}
