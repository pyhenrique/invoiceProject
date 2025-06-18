package com.myproject.projetonf.Model;

public class ModelCliente {
    private int a01_codigo;         
    private String a01_nome;        
    private String a01_cpf;         
    private String a01_email;      
    private String a01_telefone;   
    private String a01_endereco; 
    
    public ModelCliente() {}
    
    // Getters
    public int getA01_Codigo() {
       return a01_codigo;
    }

    public String getA01_Nome() {
        return a01_nome;
    }

    public String getA01_Cpf() { 
        return a01_cpf;
    }

    public String getA01_Email() {
        return a01_email;
    }

    public String getA01_Telefone() {
        return a01_telefone;
    }

    public String getA01_Endereco() {
        return a01_endereco;
    }
    
     // Setters
    public void setA01_Codigo(int a01_codigo) {
        this.a01_codigo = a01_codigo;
    }

    public void setA01_Nome(String a01_nome) {
        this.a01_nome = a01_nome;
    }

    public void setA01_Cpf(String a01_cpf) { 
        this.a01_cpf = a01_cpf;
    }

    public void setA01_Email(String a01_email) {
        this.a01_email = a01_email;
    }

    public void setA01_Telefone(String a01_telefone) {
        this.a01_telefone = a01_telefone;
    }

    public void setA01_Endereco(String a01_endereco) {
        this.a01_endereco = a01_endereco;
    }
    
}
