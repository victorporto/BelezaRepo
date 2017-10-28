package belezaapp.studiovictor.com.belezaapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private Button botaoCriarConta, botaoEntrar;
    private EditText campoEmail, campoSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        //Elementos da tela
        botaoCriarConta = (Button) findViewById(R.id.id_botaoCriarConta);
        botaoEntrar = (Button) findViewById(R.id.id_botaoEntrar);
        campoEmail = (EditText) findViewById(R.id.id_campoEmail);
        campoSenha = (EditText) findViewById(R.id.id_campoSenha);


        //'SetOnClickListeners'
        botaoEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = campoEmail.getText().toString(),
                       senha = campoSenha.getText().toString();

                try {
                    //Abrindo/Criando Banco de Dados da aplicação.
                    SQLiteDatabase bancoDeDados = openOrCreateDatabase("BancoDeDadosTest0", MODE_PRIVATE, null);

                    //Executando SQLs que cria as tabelas 'Tabela_Usuarios' e 'Tabela_SaloesDeBeleza', se não tiverem sido criadas ainda.
                    bancoDeDados.execSQL("CREATE TABLE IF NOT EXISTS Tabela_Usuarios (usuario_nome VARCHAR, usuario_cpf BIGINT, usuario_email VARCHAR, usuario_senha VARCHAR)");
                    bancoDeDados.execSQL("CREATE TABLE IF NOT EXISTS Tabela_SaloesDeBeleza (salao_nome VARCHAR, salao_qntFuncionarios INT, salao_endereco VARCHAR, salao_telefone INT)");

                    //Executando SQL de busca no Banco de Dados e movendo o 'cursor' pra posição inicial dos dados recuperados.
                    Cursor cursor = bancoDeDados.rawQuery("SELECT * FROM Tabela_Usuarios WHERE usuario_email = '" + email + "'", null);
                    cursor.moveToFirst();

                    if (cursor != null) {
                        //Guardando os indices das colunas do 'cursor' dentro de variáveis mais legíveis .
                        int indiceColunaEmail = cursor.getColumnIndex("usuario_email");
                        int indiceColunaSenha = cursor.getColumnIndex("usuario_senha");

                        if (email.equals(cursor.getString(indiceColunaEmail))
                         && senha.equals(cursor.getString(indiceColunaSenha))) {
                            Toast.makeText(getApplicationContext(), "Achou resultado!", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Email ou Senha não encontrados.", Toast.LENGTH_LONG).show();
                        }

                    } else {
                        Toast.makeText(getApplicationContext(), "Email ou Senha não encontrados.", Toast.LENGTH_LONG).show();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


        botaoCriarConta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, CriarContaActivity.class));
            }
        });

    }
}

//TEST
//bancoDeDados.execSQL("INSERT INTO Tabela_Usuarios (usuario_nome, usuario_cpf, usuario_email, usuario_senha) VALUES ('TEST', 01234567890, 'homero@gmail.com', '123')");
//bancoDeDados.execSQL("INSERT INTO Tabela_Usuarios (usuario_nome, usuario_cpf, usuario_email, usuario_senha) VALUES ('TEST2', 01234567890, 'moises@gmail.com', '123456')");