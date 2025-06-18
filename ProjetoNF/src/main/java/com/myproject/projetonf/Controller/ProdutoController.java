package com.myproject.projetonf.Controller;

import com.myproject.projetonf.DAO.ProdutoDAO;
import com.myproject.projetonf.Model.ModelProduto;

public class ProdutoController {
    private final ProdutoDAO produtoDAO;

    public ProdutoController() {
        this.produtoDAO = new ProdutoDAO();
    }

    public void inserirProduto(String nome, double preco, String categoria) {
        ModelProduto produto = new ModelProduto();
        produto.setA02_Nome(nome);
        produto.setA02_Preco(preco);
        produto.setA02_Categoria(categoria);
        
        produtoDAO.inserirProduto(
            produto.getA02_Nome(),
            produto.getA02_Preco(),
            produto.getA02_Categoria()
        );
    }

    public void atualizarProduto(int codigo, String nome, double preco, String categoria) {
        ModelProduto produto = new ModelProduto();
        produto.setA02_Codigo(codigo);
        produto.setA02_Nome(nome);
        produto.setA02_Preco(preco);
        produto.setA02_Categoria(categoria);
        
        produtoDAO.atualizarProduto(
            produto.getA02_Codigo(),
            produto.getA02_Nome(),
            produto.getA02_Preco(),
            produto.getA02_Categoria()
        );
    }

    public void removerProduto(int codigo) {
        produtoDAO.removerProduto(codigo);
    }

    public ModelProduto consultarProduto(int codigo) {
        return produtoDAO.consultarProduto(codigo);
    }
}
