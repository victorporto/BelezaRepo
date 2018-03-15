package belezaapp.studiovictor.com.belezaapp;

import android.os.Debug;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import belezaapp.studiovictor.com.belezaapp.ClassesDeDados.Servicos;
import belezaapp.studiovictor.com.belezaapp.Config.ConfigFirebase;
import belezaapp.studiovictor.com.belezaapp.Config.Dados;

public class CriarServicoActivity extends AppCompatActivity {

    //private Cadastros cadastro;
    private Button btnCancelar, btnCriarServico;
    private EditText servicoNome, servicoPreco, servicoTempo;
    private boolean isCamposValidados = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criar_servico);
        //Esconde a 'ActionBar'
        final ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        btnCancelar = (Button) findViewById(R.id.id_btnVoltar);
        btnCriarServico = (Button) findViewById(R.id.id_btnCriarServico);
        servicoNome = (EditText) findViewById(R.id.id_servicoNome);
        servicoPreco = (EditText) findViewById(R.id.id_servicoPreco);
        servicoTempo = (EditText) findViewById(R.id.id_servicoTempo);

        //Recupera os dados que são enviados das classes 'SalaoServicosActivity' ou da 'LstServicoAdapter'.
        //De maneira a determinar se o usuário deseja Editar dados ou Criar um novo serviço.
        final Bundle extras = getIntent().getExtras();

        if(extras != null && extras.getBoolean("editar")) {
            servicoNome.setText("" + Dados.dadosDoSalao.getSalaoServicos().get(extras.getInt("position")).getNomeServico());
            servicoPreco.setText("" + Float.toString(Dados.dadosDoSalao.getSalaoServicos().get(extras.getInt("position")).getPrecoServico()));
            servicoTempo.setText("" + Integer.toString(Dados.dadosDoSalao.getSalaoServicos().get(extras.getInt("position")).getTempoServico()));
        }


        btnCriarServico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            if (extras != null && !extras.getBoolean("editar")) {
                //region criarServico
                if(!servicoComMesmoNome(servicoNome.getText().toString())) {
                    try {
                        if (validarCampos()) {
                            criarServico(servicoNome.getText().toString(), Float.parseFloat(servicoPreco.getText().toString()), Integer.parseInt(servicoTempo.getText().toString()));
                            finish();
                        }
                    } catch (Exception e) {
                        // TODO exception code
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Já existe um serviço com este nome.", Toast.LENGTH_LONG).show();
                }
                //endregion
            } else if (extras != null && extras.getBoolean("editar")) {
                //region editarServico
                try {
                    if (validarCampos()) {
                        editarServico(extras.getInt("position"), servicoNome.getText().toString(), Float.parseFloat(servicoPreco.getText().toString()), Integer.parseInt(servicoTempo.getText().toString()));
                        finish();
                    }
                } catch (Exception e) {
                    // TODO exception code
                }
                //endregion
            }
            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    private boolean validarCampos() {
        isCamposValidados = false;
        //Método para enviar os dados dos quatro EditTexts para DatabaseHelper.inserirDados
        //O sucesso é determinado por uma boolean

        try {

            //Se todos os campos foram preenchidos, a verificação segue adiante.
            if (!servicoNome.getText().toString().isEmpty() &&
                    !servicoPreco.getText().toString().isEmpty() &&
                    !servicoTempo.getText().toString().isEmpty()
                    ) {
                isCamposValidados = true;
            } else {
                Toast.makeText(getApplicationContext(), "Verifique se todos campos foram preenchidos.", Toast.LENGTH_LONG).show();
            }

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "A validação dos campos foi mal sucedida.", Toast.LENGTH_LONG).show();
        }

        return isCamposValidados;
    }

    private void criarServico(String _servicoNome, float _servicoPreco, int _servicoTempo) {

        Servicos servico = new Servicos(_servicoNome, _servicoTempo, _servicoPreco);

        //Verificação se o Salão já foi inicializado ou não.
        if (Dados.dadosDoSalao.getSalaoServicos() == null) {
            Dados.dadosDoSalao.setSalaoServicos(new ArrayList<Servicos>());
            Dados.dadosDoSalao.getSalaoServicos().add(servico);
        } else {
            Dados.dadosDoSalao.getSalaoServicos().add(servico);
        }

        ConfigFirebase.getRefFirebaseTeste().child("Saloes").child(Dados.dadosDoSalao.getSalaoNome()).child("salaoServicos").setValue(Dados.dadosDoSalao.getSalaoServicos());
        Toast.makeText(CriarServicoActivity.this, "Serviço Adicionado.", Toast.LENGTH_SHORT).show();
        finish();
    }

    private void editarServico(int _position, String _servicoNome, float _servicoPreco, int _servicoTempo) {
        Servicos servico = new Servicos(_servicoNome, _servicoTempo, _servicoPreco);

        Dados.dadosDoSalao.getSalaoServicos().set(_position, servico);
        ConfigFirebase.getRefFirebaseTeste().child("Saloes").child(Dados.dadosDoSalao.getSalaoNome()).child("salaoServicos").setValue(Dados.dadosDoSalao.getSalaoServicos());
        Toast.makeText(CriarServicoActivity.this, "Serviço Alterado.", Toast.LENGTH_SHORT).show();
        finish();
    }

    private boolean servicoComMesmoNome(String _servicoNome) {
        for (Servicos servico: Dados.dadosDoSalao.getSalaoServicos()) {
            if (_servicoNome.equals(servico.getNomeServico())) {
                return true;
            }
        }
        return false;
    }
}
