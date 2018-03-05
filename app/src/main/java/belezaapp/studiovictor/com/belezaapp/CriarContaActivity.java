package belezaapp.studiovictor.com.belezaapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import belezaapp.studiovictor.com.belezaapp.ClassesDeDados.Cadastros;
import belezaapp.studiovictor.com.belezaapp.Config.ConfigFirebase;

public class CriarContaActivity extends AppCompatActivity {

    private EditText salaoNome, salaoCNPJ, salaoTelefone, salaoEnderecoBairro, salaoEnderecoRua, salaoEnderecoNumero;
    private EditText nome, cpf, email, senha, confirmarSenha;
    private Button enviarDados;
    private static final String TAG = "CriarContaActivity";
    boolean resultadoValidarCampos = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criar_conta);
        //Esconde a 'ActionBar'
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        //region INFORMAÇÕES DO SALÃO
        salaoNome = (EditText) findViewById(R.id.campoSalaoNome);
        salaoCNPJ = (EditText) findViewById(R.id.campoSalaoCNPJ);
        salaoTelefone = (EditText) findViewById(R.id.campoSalaoTelefone);
        salaoEnderecoBairro = (EditText) findViewById(R.id.campoSalaoEnderecoBairro);
        salaoEnderecoRua = (EditText) findViewById(R.id.campoSalaoEnderecoRua);
        salaoEnderecoNumero = (EditText) findViewById(R.id.campoSalaoEnderecoNumero);
        //endregion

        //region INFORMAÇÕES PESSOAIS
        nome = (EditText) findViewById(R.id.campoNome);
        cpf = (EditText) findViewById(R.id.campoCPF);
        email = (EditText) findViewById(R.id.campoEmail);
        senha = (EditText) findViewById(R.id.campoSenha);
        confirmarSenha = (EditText) findViewById(R.id.campoConfirmarSenha);
        //endregion

        enviarDados = (Button) findViewById(R.id.botaoEnviarDados);

        // adicionarDados();

        enviarDados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (validarCampos()) {
                        criarConta(email.getText().toString(), senha.getText().toString());
                    }
                } catch (Exception e) {
                    // TODO exception code
                }
            }

        });

    }

    private boolean validarCampos () {

        resultadoValidarCampos = false;
        //Método para enviar os dados dos quatro EditTexts para DatabaseHelper.inserirDados
        //O sucesso é determinado por uma boolean

        try {

            //Se todos os campos foram preenchidos, a verificação segue adiante.
            if (!salaoNome.getText().toString().isEmpty() &&
                    !salaoCNPJ.getText().toString().isEmpty() &&
                    !salaoTelefone.getText().toString().isEmpty() &&
                    !salaoEnderecoBairro.getText().toString().isEmpty() &&
                    !salaoEnderecoRua.getText().toString().isEmpty() &&
                    !salaoEnderecoNumero.getText().toString().isEmpty() &&
                    !nome.getText().toString().isEmpty() &&
                    !cpf.getText().toString().isEmpty() &&
                    !email.getText().toString().isEmpty() &&
                    !senha.getText().toString().isEmpty()
                    )
            {
                //Se as duas senhas digitadas baterem, o App realiza o cadastro.
                if (senha.getText().toString().equals(confirmarSenha.getText().toString())) {
                    resultadoValidarCampos = true;
                } else {
                    Toast.makeText(getApplicationContext(), "As senhas digitadas não são iguais.", Toast.LENGTH_LONG).show();
                }

            } else {
                Toast.makeText(getApplicationContext(), "Verifique se todos campos foram preenchidos.", Toast.LENGTH_LONG).show();
            }

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "A validação dos campos foi mal sucedida.", Toast.LENGTH_LONG).show();
        }

        return resultadoValidarCampos;
    }

    private void criarConta(String _email, String _password) {
        ConfigFirebase.getAuthFirebase().createUserWithEmailAndPassword(_email, _password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            //Inserir dados dentro de objetos
                            Cadastros cadastro = new Cadastros(salaoNome.getText().toString(), Long.parseLong(salaoCNPJ.getText().toString()), Long.parseLong(salaoTelefone.getText().toString()), salaoEnderecoBairro.getText().toString(), salaoEnderecoRua.getText().toString(), Integer.parseInt(salaoEnderecoNumero.getText().toString()), nome.getText().toString(), Long.parseLong(cpf.getText().toString()), email.getText().toString());
                            //Inserir objetos dentro do banco de dados
                            ConfigFirebase.getRefFirebaseTeste().child("Saloes").child(cadastro.getSalaoNome()).setValue(cadastro);
                            ConfigFirebase.getAuthFirebase().signOut();
                            Toast.makeText(CriarContaActivity.this, "Cadastro feito.", Toast.LENGTH_SHORT).show();
                            finish();

                        } else {
                            // Falha ao criar conta, mostrar mensagem de erro para o usuário.
                            Toast.makeText(CriarContaActivity.this, "Cadastro falhou. " + task.getException().toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}