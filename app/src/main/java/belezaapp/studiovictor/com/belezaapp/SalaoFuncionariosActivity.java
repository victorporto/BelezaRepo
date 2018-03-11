package belezaapp.studiovictor.com.belezaapp;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import belezaapp.studiovictor.com.belezaapp.ClassesDeDados.Funcionarios;
import belezaapp.studiovictor.com.belezaapp.ClassesDeDados.Servicos;
import belezaapp.studiovictor.com.belezaapp.Config.Dados;
import belezaapp.studiovictor.com.belezaapp.CustomArrayAdapters.LstFuncionariosAdapter;
import belezaapp.studiovictor.com.belezaapp.CustomArrayAdapters.LstServicoAdapter;

public class SalaoFuncionariosActivity extends AppCompatActivity {

    private ListView listViewFuncionarios;
    private Button btnVoltarFuncionario, btnCriarFuncionario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salao_funcionarios);

        //Esconde a 'ActionBar'
        final ActionBar actionBar = getSupportActionBar();
        actionBar.hide();


        listViewFuncionarios = (ListView) findViewById(R.id.id_listvFuncionarios);
        btnVoltarFuncionario = (Button) findViewById(R.id.id_btnVoltarFuncionario);
        btnCriarFuncionario = (Button) findViewById(R.id.id_btnCriarFuncionario);



        btnVoltarFuncionario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnCriarFuncionario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Se já existirem serviços, o usuário poderá criar funcionarios.
                Intent intent = new Intent(SalaoFuncionariosActivity.this, CriarFuncionarioActivity.class);
                intent.putExtra("editar", false);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();

        ArrayList<Funcionarios> funcionarios = new ArrayList<Funcionarios>();
        LstFuncionariosAdapter adapter;
        adapter = new LstFuncionariosAdapter(funcionarios,SalaoFuncionariosActivity.this);
        listViewFuncionarios.setAdapter(adapter);

        //Adicionando serviços na listView.
        //servicos.clear();
        if (Dados.dadosDoSalao.getSalaoFuncionarios() != null && !Dados.dadosDoSalao.getSalaoFuncionarios().isEmpty()) {
            for (Funcionarios _funcionario: Dados.dadosDoSalao.getSalaoFuncionarios()) {
                funcionarios.add(_funcionario);
            }
        }

    }
}
