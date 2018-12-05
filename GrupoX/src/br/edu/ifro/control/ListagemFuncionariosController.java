/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifro.control;

import br.edu.ifro.model.Cliente;
import br.edu.ifro.model.Funcionario;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 * FXML Controller class
 *
 * @author 02233249224
 */
public class ListagemFuncionariosController implements Initializable {

    @FXML
    private TableView<Funcionario> tbFuncionario;
    @FXML
    private Button btnFechar;
    @FXML
    private Button btnRemover;
    @FXML
    private Button btnEditar;
    @FXML
    private Button btnSalvar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listar();
    }    

    @FXML
    private void fechar(ActionEvent event) {
         Stage stage = (Stage) btnEditar.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void remover(ActionEvent event) { 
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("aula");
        EntityManager em = emf.createEntityManager();
        
        Funcionario Funcionario = (Funcionario) tbFuncionario.getSelectionModel().getSelectedItem();
        
        Query query = em.createQuery("SELECT a FROM Funcionario as a WHERE a.id = :id");
        query.setParameter("id", Funcionario.getId());
               
        Funcionario a = (Funcionario) query.getSingleResult();
        
        em.getTransaction().begin();
        em.remove(a);
        em.getTransaction().commit();
        listar();    
    }

    @FXML
    private void editar(ActionEvent event)  throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/br/edu/ifro/view/CadastrarFuncionarioController2.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        
        CadastrarFuncionarioController2 c = fxmlLoader.getController();
        c.editarFuncionario((Funcionario) tbFuncionario.getSelectionModel().getSelectedItem());
        
        stage.setTitle("Cadastrar Funcionario");
        stage.setScene(scene);
        stage.show();   
        
    }

    @FXML
    private void salvar(ActionEvent event) {
    }
    

private void listar() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("aula");
        EntityManager em = emf.createEntityManager();
        
                
        // import javax.persistence.Query;
        Query query = em.createQuery("SELECT a FROM Funcionario a");
        // import java.util.List;
        // import br.edu.ifro.model.Cliente;
        List funcionario = query.getResultList();
        // import javafx.collections.ObservableList;
        // import javafx.collections.FXCollections;
        ObservableList oFuncionario = FXCollections.observableArrayList(funcionario);                                
        tbFuncionario.setItems(oFuncionario);
    }    
}