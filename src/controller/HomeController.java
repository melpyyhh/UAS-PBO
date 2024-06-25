package controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Line;
import javafx.util.Duration;
import model.User;
import util.Sesi;
import util.ViewFactory;

import java.time.LocalDate;
import java.time.LocalTime;

public class HomeController {

    @FXML
    private Button HomeButton;

    @FXML
    private Button logoutButton;

    @FXML
    private Button RegistButton;

    @FXML
    private Button RiwayatButton;

    @FXML
    private Label dateLabel;

    @FXML
    private Label timeLabel;

    @FXML
    private Label welcomeLabel;

    @FXML
    private ImageView logoImageView;

    @FXML
    private Line topLine;

    @FXML
    private Line bottomLine;

    @FXML
    private Label addressLabel;
    
    @FXML
    private Label userLabel;
    
    @FXML
    void homeView(ActionEvent event) {
        // Logic untuk menampilkan view Home
    	Parent homeViewParent = ViewFactory.loadView("/view/HomeView.fxml");
        if (homeViewParent != null) {
            Scene homeViewScene = new Scene(homeViewParent);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(homeViewScene);
            window.show();
        }
    }

    @FXML
    void registView(ActionEvent event) {
    	Parent registViewParent = ViewFactory.loadView("/view/RegistView.fxml");
        if (registViewParent != null) {
            Scene homeViewScene = new Scene(registViewParent);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(homeViewScene);
            window.show();
        }
    }

    @FXML
    void riwayatView(ActionEvent event) {
        // Logic untuk menampilkan view Riwayat Medis
    	Parent riwayatViewParent = ViewFactory.loadView("/view/RiwayatView.fxml");
        if (riwayatViewParent != null) {
            Scene homeViewScene = new Scene(riwayatViewParent);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(homeViewScene);
            window.show();
        }
    }

    @FXML
    void logout(ActionEvent event) {
    	// Clear the session
        Sesi.clear();
        // Navigate to the login view
        Parent loginViewParent = ViewFactory.loadView("/view/LoginView.fxml");
        if (loginViewParent != null) {
            Scene homeViewScene = new Scene(loginViewParent);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(homeViewScene);
            window.show();
        }
    }

    @FXML
    public void initialize() {
        // Contoh set date dan time, bisa ditambahkan logic untuk mendapatkan date dan time sebenarnya
    	updateDateTimeLabels();
    	Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(1), event -> updateDateTimeLabels())
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
        User currentUser = Sesi.getCurrentUser();
        if (currentUser != null) {
            userLabel.setText(currentUser.getNim());
        }
    }
    
 // Method untuk update timeLabel secara realtime
    private void updateDateTimeLabels() {
        // Update tanggal dan jam secara realtime
        dateLabel.setText("Tanggal : " + LocalDate.now());
        timeLabel.setText("Jam       : " + LocalTime.now().toString().substring(0, 8));
    }
}
