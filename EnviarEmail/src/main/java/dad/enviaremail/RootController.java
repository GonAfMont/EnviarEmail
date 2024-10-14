package dad.enviaremail;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

import java.io.IOException;

public class RootController {

    @FXML
    private TextField serverIPText;
    @FXML
    private TextField serverPortText;
    @FXML
    private CheckBox conectionSSLCheckbox;
    @FXML
    private TextField fromEmailText;
    @FXML
    private TextField toEmailText;
    @FXML
    private TextField subjectText;
    @FXML
    private TextArea messageTextArea;
    @FXML
    private PasswordField emailPasswordField;
    @FXML
    private Button sendEmailButton;
    @FXML
    private Button emptyEmailButton;
    @FXML
    private Button closeEmailButton;
    @FXML
    private GridPane root;

    public RootController() {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/RootView.fxml"));
            loader.setController(this);

            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public GridPane getRoot() {
        return root;
    }

    public void setRoot(GridPane root) {
        this.root = root;
    }

    @FXML
    public void setSendEmailButton(ActionEvent event) {
        try {
            Email email = new SimpleEmail();
            email.setHostName(serverIPText.getText());
            email.setSmtpPort(Integer.parseInt(serverPortText.getText()));
            email.setAuthenticator(new DefaultAuthenticator(fromEmailText.getText(), emailPasswordField.getText()));
            email.setSSLOnConnect(conectionSSLCheckbox.isSelected());
            email.setFrom(fromEmailText.getText());
            email.setSubject(subjectText.getText());
            email.setMsg(messageTextArea.getText());
            email.addTo(toEmailText.getText());
            email.send();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Mensaje enviado");
            alert.setHeaderText("Mensaje enviado con éxito a " + toEmailText.getText());
            alert.showAndWait();

        } catch (EmailException e) {
            System.out.println("No se ha podido enviar el email");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No se pudo enviar el email");
            alert.setContentText("Invalid message supplied");
            alert.showAndWait();

        }

    }

    @FXML
    public void setEmptyEmailButton(ActionEvent event) {
        serverIPText.clear();
        serverPortText.clear();
        fromEmailText.clear();
        toEmailText.clear();
        subjectText.clear();
        messageTextArea.clear();
        emailPasswordField.clear();
        conectionSSLCheckbox.setSelected(false);
    }

    @FXML
    public void setCloseEmailButton(ActionEvent event) {
        Stage stage = (Stage) closeEmailButton.getScene().getWindow();
        stage.close();
    }

}

