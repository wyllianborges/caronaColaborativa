package br.com.up.caronaup;

/**
 * Created by Wyllian on 26/06/2016.
 */
public class ItemExtrato {

    private String tipo;
    private String titulo;
    private String descricao;
    private String valor;

    public ItemExtrato(String tipo, String titulo, String descricao, String valor) {
        this.tipo = tipo;
        this.titulo = titulo;
        this.descricao = descricao;
        this.valor = valor;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
}
