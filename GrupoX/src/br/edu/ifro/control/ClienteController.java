/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifro.control;

import br.edu.ifro.model.Cliente;
import br.edu.ifro.model.Cidade;
import br.eti.diegofonseca.MaskFieldUtil;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 * FXML Controller class
 *
 * @author erica
 */
public class ClienteController implements Initializable {

    @FXML
    private TextField txtNome;
    
    @FXML
    private TextField txtTelefone;
    
    private Cliente cliente;
    
    @FXML
    private ComboBox<Cidade> cbCidade;
    
    public void editarCliente(Cliente cliente) {
        this.cliente = cliente;
        txtNome.setText(cliente.getNome());
        txtTelefone.setText(cliente.getTelefone());
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        MaskFieldUtil.foneField(txtTelefone);    
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("aula");
        EntityManager em = emf.createEntityManager();
        
        Query query = em.createQuery("SELECT c FROM Cidade c ");
        List cidades = query.getResultList();
        
        cbCidade.setItems(FXCollections.observableArrayList(cidades));
        
    }    

    @FXML
    private void salvar(ActionEvent event) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("aula");
        EntityManager em = emf.createEntityManager();
        
        Cliente cliente1;
        if (cliente != null) {            
            Query query = em.createQuery("SELECT a FROM Cliente as a WHERE a.id = :id");
            query.setParameter("id", cliente.getId());

            cliente1 = (Cliente) query.getSingleResult();
        } 
        else {
            cliente1 = new Cliente();
        }     
        
        cliente1.setNome(txtNome.getText());
        // com mascara
        cliente1.setTelefone(txtTelefone.getText());
        
        cliente1.setCidade(cbCidade.getSelectionModel().getSelectedItem());
        // sem mascara
        //cliente1.setTelefone(MaskFieldUtil.onlyAlfaNumericValue(txtTelefone));
        
        em.getTransaction().begin();
        
        em.persist(cliente1);
        
        em.getTransaction().commit();
        
    }

    @FXML
    private void fechar(ActionEvent event) {
        Stage stage = (Stage) txtNome.getScene().getWindow();
        stage.close();
    }
    
}
