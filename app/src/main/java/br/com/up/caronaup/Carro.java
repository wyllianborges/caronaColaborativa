package br.com.up.caronaup;


public class Carro {
    private String marca;
    private String modelo;
    private String ano;
    private String cor;
    private String capacidade;
    private int photo;



    //Teste eduardo

    public Carro(){}

    public Carro(String marca, String modelo, int photo) {
        this.marca = marca;
        this.modelo = modelo;
        this.photo = photo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(String capacidade) {
        this.capacidade = capacidade;
    }

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }
}
