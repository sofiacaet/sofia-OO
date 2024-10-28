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

public class TerceiraTelaController 
{
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField nota1Field;

    @FXML
    private TextField nota2Field;

    @FXML
    private TextField nota3Field;

    @FXML
    private TextField nota4Field;

    
    @FXML
    void voltar(ActionEvent event) 
    {
    try {
        App.setRoot("primeiraTela"); // Certifique-se de que o nome do arquivo FXML está correto
    } catch (IOException e) 
    {
        e.printStackTrace(); // Lide com o erro, se ocorrer
    }
    }

    @FXML
    void listar(ActionEvent event) 
    {
    try {
        App.setRoot("segundaTela"); // Certifique-se de que o nome do arquivo FXML está correto
    } catch (IOException e) 
    {
        e.printStackTrace(); // Lide com o erro, se ocorrer
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erro");
        alert.setHeaderText("Erro ao abrir a lista de alunos");
        alert.setContentText(e.getMessage());
        alert.showAndWait();
        
    }
    }

    @FXML
    void initialize() 
    {
        assert nota1Field != null : "fx:id=\"nota1Field\" was not injected: check your FXML file 'terceiraTela.fxml'.";
        assert nota2Field != null : "fx:id=\"nota2Field\" was not injected: check your FXML file 'terceiraTela.fxml'.";
        assert nota3Field != null : "fx:id=\"nota3Field\" was not injected: check your FXML file 'terceiraTela.fxml'.";
        assert nota4Field != null : "fx:id=\"nota4Field\" was not injected: check your FXML file 'terceiraTela.fxml'.";

    }

    @FXML
    void cadastrar(ActionEvent event) {
        try {
            // Pega os valores das notas
            int nota1 = Integer.parseInt(nota1Field.getText());
            int nota2 = Integer.parseInt(nota2Field.getText());
            int nota3 = Integer.parseInt(nota3Field.getText());
            int nota4 = Integer.parseInt(nota4Field.getText());

            Notas notas = new Notas(nota1, nota2, nota3, nota4);
            int media = notas.getMedia(); // chama a função

            // salva no arquivo
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("alunos.txt", true))) {
                writer.write("Média: " + media);
                writer.newLine();
            }

            // troca telaa
            App.setRoot("segundaTela");

        } catch (Exception e) 
        {

            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("Erro");
            errorAlert.setHeaderText("Ocorreu um erro.");
            errorAlert.setContentText("Por favor, verifique os dados inseridos.");
            errorAlert.showAndWait();
        }
    }


}
