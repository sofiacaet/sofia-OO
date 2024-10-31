package com.caetano;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class PrimeiraTelaController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField cpfField;

    @FXML
    private TextField dtField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField nomeField;

    @FXML
    void initialize() {
        assert cpfField != null : "fx:id=\"cpfField\" was not injected: check your FXML file 'primeiraTela.fxml'.";
        assert dtField != null : "fx:id=\"dtField\" was not injected: check your FXML file 'primeiraTela.fxml'.";
        assert emailField != null : "fx:id=\"emailField\" was not injected: check your FXML file 'primeiraTela.fxml'.";
        assert nomeField != null : "fx:id=\"nomeField\" was not injected: check your FXML file 'primeiraTela.fxml'.";
    }

    @FXML
    void cadastrar(ActionEvent event) 
    { // Pega dados que o usuário digitou
        String nome = nomeField.getText();
        String email = emailField.getText();
        String dataNascimento = dtField.getText();
        String cpf = cpfField.getText(); // atribuindo a variavel

        Aluno aluno = new Aluno(nome, email, dataNascimento, cpf); //criando novo objeto

        // arquivo - filewriter adiciona um novo dado ao final do que já existe ou seja pegando e colocando no arquivo que ja existe
        // writer escrevendo no arquivo
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("alunos.txt", true))) {
            writer.write(nome + " Email: " + email + " Data de Nascimento: " + dataNascimento + " CPF: " + cpf); // escrever nome no arquivo
            //writer.newLine(); // nova linha -> pra pular uma linha

        } catch (IOException e) //explodindo uma mensagem de erro
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Erro ao salvar dados");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
            return;
        }

        // Sucesso
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("CADASTRO COMPLETO!!");
        alert.setHeaderText("Cadastrado completo com sucesso!!");
        alert.setContentText(aluno.toString());
        alert.showAndWait();

        // Limpar os campos
        nomeField.clear();
        emailField.clear();
        dtField.clear();
        cpfField.clear();

        // Troca a tela
        try {
            App.setRoot("terceiraTela");
        } catch (IOException e) {
            e.printStackTrace(); // Erro
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("Erro");
            errorAlert.setHeaderText("Erro ao abrir a nova tela!");
            errorAlert.setContentText(e.getMessage());
            errorAlert.showAndWait();
        }
    }
}