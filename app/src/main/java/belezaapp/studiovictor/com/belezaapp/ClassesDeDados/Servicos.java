package belezaapp.studiovictor.com.belezaapp.ClassesDeDados;

/**
 * Created by Victor on 29/01/2018.
 */

public class Servicos {

    private String nomeServico;
    private int tempoServico;
    private float precoServico;

    public Servicos() {

    }

    public Servicos(String nomeServico, int tempoServico, float precoServico) {
        this.nomeServico = nomeServico;
        this.tempoServico = tempoServico;
        this.precoServico = precoServico;
    }

    public String getNomeServico() {
        return nomeServico;
    }

    public void setNomeServico(String nomeServico) {
        this.nomeServico = nomeServico;
    }

    public int getTempoServico() {
        return tempoServico;
    }

    public void setTempoServico(int tempoServico) {
        this.tempoServico = tempoServico;
    }

    public float getPrecoServico() {
        return precoServico;
    }

    public void setPrecoServico(float precoServico) {
        this.precoServico = precoServico;
    }
}
