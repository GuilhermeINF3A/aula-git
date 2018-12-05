package br.edu.ifro.control;

import br.edu.ifro.model.Cliente;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class ListagemClienteController implements Initializable {

    @FXML
    private TableView<?> tbCliente;
    private ComboBox<?> cbCliente;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listar();
    }    

    @FXML
    private void editar(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/br/edu/ifro/view/Cliente.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        
        ClienteController c = fxmlLoader.getController();
        c.editarCliente((Cliente) tbCliente.getSelectionModel().getSelectedItem());
        
        stage.setTitle("Cadastrar cliente");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void excluir(ActionEvent event) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("aula");
        EntityManager em = emf.createEntityManager();
        
        Cliente Cliente = (Cliente) tbCliente.getSelectionModel().getSelectedItem();
        
        Query query = em.createQuery("SELECT a FROM Cliente as a WHERE a.id = :id");
        query.setParameter("id", Cliente.getId());
               
        Cliente a = (Cliente) query.getSingleResult();
        
        em.getTransaction().begin();
        em.remove(a);
        em.getTransaction().commit();
        listar();
    }
    
    private void listar() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("aula");
        EntityManager em = emf.createEntityManager();
                
        // import javax.persistence.Query;
        Query query = em.createQuery("SELECT a FROM Cliente as a");
        // import java.util.List;
        // import br.edu.ifro.model.Cliente;
        List<Cliente> cliente = query.getResultList();
        // import javafx.collections.ObservableList;
        // import javafx.collections.FXCollections;
        ObservableList oCliente = FXCollections.observableArrayList(cliente);                                 
        cbCliente.setItems(oCliente);
        tbCliente.setItems(oCliente);
    }
    
}
