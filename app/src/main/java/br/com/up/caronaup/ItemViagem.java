package br.com.up.caronaup;

/**
 * Created by Wyllian on 31/08/2016.
 */
public class ItemViagem {

    private String id;
    private String data;
    private String horario;
    private String saida;
    private String destino;
    private String passageiros;

    public ItemViagem(String id, String data, String horario, String saida, String destino, String passageiros) {
        this.id = id;
        this.data = data;
        this.horario = horario;
        this.saida = saida;
        this.destino = destino;
        this.passageiros = passageiros;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getSaida() {
        return saida;
    }

    public void setSaida(String saida) {
        this.saida = saida;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getPassageiros() {
        return passageiros;
    }

    public void setPassageiros(String passageiros) {
        this.passageiros = passageiros;
    }
}
