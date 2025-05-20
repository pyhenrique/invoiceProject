package com.myproject.projetonf;

import java.sql.Connection;
import com.myproject.projetonf.DAO.Conexao;  

public class Main {
    public static void main(String[] args) {
        Connection conexao = Conexao.conectar();

        if (conexao != null) {
            System.out.println("Teste de conexão bem-sucedido!");
        } else {
            System.out.println("Falha no teste de conexão.");
        }
    }
}
