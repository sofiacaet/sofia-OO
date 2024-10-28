package com.caetano;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class PrimeiraTelaController{

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button cadastrarfield;

    @FXML
    private TextField cpffield;

    @FXML
    private TextField emailfield;

    @FXML
    private TextField nascimentofield;

    @FXML
    private TextField nomefield;

    @FXML
    void cadastrar(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert cadastrarfield != null : "fx:id=\"cadastrarfield\" was not injected: check your FXML file 'primeiraTela.fxml'.";
        assert cpffield != null : "fx:id=\"cpffield\" was not injected: check your FXML file 'primeiraTela.fxml'.";
        assert emailfield != null : "fx:id=\"emailfield\" was not injected: check your FXML file 'primeiraTela.fxml'.";
        assert nascimentofield != null : "fx:id=\"nascimentofield\" was not injected: check your FXML file 'primeiraTela.fxml'.";
        assert nomefield != null : "fx:id=\"nomefield\" was not injected: check your FXML file 'primeiraTela.fxml'.";

    }

    @FXML
    void CadastrarAluno(ActionEvent event) {
        String nome = nomefield.getText();
        String dataNascimento = nascimentofield.getText();
        String cpf = cpffield.getText();
        String email = emailfield.getText();

        Aluno aluno = new Aluno(nome, dataNascimento, cpf, email);

         // Troca a tela
         try {
            App.setRoot("segundaTela");
        } catch (IOException e) {
            e.printStackTrace(); // Erro
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("Erro");
            errorAlert.setHeaderText("Erro ao abrir a nova tela");
            errorAlert.setContentText(e.getMessage());
            errorAlert.showAndWait();
        }
    }


}
