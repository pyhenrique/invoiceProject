package com.myproject.projetonf.Controller;

import com.myproject.projetonf.DAO.ClienteDAO;
import com.myproject.projetonf.Model.ModelCliente;
import java.util.List;

public class ClienteController {
    private final ClienteDAO clienteDAO;

    public ClienteController() {
        this.clienteDAO = new ClienteDAO();
    }

    public void inserirCliente(String nome, String cpf, String email, String telefone, String endereco) {
        ModelCliente cliente = new ModelCliente();
        cliente.setA01_Nome(nome);
        cliente.setA01_Cpf(cpf);
        cliente.setA01_Email(email);
        cliente.setA01_Telefone(telefone);
        cliente.setA01_Endereco(endereco);

        clienteDAO.inserirCliente(
            cliente.getA01_Nome(),
            cliente.getA01_Cpf(),
            cliente.getA01_Email(),
            cliente.getA01_Telefone(),
            cliente.getA01_Endereco()
        );
    }

    public void atualizarCliente(int codigo, String nome, String cpf, String email,String telefone, String endereco ) {
        ModelCliente cliente = new ModelCliente();
        cliente.setA01_Codigo(codigo);
        cliente.setA01_Nome(nome);
        cliente.setA01_Cpf(cpf);
        cliente.setA01_Email(email);
        cliente.setA01_Telefone(telefone);
        cliente.setA01_Endereco(endereco);

        clienteDAO.atualizarCliente(
            cliente.getA01_Codigo(),
            cliente.getA01_Nome(),
            cliente.getA01_Cpf(),
            cliente.getA01_Email(),
            cliente.getA01_Telefone(),
            cliente.getA01_Endereco()
        );
    }

    public void removerCliente(int codigo) {
        clienteDAO.removerCliente(codigo);
    }

    public ModelCliente consultarCliente(int codigo) {
        return clienteDAO.consultarCliente(codigo);
    }
    
    public List<ModelCliente> listarClientes() {
        return clienteDAO.listarClientes();
    }
}
