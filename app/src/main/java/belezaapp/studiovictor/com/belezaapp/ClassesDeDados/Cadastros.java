package belezaapp.studiovictor.com.belezaapp.ClassesDeDados;

import java.util.ArrayList;

/**
 * Created by Victor on 27/12/2017.
 */

public class Cadastros {

    //region Salão
    private String salaoNome;
    private long salaoCNPJ;
    private long salaoTelefone;
    private String salaoEnderecoBairro;
    private String salaoEnderecoRua;
    private int salaoEnderecoNumero;
    private ArrayList<Servicos> salaoServicos;
    private ArrayList<Funcionarios> salaoFuncionarios;
    //endregion
    //region Pessoais
    private String nome;
    private long CPF;
    private String email;
    //endregion
    //region Utils
    private int statusCadastro;
    //endregion

    public Cadastros() {

    }

    public Cadastros(String salaoNome, long salaoCNPJ, long salaoTelefone, String salaoEnderecoBairro, String salaoEnderecoRua, int salaoEnderecoNumero, String nome, long CPF, String email) {
        this.salaoNome = salaoNome;
        this.salaoCNPJ = salaoCNPJ;
        this.salaoTelefone = salaoTelefone;
        this.salaoEnderecoBairro = salaoEnderecoBairro;
        this.salaoEnderecoRua = salaoEnderecoRua;
        this.salaoEnderecoNumero = salaoEnderecoNumero;

        this.nome = nome;
        this.CPF = CPF;
        this.email = email;

        this.statusCadastro = 0;
    }

    public String getSalaoNome() {
        return salaoNome;
    }

    public void setSalaoNome(String salaoNome) {
        this.salaoNome = salaoNome;
    }

    public long getSalaoCNPJ() {
        return salaoCNPJ;
    }

    public void setSalaoCNPJ(long salaoCNPJ) {
        this.salaoCNPJ = salaoCNPJ;
    }

    public long getSalaoTelefone() {
        return salaoTelefone;
    }

    public void setSalaoTelefone(long salaoTelefone) {
        this.salaoTelefone = salaoTelefone;
    }

    public String getSalaoEnderecoBairro() {
        return salaoEnderecoBairro;
    }

    public void setSalaoEnderecoBairro(String salaoEnderecoBairro) {
        this.salaoEnderecoBairro = salaoEnderecoBairro;
    }

    public String getSalaoEnderecoRua() {
        return salaoEnderecoRua;
    }

    public void setSalaoEnderecoRua(String salaoEnderecoRua) {
        this.salaoEnderecoRua = salaoEnderecoRua;
    }

    public int getSalaoEnderecoNumero() {
        return salaoEnderecoNumero;
    }

    public void setSalaoEnderecoNumero(int salaoEnderecoNumero) {
        this.salaoEnderecoNumero = salaoEnderecoNumero;
    }

    public ArrayList<Servicos> getSalaoServicos() {
        return salaoServicos;
    }

    public void setSalaoServicos(ArrayList<Servicos> salaoServicos) {
        this.salaoServicos = salaoServicos;
    }

    public ArrayList<Funcionarios> getSalaoFuncionarios() {
        return salaoFuncionarios;
    }

    public void setSalaoFuncionarios(ArrayList<Funcionarios> salaoFuncionarios) {
        this.salaoFuncionarios = salaoFuncionarios;
    }

    public int getStatusCadastro() {
        return statusCadastro;
    }

    public void setStatusCadastro(int statusCadastro) {
        this.statusCadastro = statusCadastro;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public long getCPF() {
        return CPF;
    }

    public void setCPF(long CPF) {
        this.CPF = CPF;
    }

    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
    }

}
