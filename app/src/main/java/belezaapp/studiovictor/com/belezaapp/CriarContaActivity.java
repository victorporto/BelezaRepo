package belezaapp.studiovictor.com.belezaapp;

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
        enviarDados = (Button)findViewById(R.id.botaoEnviarDados);

        // Limpando os campos antes do usuário digitar, deixando somente os hints
        nome.setText("");
        cpf.setText("");
        email.setText("");
        senha.setText("");

        adicionarDados();
    }

    // Método para enviar os dados dos quatro EditTexts para DatabaseHelper.inserirDados
    // O sucesso é determinado por uma boolean
    public void adicionarDados(){
        enviarDados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean dadosInseridos = meuDB.inserirDados(nome.getText().toString(),
                        cpf.getText().toString(), email.getText().toString(),
                        senha.getText().toString());
                if (dadosInseridos)
                    Toast.makeText(CriarContaActivity.this, "Dados inseridos com sucesso!", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(CriarContaActivity.this, "Erro ao enviar dados", Toast.LENGTH_LONG).show();
            }
        });
    }
}
