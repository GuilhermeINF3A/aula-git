/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifro.control;
import br.edu.ifro.model.Cliente;
 import br.edu.ifro.model.Funcionario;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
 /**
 * FXML Controller class
 *
 * @author 3019657
 */
public class CadastrarFuncionarioController2 implements Initializable {
    private Funcionario funcionario;
     @FXML
    private TextField txtNome;
    @FXML
    private TextField txtUsuario;
    @FXML
    private TextField txtSenha;
     /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
     @FXML
    private void salvar(ActionEvent event) {
        Funcionario funcionario = new Funcionario();
        funcionario.setNome(txtNome.getText());
        funcionario.setUsuario(txtUsuario.getText());
        funcionario.setSenha(txtSenha.getText());
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("aula");
        EntityManager em = emf.createEntityManager();
        
        em.getTransaction().begin();
        em.persist(funcionario);
        em.getTransaction().commit();
    }

    @FXML
    private void fechar(ActionEvent event) {
        Stage stage = (Stage) txtNome.getScene().getWindow();
        stage.close();
    }
    
    public void editarFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
        txtNome.setText(funcionario.getNome());
        txtUsuario.setText(funcionario.getUsuario());
        txtSenha.setText(funcionario.getSenha());
    }
    
 }

    
