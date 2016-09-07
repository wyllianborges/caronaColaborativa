package br.com.up.caronaup;

/**
 * Created by Wyllian on 31/08/2016.
 */
public class ItemPassageiro {

    private String id;
    private String nome;
    private String descricao;
    private int photo;

    public ItemPassageiro(String id, String nome, String descricao, int photo) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.photo = photo;
    }

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
