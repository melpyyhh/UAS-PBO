package util;

import model.User;
import javafx.concurrent.Task;
import javafx.scene.control.Alert;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class KlinikFacade {

    public static boolean validateDate(LocalDate date) {
        LocalDate today = LocalDate.now();
        if (date.isEqual(today) || date.isBefore(today)) {
            showAlert(Alert.AlertType.ERROR, "Submit Gagal", "Tanggal harus setelah hari ini.");
            return false;
        }
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        if (dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY) {
            showAlert(Alert.AlertType.ERROR, "Submit Gagal", "Klinik Libur di Hari Sabtu dan Minggu.");
            return false;
        }
        return true;
    }

    public static void sendEmail(User user, String nama, LocalDate tanggal, String jam, String keluhan) {
        String emailReceiver = user.getEmail();
        String toEmail = emailReceiver;
        String subject = "Pengingat Janji Temu";
        String body = "Ini adalah pengingat bahwa Anda telah berhasil membuat registrasi dengan detail sebagai berikut:\n" +
                      "Nama: " + nama + "\n" +
                      "Waktu Kedatangan: " + tanggal + " " + jam + "\n" +
                      "Keluhan: " + keluhan + "\n\n" +
                      "Silahkan datang ke klinik sesuai dengan waktu yang telah Anda pilih.";
        Task<Void> task = EmailSender.sendReminderEmail(toEmail, subject, body);

        task.setOnSucceeded(event -> {
            showAlert(Alert.AlertType.INFORMATION, "Email Berhasil dikirim", "Email Pengingat Berhasil dikirim!");
        });

        task.setOnFailed(event -> {
            showAlert(Alert.AlertType.ERROR, "Email Gagal dikirim", "Pengingat Gagal dikirim.");
        });

        Thread thread = new Thread(task);
        thread.setDaemon(true);
        thread.start();
    }

    public static void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
