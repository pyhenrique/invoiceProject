package com.myproject.projetonf.DAO;

import com.myproject.projetonf.Model.ModelCliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;


public class ClienteDAO {
    
    public void inserirCliente(String sNome, String sCpf, String sEmail, String sTelefone, String sEndereco) {

        ModelCliente objModelCliente = new ModelCliente();
        objModelCliente.setA01_Nome(sNome);
        objModelCliente.setA01_Cpf(sCpf);
        objModelCliente.setA01_Email(sEmail);
        objModelCliente.setA01_Telefone(sTelefone);
        objModelCliente.setA01_Endereco(sEndereco);

        try (Connection conn = Conexao.conectar();
             CallableStatement stmt = conn.prepareCall("{CALL dbo.sp_inserir_cliente(?, ?, ?, ?, ?)}")) {

            stmt.setString(1, objModelCliente.getA01_Nome());
            stmt.setString(2, objModelCliente.getA01_Cpf());
            stmt.setString(3, objModelCliente.getA01_Email());
            stmt.setString(4, objModelCliente.getA01_Telefone());
            stmt.setString(5, objModelCliente.getA01_Endereco());

            stmt.execute();

            JOptionPane.showMessageDialog(null, "Cliente inserido com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao inserir cliente:\n" + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
}   


public void removerCliente(int codigo) {

    try (Connection conn = Conexao.conectar();
         CallableStatement stmt = conn.prepareCall("{CALL dbo.sp_deletar_cliente(?)}")) {

        stmt.setInt(1, codigo);
        stmt.execute();

        JOptionPane.showMessageDialog(null, "Cliente removido com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Erro ao remover cliente:\n" + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
    }
}


    
    public void atualizarCliente(int codigo, String sNome, String sCpf,  String sEmail, String sTelefone, String sEndereco) {

        ModelCliente objModelCliente = new ModelCliente();
        objModelCliente.setA01_Codigo(codigo);
        objModelCliente.setA01_Nome(sNome);
        objModelCliente.setA01_Cpf(sCpf);
        objModelCliente.setA01_Email(sEmail);
        objModelCliente.setA01_Telefone(sTelefone);
        objModelCliente.setA01_Endereco(sEndereco);

        try (Connection conn = Conexao.conectar();
             CallableStatement stmt = conn.prepareCall("{CALL dbo.sp_atualizar_cliente(?, ?, ?, ?, ?, ?)}")) {

            stmt.setInt(1, objModelCliente.getA01_Codigo());
            stmt.setString(2, objModelCliente.getA01_Nome());
            stmt.setString(3, objModelCliente.getA01_Cpf());
            stmt.setString(4, objModelCliente.getA01_Email());
            stmt.setString(5, objModelCliente.getA01_Telefone());
            stmt.setString(6, objModelCliente.getA01_Endereco());

            stmt.execute();

            JOptionPane.showMessageDialog(null, "Cliente atualizado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar cliente:\n" + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
}


    
public ModelCliente consultarCliente(int codigo) {
    String sql = "SELECT * FROM a01_cliente WHERE a01_codigo = ?";
    ModelCliente cliente = null;

    try (Connection conn = Conexao.conectar();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setInt(1, codigo);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            cliente = new ModelCliente();
            cliente.setA01_Codigo(rs.getInt("a01_codigo"));
            cliente.setA01_Nome(rs.getString("a01_nome"));
            cliente.setA01_Cpf(rs.getString("a01_cpf"));
            cliente.setA01_Email(rs.getString("a01_email"));
            cliente.setA01_Telefone(rs.getString("a01_telefone"));
            cliente.setA01_Endereco(rs.getString("a01_endereco"));
        }

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Erro ao consultar cliente:\n" + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
    }

    return cliente;
}

public List<ModelCliente> listarClientes() {
    List<ModelCliente> clientes = new ArrayList<>();
    String sql = "SELECT a01_codigo, a01_nome, a01_cpf, a01_email, a01_telefone, a01_endereco FROM a01_cliente ORDER BY a01_nome";

    try (Connection conn = Conexao.conectar();
         PreparedStatement stmt = conn.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {
            ModelCliente cliente = new ModelCliente();
            cliente.setA01_Codigo(rs.getInt("a01_codigo"));
            cliente.setA01_Nome(rs.getString("a01_nome"));
            cliente.setA01_Cpf(rs.getString("a01_cpf"));
            cliente.setA01_Email(rs.getString("a01_email"));
            cliente.setA01_Telefone(rs.getString("a01_telefone"));
            cliente.setA01_Endereco(rs.getString("a01_endereco"));
            clientes.add(cliente);
        }

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, 
            "Erro ao listar clientes:\n" + e.getMessage(),
            "Erro", JOptionPane.ERROR_MESSAGE);
    }
    return clientes;
}
}
