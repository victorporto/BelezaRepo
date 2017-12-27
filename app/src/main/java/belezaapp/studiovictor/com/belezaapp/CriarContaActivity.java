package belezaapp.studiovictor.com.belezaapp;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CriarContaActivity extends AppCompatActivity {

    private EditText salaoNome, salaoCNPJ, salaoTelefone, salaoEnderecoBairro, salaoEnderecoRua, salaoEnderecoNumero;
    private EditText nome, cpf, email, senha, confirmarSenha;
    private Button enviarDados;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference().child("Teste");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criar_conta);


        myRef.child("00").setValue("Hello, World!");


        //region INFORMAÇÕES DO SALÃO
        salaoNome = (EditText) findViewById(R.id.campoSalaoNome);
        salaoCNPJ = (EditText) findViewById(R.id.campoSalaoCNPJ);
        salaoTelefone = (EditText) findViewById(R.id.campoSalaoTelefone);
        salaoEnderecoBairro = (EditText) findViewById(R.id.campoSalaoEnderecoBairro);
        salaoEnderecoRua = (EditText) findViewById(R.id.campoSalaoEnderecoRua);
        salaoEnderecoNumero = (EditText) findViewById(R.id.campoSalaoEnderecoRua);
        //endregion

        //region INFORMAÇÕES PESSOAIS
        nome = (EditText)findViewById(R.id.campoNome);
        cpf = (EditText)findViewById(R.id.campoCPF);
        email = (EditText)findViewById(R.id.campoEmail);
        senha = (EditText)findViewById(R.id.campoSenha);
        confirmarSenha = (EditText)findViewById(R.id.campoConfirmarSenha);
        //endregion

        enviarDados = (Button)findViewById(R.id.botaoEnviarDados);

        adicionarDados();
    }

    // Método para enviar os dados dos quatro EditTexts para DatabaseHelper.inserirDados
    // O sucesso é determinado por uma boolean
    public void adicionarDados(){
        enviarDados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    //'If' que verifica se já existe um cadastro com esse 'Email'.
                    //Se o email for encontrado no FireBase, o IF abaixo deve ser chamado.
                    if () {
                        Toast.makeText(getApplicationContext(), "O Email não é válido ou já foi utilizado.", Toast.LENGTH_LONG).show();
                    } else {
                        //Se o 'Email' e a 'Senha' forem diferentes de uma string vazia a verificação segue adiante.
                        if (!email.getText().toString().isEmpty() && !senha.getText().toString().isEmpty()) {
                            //Se as duas senhas digitadas baterem, o App realiza o cadastro.
                            if (senha.getText().toString().equals(confirmarSenha.getText().toString())) {


//                                boolean dadosInseridos = meuDB.inserirDados(nome.getText().toString(),
//                                        cpf.getText().toString(), email.getText().toString(),
//                                        senha.getText().toString());


                                //'dadosInseridos' serve como um boolean de checagem, que verifica se os dados foram inseridos com sucesso (True). Não sei como que vai ficar essa verificação
                                //com o FireBase.
                                if (dadosInseridos) {
                                    Toast.makeText(CriarContaActivity.this, "Conta criada com sucesso!", Toast.LENGTH_LONG).show();
                                    finish();
                                } else {
                                    Toast.makeText(CriarContaActivity.this, "Erro ao enviar dados", Toast.LENGTH_LONG).show();
                                }
                            } else {
                                Toast.makeText(getApplicationContext(), "As senhas digitadas não são iguais.", Toast.LENGTH_LONG).show();
                            }

                        } else {
                            Toast.makeText(getApplicationContext(), "Verifique se os campos essenciais foram preenchidos.", Toast.LENGTH_LONG).show();
                        }

                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
