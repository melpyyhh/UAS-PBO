package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.User;
import model.UserDAO;
import util.Sesi;
import util.ViewFactory;

public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginButton;
    
    private UserDAO userDAO = new UserDAO();
    @FXML
    private void loginButtonAction(ActionEvent event) {
    	String username = usernameField.getText();
        String password = passwordField.getText();

        User user = userDAO.getUserByUsernameAndPassword(username, password);

        if (user != null) {
            Sesi.setCurrentUser(user);
            Parent homeViewParent = ViewFactory.loadView("/view/HomeView.fxml");
            if (homeViewParent != null) {
                Scene homeViewScene = new Scene(homeViewParent);
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(homeViewScene);
                window.show();
            }
        } 
    }
}
