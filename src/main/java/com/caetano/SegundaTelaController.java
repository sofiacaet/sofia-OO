package com.caetano;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextInputDialog;

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

        // Carregar alunos do arquivo
        carregarAlunos(); // chamando método 
    }

    // Método para carregar alunos do arquivo
    private void carregarAlunos() {
        listaField.getItems().clear(); // Limpa a lista antes de carregar novos dados
        try (BufferedReader reader = new BufferedReader(new FileReader("alunos.txt"))) {
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

    // Método para voltar à tela anterior
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

    // Método para excluir aluno selecionado
    @FXML
    private void excluirAluno(ActionEvent event) {
        String alunoSelecionado = listaField.getSelectionModel().getSelectedItem();
        if (alunoSelecionado != null) {
            // Remove o aluno da ListView
            listaField.getItems().remove(alunoSelecionado);

            // Atualiza o arquivo 'alunos.txt'
            atualizarArquivo();
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Sucesso");
            alert.setHeaderText("Aluno excluído");
            alert.setContentText("O aluno " + alunoSelecionado + " foi excluído com sucesso.");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Atenção");
            alert.setHeaderText("Nenhum aluno selecionado");
            alert.setContentText("Por favor, selecione um aluno para excluir.");
            alert.showAndWait();
        }
    }

    // Método para editar aluno selecionado
    @FXML
    private void editarAluno(ActionEvent event) {
        String alunoSelecionado = listaField.getSelectionModel().getSelectedItem();
        if (alunoSelecionado != null) {
            // Exibe um diálogo para editar os dados do aluno
            TextInputDialog dialog = new TextInputDialog(alunoSelecionado);
            dialog.setTitle("Editar Aluno");
            dialog.setHeaderText("Edite os dados do aluno");
            dialog.setContentText("Aluno:");

            dialog.showAndWait().ifPresent(novoNome -> {
                // Atualiza o aluno na ListView
                int index = listaField.getSelectionModel().getSelectedIndex();
                listaField.getItems().set(index, novoNome);

                // Atualiza o arquivo 'alunos.txt'
                atualizarArquivo();

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Sucesso");
                alert.setHeaderText("Aluno editado");
                alert.setContentText("O aluno foi editado com sucesso.");
                alert.showAndWait();
            });
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Atenção");
            alert.setHeaderText("Nenhum aluno selecionado");
            alert.setContentText("Por favor, selecione um aluno para editar.");
            alert.showAndWait();
        }
    }

    // Método para atualizar o arquivo 'alunos.txt' após a exclusão ou edição
    private void atualizarArquivo() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("alunos.txt"))) {
            for (String aluno : listaField.getItems()) {
                writer.write(aluno);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("Erro");
            errorAlert.setHeaderText("Erro ao atualizar o arquivo de alunos");
            errorAlert.setContentText(e.getMessage());
            errorAlert.showAndWait();
        }
    }
}
