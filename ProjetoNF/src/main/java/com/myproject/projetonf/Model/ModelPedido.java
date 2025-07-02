package com.myproject.projetonf.Model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ModelPedido {
    private int a04_codigo;
    private int a01_codigo_cliente;
    private String a04_data_pedido;
    private double a04_valor_total;

    public ModelPedido() {}

    // Getters
    public int getA04_Codigo() {
        return a04_codigo;
    }

    public int getA01_Codigo_Cliente() {
        return a01_codigo_cliente;
    }

    public String getA04_Data_Pedido() {
        return a04_data_pedido;
    }
    
    public String getA04_Data_PedidoParaBD() {
    try {
        SimpleDateFormat sdfExibicao = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat sdfBD = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        return sdfBD.format(sdfExibicao.parse(this.a04_data_pedido));
    } catch (Exception e) {
        return this.a04_data_pedido; // Se já estiver no formato BD
    }
}

    public double getA04_Valor_Total() {
        return a04_valor_total;
    }

    // Setters
    public void setA04_Codigo(int a04_codigo) {
        this.a04_codigo = a04_codigo;
    }

    public void setA01_Codigo_Cliente(int a01_codigo_cliente) {
        this.a01_codigo_cliente = a01_codigo_cliente;
    }

public void setA04_Data_Pedido(String a04_data_pedido) {
    try {
        if (a04_data_pedido == null) {
            throw new IllegalArgumentException("Data não pode ser nula");
        }
        
        // Se já está no formato do BD (contém hífen)
        if (a04_data_pedido.contains("-")) {
            this.a04_data_pedido = converterDataParaExibicao(a04_data_pedido);
        } 
        // Se está no formato de exibição (contém barra)
        else if (a04_data_pedido.contains("/")) {
            this.a04_data_pedido = a04_data_pedido; // Armazena como está
        } else {
            throw new IllegalArgumentException("Formato de data desconhecido");
        }
    } catch (Exception e) {
        throw new IllegalArgumentException("Data inválida: " + a04_data_pedido + ". Use DD/MM/AAAA ou o formato do banco", e);
    }
}

// Método auxiliar para conversão
private String converterDataParaExibicao(String dataBD) throws ParseException {
    SimpleDateFormat sdfBD = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
    SimpleDateFormat sdfExibicao = new SimpleDateFormat("dd/MM/yyyy");
    Date data = sdfBD.parse(dataBD);
    return sdfExibicao.format(data);
}

    public void setA04_Valor_Total(double a04_valor_total) {
        this.a04_valor_total = a04_valor_total;
    }
}