package com.myproject.projetonf.DAO;

import com.myproject.projetonf.Model.ModelProduto;
import java.sql.*;
import javax.swing.JOptionPane;

public class ProdutoDAO {
    
    public void inserirProduto(String nome, double preco, String categoria) {
        ModelProduto objProduto = new ModelProduto();
        objProduto.setA02_Nome(nome);
        objProduto.setA02_Preco(preco);
        objProduto.setA02_Categoria(categoria);

        try (Connection conn = Conexao.conectar();
             CallableStatement stmt = conn.prepareCall("{CALL dbo.sp_inserir_produto(?, ?, ?)}")) {

            stmt.setString(1, objProduto.getA02_Nome());
            stmt.setDouble(2, objProduto.getA02_Preco());
            stmt.setString(3, objProduto.getA02_Categoria());
            stmt.execute();

            JOptionPane.showMessageDialog(null, "Produto inserido com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao inserir produto:\n" + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void removerProduto(int codigo) {
        try (Connection conn = Conexao.conectar();
             CallableStatement stmt = conn.prepareCall("{CALL dbo.sp_deletar_produto(?)}")) {

            stmt.setInt(1, codigo);
            stmt.execute();
            JOptionPane.showMessageDialog(null, "Produto removido com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao remover produto:\n" + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void atualizarProduto(int codigo, String nome, double preco, String categoria) {
        ModelProduto objProduto = new ModelProduto();
        objProduto.setA02_Codigo(codigo);
        objProduto.setA02_Nome(nome);
        objProduto.setA02_Preco(preco);
        objProduto.setA02_Categoria(categoria);

        try (Connection conn = Conexao.conectar();
             CallableStatement stmt = conn.prepareCall("{CALL dbo.sp_atualizar_produto(?, ?, ?, ?)}")) {

            stmt.setInt(1, objProduto.getA02_Codigo());
            stmt.setString(2, objProduto.getA02_Nome());
            stmt.setDouble(3, objProduto.getA02_Preco());
            stmt.setString(4, objProduto.getA02_Categoria());
            stmt.execute();

            JOptionPane.showMessageDialog(null, "Produto atualizado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar produto:\n" + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public ModelProduto consultarProduto(int codigo) {
        String sql = "SELECT * FROM a02_produto WHERE a02_codigo = ?";
        ModelProduto produto = null;

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, codigo);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                produto = new ModelProduto();
                produto.setA02_Codigo(rs.getInt("a02_codigo"));
                produto.setA02_Nome(rs.getString("a02_nome"));
                produto.setA02_Preco(rs.getDouble("a02_preco"));
                produto.setA02_Categoria(rs.getString("a02_categoria"));
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao consultar produto:\n" + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        return produto;
    }
    
    public double buscarPrecoPorCodigo(int codigoProduto) {
    String sql = "SELECT preco_unitario FROM produtos WHERE codigo = ?";
    try (Connection conn = Conexao.conectar();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        
        stmt.setInt(1, codigoProduto);
        ResultSet rs = stmt.executeQuery();
        
        if (rs.next()) {
            return rs.getDouble("preco_unitario"); // Retorna o preço do BD
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return 0.0; // Ou lance uma exceção se preferir
}
}