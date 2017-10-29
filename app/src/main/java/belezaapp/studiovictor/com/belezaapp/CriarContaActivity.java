package belezaapp.studiovictor.com.belezaapp;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CriarContaActivity extends AppCompatActivity {

    DatabaseHelper meuDB;
    private EditText nome;
    private EditText cpf;
    private EditText email;
    private EditText senha;
    private EditText confirmarSenha;
    private Button enviarDados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criar_conta);

        meuDB = new DatabaseHelper(this);

        nome = (EditText)findViewById(R.id.campoNome);
        cpf = (EditText)findViewById(R.id.campoCPF);
        email = (EditText)findViewById(R.id.campoEmail);
        senha = (EditText)findViewById(R.id.campoSenha);
        confirmarSenha = (EditText)findViewById(R.id.campoConfirmarSenha);
        enviarDados = (Button)findViewById(R.id.botaoEnviarDados);

        /* Eu limpei a propriedade 'text' dos elementos do layout, não sendo mais necessário limpá-los via código.
        // Limpando os campos antes do usuário digitar, deixando somente os hints
        nome.setText("");
        cpf.setText("");
        email.setText("");
        senha.setText("");
        */

        adicionarDados();
    }

    // Método para enviar os dados dos quatro EditTexts para DatabaseHelper.inserirDados
    // O sucesso é determinado por uma boolean
    public void adicionarDados(){
        enviarDados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Cursor cursor = meuDB.pegarDados(email.getText().toString());

                    //'If' que verifica se já existe um cadastro com esse 'Email'.
                    //Se cair no 'if' é porque a busca retornou um resultado, e o email já foi utilizado.
                    if (cursor.getCount()>0) {
                        Toast.makeText(getApplicationContext(), "O Email não é válido ou já foi utilizado.", Toast.LENGTH_LONG).show();

                    } else {
                        //Se o 'Email' e a 'Senha' forem diferentes de uma string vazia a verificação segue adiante.
                        if (!email.getText().toString().isEmpty() && !senha.getText().toString().isEmpty()) {
                            //Se as duas senhas digitadas baterem, o App realiza o cadastro.
                            if (senha.getText().toString().equals(confirmarSenha.getText().toString())) {

                                boolean dadosInseridos = meuDB.inserirDados(nome.getText().toString(),
                                        cpf.getText().toString(), email.getText().toString(),
                                        senha.getText().toString());
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
