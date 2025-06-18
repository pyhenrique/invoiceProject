package com.myproject.projetonf.Controller;

import com.myproject.projetonf.DAO.ItemDAO;
import com.myproject.projetonf.DAO.ProdutoDAO;
import com.myproject.projetonf.Model.ModelItem;
import java.util.List;

public class ItemController {
    private final ItemDAO itemDAO;

    public ItemController() {
        this.itemDAO = new ItemDAO();
    }

    public void inserirItem(int codigoPedido, int codigoProduto, int quantidade) {
        double precoUnitario = new ProdutoDAO().buscarPrecoPorCodigo(codigoProduto);
        
        ModelItem item = new ModelItem();
        item.setA04_Codigo(codigoPedido);
        item.setA02_Codigo(codigoProduto);
        item.setA03_Quantidade(quantidade);
        item.setA03_Preco_Unitario(precoUnitario);
        
        itemDAO.inserirItem(
            item.getA04_Codigo(),
            item.getA02_Codigo(),
            item.getA03_Quantidade(),
            item.getA03_Preco_Unitario()
        );
    }
    
    public List<ModelItem> listarItensPorNota(int numeroNota) {
        return itemDAO.listarItensPorNota(numeroNota);
    }

    public void atualizarItem(int codigoItem, int quantidade) {
        ModelItem item = consultarItem(codigoItem);
    
        if (item == null) {
            throw new RuntimeException("Item não encontrado!");
        }
        double precoUnitario = item.getA03_Preco_Unitario();
    itemDAO.atualizarItem(codigoItem, quantidade, precoUnitario);
}

    public void removerItem(int codigoItem) {
        itemDAO.removerItem(codigoItem);
    }

    public ModelItem consultarItem(int codigo) {
        return itemDAO.consultarItem(codigo);
    }
}