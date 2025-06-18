package com.myproject.projetonf.Model;

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
        this.a04_data_pedido = a04_data_pedido;
    }

    public void setA04_Valor_Total(double a04_valor_total) {
        this.a04_valor_total = a04_valor_total;
    }
}