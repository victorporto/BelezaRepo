package belezaapp.studiovictor.com.belezaapp.ClassesDeDados;

/**
 * Created by Victor on 27/12/2017.
 */

public class Cadastros {

    //region Salão
    private String salaoNome;
    private int salaoCNPJ;
    private int salaoTelefone;
    private String salaoEnderecoBairro;
    private String salaoEnderecoRua;
    private int salaoEnderecoNumero;
    //endregion
    //region Pessoais
    private String nome;
    private long CPF;
    private String email;
    private String senha;
    //endregion

    public Cadastros(String salaoNome, int salaoCNPJ, int salaoTelefone, String salaoEnderecoBairro, String salaoEnderecoRua, int salaoEnderecoNumero, String nome, long CPF, String email, String senha) {
        this.salaoNome = salaoNome;
        this.salaoCNPJ = salaoCNPJ;
        this.salaoTelefone = salaoTelefone;
        this.salaoEnderecoBairro = salaoEnderecoBairro;
        this.salaoEnderecoRua = salaoEnderecoRua;
        this.salaoEnderecoNumero = salaoEnderecoNumero;
        this.nome = nome;
        this.CPF = CPF;
        this.email = email;
        this.senha = senha;
    }

    public Cadastros() {

    }

    public String getSalaoNome() {
        return salaoNome;
    }

    public void setSalaoNome(String salaoNome) {
        this.salaoNome = salaoNome;
    }

    public int getSalaoCNPJ() {
        return salaoCNPJ;
    }

    public void setSalaoCNPJ(int salaoCNPJ) {
        this.salaoCNPJ = salaoCNPJ;
    }

    public int getSalaoTelefone() {
        return salaoTelefone;
    }

    public void setSalaoTelefone(int salaoTelefone) {
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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

}
