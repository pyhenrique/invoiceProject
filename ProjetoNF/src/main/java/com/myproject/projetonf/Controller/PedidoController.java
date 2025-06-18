package com.myproject.projetonf.Controller;

import com.myproject.projetonf.DAO.PedidoDAO;
import com.myproject.projetonf.Model.ModelPedido;

public class PedidoController {
    private final PedidoDAO pedidoDAO;

    public PedidoController() {
        this.pedidoDAO = new PedidoDAO();
    }

    public void inserirPedido(int codigoCliente, double valorTotal) {
        ModelPedido pedido = new ModelPedido();
        pedido.setA01_Codigo_Cliente(codigoCliente);
        pedido.setA04_Valor_Total(valorTotal);
        
        pedidoDAO.inserirPedido(
            pedido.getA01_Codigo_Cliente(),
            pedido.getA04_Valor_Total()
        );
    }

    public void removerPedido(int codigo) {
        pedidoDAO.removerPedido(codigo);
    }

    public ModelPedido consultarPedido(int codigo) {
        return pedidoDAO.consultarPedido(codigo);
    }
    
        public void atualizarPedido(ModelPedido pedido) {
        pedidoDAO.atualizarPedido(
            pedido.getA04_Codigo(),
            pedido.getA01_Codigo_Cliente(),
            pedido.getA04_Data_Pedido(),
            pedido.getA04_Valor_Total()
        );
    }
}