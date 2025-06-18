package com.myproject.projetonf.DAO;

import com.myproject.projetonf.Model.ModelItem;
import java.sql.*;
import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.List;

public class ItemDAO {
    
    public void inserirItem(int codigoPedido, int codigoProduto, int quantidade, double precoUnitario) {
        ModelItem objItem = new ModelItem();
        objItem.setA04_Codigo(codigoPedido);
        objItem.setA02_Codigo(codigoProduto);
        objItem.setA03_Quantidade(quantidade);
        objItem.setA03_Preco_Unitario(precoUnitario);

        try (Connection conn = Conexao.conectar();
             CallableStatement stmt = conn.prepareCall("{CALL dbo.sp_inserir_item(?, ?, ?, ?)}")) {

            stmt.setInt(1, objItem.getA04_Codigo());
            stmt.setInt(2, objItem.getA02_Codigo());
            stmt.setInt(3, objItem.getA03_Quantidade());
            stmt.setDouble(4, objItem.getA03_Preco_Unitario());
            stmt.execute();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao inserir item:\n" + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void removerItem(int codigoItem) {
        try (Connection conn = Conexao.conectar();
             CallableStatement stmt = conn.prepareCall("{CALL dbo.sp_deletar_item(?)}")) {

            stmt.setInt(1, codigoItem);
            stmt.execute();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao remover item:\n" + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void atualizarItem(int codigoItem, int quantidade, double precoUnitario) {
        try (Connection conn = Conexao.conectar();
             CallableStatement stmt = conn.prepareCall("{CALL dbo.sp_atualizar_item(?, ?, ?)}")) {

            stmt.setInt(1, codigoItem);
            stmt.setInt(2, quantidade);
            stmt.setDouble(3, precoUnitario);
            stmt.execute();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar item:\n" + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public ModelItem consultarItem(int codigo) {
        String sql = "SELECT * FROM a03_item WHERE a03_codigo = ?";
        ModelItem item = null;

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, codigo);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                item = new ModelItem();
                item.setA03_Codigo(rs.getInt("a03_codigo"));
                item.setA04_Codigo(rs.getInt("a04_codigo"));
                item.setA02_Codigo(rs.getInt("a02_codigo"));
                item.setA03_Quantidade(rs.getInt("a03_quantidade"));
                item.setA03_Preco_Unitario(rs.getDouble("a03_preco_unitario"));
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao consultar item:\n" + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        return item;
    }


public List<ModelItem> listarItensPorNota(int numeroNota) {
    List<ModelItem> itens = new ArrayList<>();
    String sql = "SELECT * FROM a03_item WHERE a04_codigo = ? ORDER BY a03_codigo";
    
    try (Connection conn = Conexao.conectar();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        
        stmt.setInt(1, numeroNota);
        ResultSet rs = stmt.executeQuery();
        
        while (rs.next()) {
            ModelItem item = new ModelItem();
            item.setA03_Codigo(rs.getInt("a03_codigo"));
            item.setA04_Codigo(rs.getInt("a04_codigo"));
            item.setA02_Codigo(rs.getInt("a02_codigo"));
            item.setA03_Quantidade(rs.getInt("a03_quantidade"));
            item.setA03_Preco_Unitario(rs.getDouble("a03_preco_unitario"));
            itens.add(item);
        }
        
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, 
            "Erro ao listar itens da nota:\n" + e.getMessage(), 
            "Erro", JOptionPane.ERROR_MESSAGE);
    }
    return itens;
}
}