package com.myproject.projetonf.Model;

public class ModelProduto {
    private int a02_codigo;
    private String a02_nome;
    private double a02_preco;
    private String a02_categoria;

    public ModelProduto() {}

    // Getters
    public int getA02_Codigo() {
        return a02_codigo;
    }

    public String getA02_Nome() {
        return a02_nome;
    }

    public double getA02_Preco() {
        return a02_preco;
    }

    public String getA02_Categoria() {
        return a02_categoria;
    }

    // Setters
    public void setA02_Codigo(int a02_codigo) {
        this.a02_codigo = a02_codigo;
    }

    public void setA02_Nome(String a02_nome) {
        this.a02_nome = a02_nome;
    }

    public void setA02_Preco(double a02_preco) {
        this.a02_preco = a02_preco;
    }

    public void setA02_Categoria(String a02_categoria) {
        this.a02_categoria = a02_categoria;
    }
}
