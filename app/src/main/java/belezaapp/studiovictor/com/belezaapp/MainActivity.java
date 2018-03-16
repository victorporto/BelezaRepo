package belezaapp.studiovictor.com.belezaapp;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;


import belezaapp.studiovictor.com.belezaapp.ClassesDeDados.Cadastros;
import belezaapp.studiovictor.com.belezaapp.Config.ConfigFirebase;
import belezaapp.studiovictor.com.belezaapp.Config.Dados;


public class MainActivity extends AppCompatActivity {

    DatabaseReference nodeSaloesRef = ConfigFirebase.getRefFirebaseTeste().child("Saloes");
    //private Cadastros cadastro;
    private TextView salaoNome, salaoCNPJ, salaoBairro, salaoRua, salaoN, salaoTelefone;
    private Button btnServicos, btnFuncionarios, btnLogout;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Esconde a 'ActionBar'
        final ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        toolbar = (Toolbar) findViewById(R.id.id_toolbarMain);
        toolbar.setTitle("Tela Principal");
        //setSupportActionBar(toolbar);

        salaoNome = (TextView) findViewById(R.id.id_salaoNome);
        salaoCNPJ = (TextView) findViewById(R.id.id_salaoCnpj);
        salaoBairro = (TextView) findViewById(R.id.id_salaoBairro);
        salaoRua = (TextView) findViewById(R.id.id_salaoRua);
        salaoN = (TextView) findViewById(R.id.id_salaoNumero);
        salaoTelefone = (TextView) findViewById(R.id.id_salaoTelefone);

        btnServicos = (Button) findViewById(R.id.id_btnServicos);
        btnFuncionarios = (Button) findViewById(R.id.id_btnFuncionarios);
        btnLogout = (Button) findViewById(R.id.id_btnLogOut);

        //Buscando pelo cadastro.
        nodeSaloesRef.orderByChild("email").equalTo(ConfigFirebase.getAuthFirebase().getCurrentUser().getEmail()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Dados.dadosDoSalao = new Cadastros();

                for(DataSnapshot child : dataSnapshot.getChildren()) {
                    Dados.dadosDoSalao = child.getValue(Cadastros.class);
                }
                atualizarDados(Dados.dadosDoSalao);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        btnServicos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SalaoServicosActivity.class);
                startActivity(intent);
            }
        });

        btnFuncionarios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SalaoFuncionariosActivity.class);
                startActivity(intent);
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConfigFirebase.getAuthFirebase().signOut();
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }


    private void atualizarDados(Cadastros _cadastro) {
        salaoNome.setText("Salão: " + _cadastro.getSalaoNome());
        salaoCNPJ.setText("CNPJ: " + _cadastro.getSalaoCNPJ());
        salaoBairro.setText("Bairro: " + _cadastro.getSalaoEnderecoBairro());
        salaoRua.setText("Rua: " + _cadastro.getSalaoEnderecoRua());
        salaoN.setText("Nº: " + _cadastro.getSalaoEnderecoNumero());
        salaoTelefone.setText("Telefone: " + _cadastro.getSalaoTelefone());

        if (_cadastro.getSalaoServicos() != null && !_cadastro.getSalaoServicos().isEmpty()) {
            btnServicos.setText("Serviços: " + _cadastro.getSalaoServicos().size());
        } else {
            btnServicos.setText("Serviços: 0");
        }

        if (_cadastro.getSalaoFuncionarios() != null && !_cadastro.getSalaoFuncionarios().isEmpty()) {
            btnFuncionarios.setText("Funcionários: " + _cadastro.getSalaoFuncionarios().size());
        } else {
            btnFuncionarios.setText("Funcionários: 0");
        }
    }

    /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate();
        return true;
    }
    */
}