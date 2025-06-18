package com.myproject.projetonf.Model;

public class ModelItem {
    private int a03_codigo;
    private int a04_codigo;
    private int a02_codigo;
    private int a03_quantidade;
    private double a03_preco_unitario;

    public ModelItem() {}

    // Getters
    public int getA03_Codigo() {
        return a03_codigo;
    }

    public int getA04_Codigo() {
        return a04_codigo;
    }

    public int getA02_Codigo() {
        return a02_codigo;
    }

    public int getA03_Quantidade() {
        return a03_quantidade;
    }

    public double getA03_Preco_Unitario() {
        return a03_preco_unitario;
    }

    // Setters
    public void setA03_Codigo(int a03_codigo) {
        this.a03_codigo = a03_codigo;
    }

    public void setA04_Codigo(int a04_codigo) {
        this.a04_codigo = a04_codigo;
    }

    public void setA02_Codigo(int a02_codigo) {
        this.a02_codigo = a02_codigo;
    }

    public void setA03_Quantidade(int a03_quantidade) {
        this.a03_quantidade = a03_quantidade;
    }

    public void setA03_Preco_Unitario(double a03_preco_unitario) {
        this.a03_preco_unitario = a03_preco_unitario;
    }
}