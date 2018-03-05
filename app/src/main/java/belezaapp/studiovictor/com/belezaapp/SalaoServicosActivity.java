package belezaapp.studiovictor.com.belezaapp;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import belezaapp.studiovictor.com.belezaapp.ClassesDeDados.Cadastros;
import belezaapp.studiovictor.com.belezaapp.ClassesDeDados.Servicos;
import belezaapp.studiovictor.com.belezaapp.Config.ConfigFirebase;
import belezaapp.studiovictor.com.belezaapp.Config.Dados;
import belezaapp.studiovictor.com.belezaapp.CustomArrayAdapters.LstServicoAdapter;

public class SalaoServicosActivity extends AppCompatActivity {

    private ListView listViewServicos;
    private Button btnVoltar, btnCriarServico;
    //private Cadastros cadastro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salao_servicos);

        //Esconde a 'ActionBar'
        final ActionBar actionBar = getSupportActionBar();
        actionBar.hide();


        listViewServicos = (ListView) findViewById(R.id.id_listvServicos);
        btnVoltar = (Button) findViewById(R.id.id_btnVoltar);
        btnCriarServico = (Button) findViewById(R.id.id_btnCriarServico);



        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnCriarServico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SalaoServicosActivity.this, CriarServicoActivity.class);
                intent.putExtra("editar", false);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();  // Always call the superclass method first

         ArrayList<Servicos> servicos = new ArrayList<Servicos>();
        LstServicoAdapter adapter;
        adapter = new LstServicoAdapter(servicos, Dados.dadosDoSalao.getSalaoNome(),SalaoServicosActivity.this);
        listViewServicos.setAdapter(adapter);

        //Adicionando serviços na listView.
        //servicos.clear();
        if (Dados.dadosDoSalao.getSalaoServicos() != null && !Dados.dadosDoSalao.getSalaoServicos().isEmpty()) {
            for (Servicos _servico: Dados.dadosDoSalao.getSalaoServicos()) {
                servicos.add(_servico);
            }
        }

    }
}
