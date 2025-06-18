package com.myproject.projetonf.DAO;

import com.myproject.projetonf.Model.ModelPedido;
import java.sql.*;
import javax.swing.JOptionPane;

public class PedidoDAO {
    
        public void inserirPedido(int codigoCliente, double valorTotal) {
          try (Connection conn = Conexao.conectar();
             CallableStatement stmt = conn.prepareCall("{CALL dbo.sp_inserir_pedido(?, ?)}")) {
            
            stmt.setInt(1, codigoCliente);    
            stmt.setDouble(2, valorTotal);   
            
            stmt.execute();
            
        } catch (SQLException e) {
            throw new RuntimeException("Erro no banco de dados ao inserir pedido: " + e.getMessage());
        }
    }

    public void removerPedido(int codigo) {
        try (Connection conn = Conexao.conectar();
             CallableStatement stmt = conn.prepareCall("{CALL dbo.sp_deletar_pedido(?)}")) {

            stmt.setInt(1, codigo);
            stmt.execute();
            JOptionPane.showMessageDialog(null, "Pedido removido com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao remover pedido:\n" + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

public void atualizarPedido(int codigo, int codigoCliente, String data, double valorTotal) {
        try (Connection conn = Conexao.conectar();
             CallableStatement stmt = conn.prepareCall("{CALL dbo.sp_atualizar_pedido(?, ?, ?, ?)}")) {
            
            stmt.setInt(1, codigo);
            stmt.setInt(2, codigoCliente);
            stmt.setString(3, data);
            stmt.setDouble(4, valorTotal);
            stmt.execute();
            
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao atualizar pedido", e);
        }
    }

    public ModelPedido consultarPedido(int codigo) {
        String sql = "SELECT * FROM a04_pedido WHERE a04_codigo = ?";
        ModelPedido pedido = null;

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, codigo);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                pedido = new ModelPedido();
                pedido.setA04_Codigo(rs.getInt("a04_codigo"));
                pedido.setA01_Codigo_Cliente(rs.getInt("a01_codigo_cliente"));
                pedido.setA04_Data_Pedido(rs.getString("a04_data_pedido"));
                pedido.setA04_Valor_Total(rs.getDouble("a04_valor_total"));
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao consultar pedido:\n" + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        return pedido;
    }
}

