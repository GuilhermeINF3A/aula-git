package br.edu.ifro.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;


@Entity
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
        
    @ManyToOne
    private Cliente nomeCliente;

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public Cliente getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(Cliente nomeCliente) {
        this.nomeCliente = nomeCliente;
    }


    
    @ManyToMany
    private List<Produto> produtos;
    
    public Pedido() {
        produtos = new ArrayList<Produto>();
    }
    
    public void addProduto(Produto produto) {
        produtos.add(produto);
    }
    
    public void removeProduto(Produto produto) {
        produtos.remove(produto);
    }
    
    public List<Produto> getProdutos() {
        return produtos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
   
}
