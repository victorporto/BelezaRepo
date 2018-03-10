package belezaapp.studiovictor.com.belezaapp.ClassesDeDados;

import java.util.ArrayList;

/**
 * Created by Victor on 30/01/2018.
 */

public class Funcionarios {

    private String nomeFuncionario;
    private ArrayList<String> servicosFuncionario;

    //Alterar os atributos a seguir, substituindo por variáveis apropriadas para DATAS e HORAS.
    private boolean domingo;
    private boolean segunda;
    private boolean terca;
    private boolean quarta;
    private boolean quinta;
    private boolean sexta;
    private boolean sabado;
    private String horarioDeTrabalhoInicio;
    private String horarioDeTrabalhoFim;
    private String horarioDeIntervaloInicio;
    private String horarioDeIntervaloFim;
    //Alterar os atributos a seguir, substituindo por variáveis apropriadas para DATAS e HORAS.

    public Funcionarios() {

    }

    public Funcionarios(String nomeFuncionario,
                        ArrayList<String> servicosFuncionario,
                        boolean domingo,
                        boolean segunda,
                        boolean terca,
                        boolean quarta,
                        boolean quinta,
                        boolean sexta,
                        boolean sabado,
                        String horarioDeTrabalhoInicio,
                        String horarioDeTrabalhoFim,
                        String horarioDeIntervaloInicio,
                        String horarioDeIntervaloFim) {
        this.nomeFuncionario = nomeFuncionario;
        this.servicosFuncionario = servicosFuncionario;
        this.domingo = domingo;
        this.segunda = segunda;
        this.terca = terca;
        this.quarta = quarta;
        this.quinta = quinta;
        this.sexta = sexta;
        this.sabado = sabado;
        this.horarioDeTrabalhoInicio = horarioDeTrabalhoInicio;
        this.horarioDeTrabalhoFim = horarioDeTrabalhoFim;
        this.horarioDeIntervaloInicio = horarioDeIntervaloInicio;
        this.horarioDeIntervaloFim = horarioDeIntervaloFim;
    }

    public String getNomeFuncionario() {
        return nomeFuncionario;
    }

    public void setNomeFuncionario(String nomeFuncionario) {
        this.nomeFuncionario = nomeFuncionario;
    }

    public ArrayList<String> getServicosFuncionario() {
        return servicosFuncionario;
    }

    public void setServicosFuncionario(ArrayList<String> servicosFuncionario) {
        this.servicosFuncionario = servicosFuncionario;
    }

    public boolean isDomingo() {
        return domingo;
    }

    public void setDomingo(boolean domingo) {
        this.domingo = domingo;
    }

    public boolean isSegunda() {
        return segunda;
    }

    public void setSegunda(boolean segunda) {
        this.segunda = segunda;
    }

    public boolean isTerca() {
        return terca;
    }

    public void setTerca(boolean terca) {
        this.terca = terca;
    }

    public boolean isQuarta() {
        return quarta;
    }

    public void setQuarta(boolean quarta) {
        this.quarta = quarta;
    }

    public boolean isQuinta() {
        return quinta;
    }

    public void setQuinta(boolean quinta) {
        this.quinta = quinta;
    }

    public boolean isSexta() {
        return sexta;
    }

    public void setSexta(boolean sexta) {
        this.sexta = sexta;
    }

    public boolean isSabado() {
        return sabado;
    }

    public void setSabado(boolean sabado) {
        this.sabado = sabado;
    }

    public String getHorarioDeTrabalhoInicio() {
        return horarioDeTrabalhoInicio;
    }

    public void setHorarioDeTrabalhoInicio(String horarioDeTrabalhoInicio) {
        this.horarioDeTrabalhoInicio = horarioDeTrabalhoInicio;
    }

    public String getHorarioDeTrabalhoFim() {
        return horarioDeTrabalhoFim;
    }

    public void setHorarioDeTrabalhoFim(String horarioDeTrabalhoFim) {
        this.horarioDeTrabalhoFim = horarioDeTrabalhoFim;
    }

    public String getHorarioDeIntervaloInicio() {
        return horarioDeIntervaloInicio;
    }

    public void setHorarioDeIntervaloInicio(String horarioDeIntervaloInicio) {
        this.horarioDeIntervaloInicio = horarioDeIntervaloInicio;
    }

    public String getHorarioDeIntervaloFim() {
        return horarioDeIntervaloFim;
    }

    public void setHorarioDeIntervaloFim(String horarioDeIntervaloFim) {
        this.horarioDeIntervaloFim = horarioDeIntervaloFim;
    }
}
