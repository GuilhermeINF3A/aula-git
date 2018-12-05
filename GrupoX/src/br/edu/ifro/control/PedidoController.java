package br.edu.ifro.control;

import br.edu.ifro.model.Aluno;
import br.edu.ifro.model.Pedido;
import br.edu.ifro.model.Produto;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;


public class PedidoController implements Initializable {

    
    @FXML
    private ComboBox<Produto> cbProduto;
    @FXML
    private Button btnAdicionar;
    @FXML
    private TableView<Produto> tbProdutos;
    @FXML
    private Button btnSalvar;
    @FXML
    private Button btnRemover;
    
    private Pedido pedido;
    @FXML
    private ComboBox<Aluno> cbCliente;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("aula");
        EntityManager em = emf.createEntityManager();
        
        Query query = em.createQuery("SELECT p FROM Produto p ");
        List produtos = query.getResultList();
        
        cbProduto.setItems(FXCollections.observableArrayList(produtos));
        
        pedido = new Pedido();
        
        listar();
    }     

    @FXML
    private void adicionarProduto(ActionEvent event) {
        pedido.addProduto(cbProduto.getSelectionModel().getSelectedItem());
        
        tbProdutos.setItems(FXCollections.observableArrayList(pedido.getProdutos()));
    }

    @FXML
    private void salvar(ActionEvent event) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("aula");
        EntityManager em = emf.createEntityManager();
        
        pedido.setNomeCliente(cbCliente.getSelectionModel().getSelectedItem());
        
        em.getTransaction().begin();
        em.persist(pedido);
        em.getTransaction().commit();
    }

    @FXML
    private void removerProduto(ActionEvent event) {
        pedido.removeProduto(cbProduto.getSelectionModel().getSelectedItem());
        
        tbProdutos.setItems(FXCollections.observableArrayList(pedido.getProdutos()));
    }
    private void listar() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("aula");
        EntityManager em = emf.createEntityManager();
                
        // import javax.persistence.Query;
        Query qr = em.createQuery("SELECT a FROM Aluno as a");
        // import java.util.List;
        // import br.edu.ifro.model.Aluno;
        List<Aluno> alunos = qr.getResultList();
        // import javafx.collections.ObservableList;
        // import javafx.collections.FXCollections;
        ObservableList oAlunos = FXCollections.observableArrayList(alunos);                                 
        cbCliente.setItems(oAlunos);
        
    }
    
    
}
