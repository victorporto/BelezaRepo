package belezaapp.studiovictor.com.belezaapp.ClassesDeDados;

import java.util.ArrayList;

/**
 * Created by Victor on 30/01/2018.
 */

public class Funcionarios {

    private String nomeFuncionario;
    private ArrayList<String> servicosFuncionario;

    //Alterar os atributos a seguir, substituindo por variáveis apropriadas para DATAS e HORAS.
    private ArrayList<String> diasDeTrabalhoSemanal;
    private int horarioDeTrabalhoInicio;
    private int horarioDeTrabalhoFim;
    private int horarioDeIntervaloInicio;
    private int horarioDeIntervaloFim;
    //Alterar os atributos a seguir, substituindo por variáveis apropriadas para DATAS e HORAS.

    public Funcionarios() {

    }

    public Funcionarios(String nomeFuncionario) {
        this.nomeFuncionario = nomeFuncionario;
        this.servicosFuncionario = new ArrayList<String>();
        this.servicosFuncionario.add("0");
        this.diasDeTrabalhoSemanal = new ArrayList<String>();
        this.diasDeTrabalhoSemanal.add("Segunda");
        this.diasDeTrabalhoSemanal.add("Terça");
        this.diasDeTrabalhoSemanal.add("Quarta");
        this.diasDeTrabalhoSemanal.add("Quinta");
        this.diasDeTrabalhoSemanal.add("Sexta");
        this.horarioDeTrabalhoInicio = 0;
        this.horarioDeTrabalhoFim = 0;
        this.horarioDeIntervaloInicio = 0;
        this.horarioDeIntervaloFim = 0;
    }

    public Funcionarios(String nomeFuncionario, ArrayList<String> servicosFuncionario, ArrayList<String> diasDeTrabalhoSemanal, int horarioDeTrabalhoInicio, int horarioDeTrabalhoFim, int horarioDeIntervaloInicio, int horarioDeIntervaloFim) {
        this.nomeFuncionario = nomeFuncionario;
        this.servicosFuncionario = servicosFuncionario;
        this.diasDeTrabalhoSemanal = diasDeTrabalhoSemanal;
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

    public ArrayList<String> getDiasDeTrabalhoSemanal() {
        return diasDeTrabalhoSemanal;
    }

    public void setDiasDeTrabalhoSemanal(ArrayList<String> diasDeTrabalhoSemanal) {
        this.diasDeTrabalhoSemanal = diasDeTrabalhoSemanal;
    }

    public int getHorarioDeTrabalhoInicio() {
        return horarioDeTrabalhoInicio;
    }

    public void setHorarioDeTrabalhoInicio(int horarioDeTrabalhoInicio) {
        this.horarioDeTrabalhoInicio = horarioDeTrabalhoInicio;
    }

    public int getHorarioDeTrabalhoFim() {
        return horarioDeTrabalhoFim;
    }

    public void setHorarioDeTrabalhoFim(int horarioDeTrabalhoFim) {
        this.horarioDeTrabalhoFim = horarioDeTrabalhoFim;
    }

    public int getHorarioDeIntervaloInicio() {
        return horarioDeIntervaloInicio;
    }

    public void setHorarioDeIntervaloInicio(int horarioDeIntervaloInicio) {
        this.horarioDeIntervaloInicio = horarioDeIntervaloInicio;
    }

    public int getHorarioDeIntervaloFim() {
        return horarioDeIntervaloFim;
    }

    public void setHorarioDeIntervaloFim(int horarioDeIntervaloFim) {
        this.horarioDeIntervaloFim = horarioDeIntervaloFim;
    }
}
