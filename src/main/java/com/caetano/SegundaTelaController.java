package com.caetano;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;

public class SegundaTelaController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ListView<String> listaField; // define o tipo da list view

    

    @FXML
    void initialize() {
        assert listaField != null : "fx:id=\"listaField\" was not injected: check your FXML file 'segundaTela.fxml'.";

        //Aparecer alunos do arquivo
        carregarAlunos(); //chamando metodo 

    }

    // arquivo

    private void carregarAlunos() //no caso esse metodo pega oq ta no arquivo adiciona no lista field
    {
        listaField.getItems().clear(); // Limpa a lista antes de carregar novos dados
        try (BufferedReader reader = new BufferedReader(new FileReader("alunos.txt"))) //reader pq ta lendo oq ta dentro do arquivo
        {
            String linha;
            while ((linha = reader.readLine()) != null) {
                listaField.getItems().add(linha); // Adiciona cada linha ao listaField
            }
        } catch (IOException e) {
            e.printStackTrace();
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("Erro");
            errorAlert.setHeaderText("Erro ao carregar a lista de alunos");
            errorAlert.setContentText(e.getMessage());
            errorAlert.showAndWait();
        }
    }
    

    @FXML
    void voltarTela(ActionEvent event) {
        try {
            App.setRoot("primeiraTela"); 
        } catch (IOException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Erro ao voltar para a tela inicial");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

}