package belezaapp.studiovictor.com.belezaapp;

import android.app.TimePickerDialog;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.BoringLayout;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import belezaapp.studiovictor.com.belezaapp.ClassesDeDados.Funcionarios;
import belezaapp.studiovictor.com.belezaapp.ClassesDeDados.Servicos;
import belezaapp.studiovictor.com.belezaapp.Config.ConfigFirebase;
import belezaapp.studiovictor.com.belezaapp.Config.Dados;
import belezaapp.studiovictor.com.belezaapp.CustomArrayAdapters.LstServicoDoFuncionarioAdapter;

public class CriarFuncionarioActivity extends AppCompatActivity {

    private TextView trabalhoInicio, trabalhoFim, intervaloInicio, intervaloFim;
    private CheckBox domCheckBox, segCheckBox, terCheckBox, quaCheckBox, quiCheckBox, sexCheckBox, sabCheckBox;
    private Button btnCancelar, btnCriarFuncionario;
    private ListView listViewServicosDoFuncionario;
    private EditText funcionarioNome;
    private ArrayList<String> servicosDoSalao = nomesServicosSalao();
    private ArrayList<String> servicosDoFuncionario = new ArrayList<String>();
    //Os dois ArrayLists são utilizados pela classe LstServicoDoFuncionarioAdapter.
    public static ArrayList<String> servicosAdicionados = new ArrayList<String>();
    public static ArrayList<String> servicosRemovidos = new ArrayList<String>();

    private boolean isCamposValidados = false;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criar_funcionario);
        //Esconde a 'ActionBar'
        final ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        toolbar = (Toolbar) findViewById(R.id.id_toolbarCriarFuncionario);
        toolbar.setTitle("Funcionário");

        final Bundle extras = getIntent().getExtras();

        //region Inicializando elementos da View
        funcionarioNome = (EditText) findViewById(R.id.id_edtFuncionarioNome);

        trabalhoInicio = (TextView) findViewById(R.id.id_txtTrabalhoInicio);
        trabalhoFim = (TextView) findViewById(R.id.id_txtTrabalhoFim);
        intervaloInicio = (TextView) findViewById(R.id.id_txtIntervaloInicio);
        intervaloFim = (TextView) findViewById(R.id.id_txtIntervaloFim);

        domCheckBox = (CheckBox) findViewById(R.id.id_domCriarFuncCheckBox);
        segCheckBox = (CheckBox) findViewById(R.id.id_segCriarFuncCheckBox);
        terCheckBox = (CheckBox) findViewById(R.id.id_terCriarFuncCheckBox);
        quaCheckBox = (CheckBox) findViewById(R.id.id_quaCriarFuncCheckBox);
        quiCheckBox = (CheckBox) findViewById(R.id.id_quiCriarFuncCheckBox);
        sexCheckBox = (CheckBox) findViewById(R.id.id_sexCriarFuncCheckBox);
        sabCheckBox = (CheckBox) findViewById(R.id.id_sabCriarFuncCheckBox);

        listViewServicosDoFuncionario = (ListView) findViewById(R.id.id_listvServicosDoFuncionario);

        btnCancelar = (Button) findViewById(R.id.id_btnCancelarFunc);
        btnCriarFuncionario = (Button) findViewById(R.id.id_btnCriarFunc);
        //endregion

        //region Se EDITAR for true, recuperar os dados e preencher os elementos


        final LstServicoDoFuncionarioAdapter adapter;


        if(extras != null && extras.getBoolean("editar")) {
            funcionarioNome.setText("" + Dados.dadosDoSalao.getSalaoFuncionarios().get(extras.getInt("position")).getNomeFuncionario());
            trabalhoInicio.setText("" + Dados.dadosDoSalao.getSalaoFuncionarios().get(extras.getInt("position")).getHorarioDeTrabalhoInicio());
            trabalhoFim.setText("" + Dados.dadosDoSalao.getSalaoFuncionarios().get(extras.getInt("position")).getHorarioDeTrabalhoFim());
            intervaloInicio.setText("" + Dados.dadosDoSalao.getSalaoFuncionarios().get(extras.getInt("position")).getHorarioDeIntervaloInicio());
            intervaloFim.setText("" + Dados.dadosDoSalao.getSalaoFuncionarios().get(extras.getInt("position")).getHorarioDeIntervaloFim());
            domCheckBox.setChecked(Dados.dadosDoSalao.getSalaoFuncionarios().get(extras.getInt("position")).isDomingo());
            segCheckBox.setChecked(Dados.dadosDoSalao.getSalaoFuncionarios().get(extras.getInt("position")).isSegunda());
            terCheckBox.setChecked(Dados.dadosDoSalao.getSalaoFuncionarios().get(extras.getInt("position")).isTerca());
            quaCheckBox.setChecked(Dados.dadosDoSalao.getSalaoFuncionarios().get(extras.getInt("position")).isQuarta());
            quiCheckBox.setChecked(Dados.dadosDoSalao.getSalaoFuncionarios().get(extras.getInt("position")).isQuinta());
            sexCheckBox.setChecked(Dados.dadosDoSalao.getSalaoFuncionarios().get(extras.getInt("position")).isSexta());
            sabCheckBox.setChecked(Dados.dadosDoSalao.getSalaoFuncionarios().get(extras.getInt("position")).isSabado());

            servicosDoFuncionario = recuperarServicosDoFuncionario(extras.getInt("position"));

            adapter = new LstServicoDoFuncionarioAdapter(servicosDoSalao, servicosDoFuncionario,CriarFuncionarioActivity.this);
            listViewServicosDoFuncionario.setAdapter(adapter);

        } else {
            adapter = new LstServicoDoFuncionarioAdapter(servicosDoSalao,CriarFuncionarioActivity.this);
            listViewServicosDoFuncionario.setAdapter(adapter);
            listViewServicosDoFuncionario.getItemIdAtPosition(0);
        }
        //endregion

        //region Eventos onClickListeners dos TextViews/TimePickers
        trabalhoInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(CriarFuncionarioActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        trabalhoInicio.setText("" + Integer.toString(hourOfDay) + ":" + Integer.toString(minute));
                    }
                }, 0, 0, true);
                timePickerDialog.show();
            }
        });

        trabalhoFim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(CriarFuncionarioActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        trabalhoFim.setText("" + Integer.toString(hourOfDay) + ":" + Integer.toString(minute));
                    }
                }, 0, 0, true);
                timePickerDialog.show();
            }
        });

        intervaloInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(CriarFuncionarioActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        intervaloInicio.setText("" + Integer.toString(hourOfDay) + ":" + Integer.toString(minute));
                    }
                }, 0, 0, true);
                timePickerDialog.show();
            }
        });

        intervaloFim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(CriarFuncionarioActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        intervaloFim.setText("" + Integer.toString(hourOfDay) + ":" + Integer.toString(minute));
                    }
                }, 0, 0, true);
                timePickerDialog.show();
            }
        });
        //endregion

        //region Habilita/Desabilita o sensor Touch do ScrollView, permitindo que o ListView detecte o Touch
        listViewServicosDoFuncionario.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int action = motionEvent.getAction();
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        view.getParent().requestDisallowInterceptTouchEvent(true);
                        break;

                    case MotionEvent.ACTION_UP:
                        view.getParent().requestDisallowInterceptTouchEvent(false);
                        break;
                }
                view.onTouchEvent(motionEvent);
                return true;
            }
        });
        //endregion

        //region onClickListeners dos Buttons
        btnCriarFuncionario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            if (extras != null && !extras.getBoolean("editar")) {
                //region criarFuncionario
                if(!funcionarioComMesmoNome(funcionarioNome.getText().toString())) {
                    try {
                        if (validarCampos()) {
                            criarFuncionario(funcionarioNome.getText().toString(),
                                    trabalhoInicio.getText().toString(),
                                    trabalhoFim.getText().toString(),
                                    intervaloInicio.getText().toString(),
                                    intervaloFim.getText().toString(),
                                    domCheckBox.isChecked(),
                                    segCheckBox.isChecked(),
                                    terCheckBox.isChecked(),
                                    quaCheckBox.isChecked(),
                                    quiCheckBox.isChecked(),
                                    sexCheckBox.isChecked(),
                                    sabCheckBox.isChecked()
                            );
                            finish();
                        }
                    } catch (Exception e) {
                        // TODO exception code
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Já existe um funcionário com este nome.", Toast.LENGTH_LONG).show();
                }
                //endregion
            } else if (extras != null && extras.getBoolean("editar")) {
                //region editarFuncionario
                try {
                    if (validarCampos()) {
                        editarFuncionario(extras.getInt("position"),
                                funcionarioNome.getText().toString(),
                                trabalhoInicio.getText().toString(),
                                trabalhoFim.getText().toString(),
                                intervaloInicio.getText().toString(),
                                intervaloFim.getText().toString(),
                                domCheckBox.isChecked(),
                                segCheckBox.isChecked(),
                                terCheckBox.isChecked(),
                                quaCheckBox.isChecked(),
                                quiCheckBox.isChecked(),
                                sexCheckBox.isChecked(),
                                sabCheckBox.isChecked()
                        );
                        finish();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    // TODO exception code
                }
                //endregion
            }
            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //endregion
    }

    private ArrayList<String> nomesServicosSalao() {
        ArrayList<String> listaServicos = new ArrayList<String>();
        if(Dados.dadosDoSalao.getSalaoServicos() != null && !Dados.dadosDoSalao.getSalaoServicos().isEmpty()) {
            for (Servicos _servico : Dados.dadosDoSalao.getSalaoServicos()) {
                listaServicos.add(_servico.getNomeServico());
            }
        }
        return listaServicos;
    }

    private ArrayList<String> recuperarServicosDoFuncionario(int _position) {
        ArrayList<String> _servicosDoFuncionario = new ArrayList<String>();
        if (Dados.dadosDoSalao.getSalaoFuncionarios().get(_position).getServicosFuncionario() != null
                && !Dados.dadosDoSalao.getSalaoFuncionarios().get(_position).getServicosFuncionario().isEmpty())
        {
            _servicosDoFuncionario = Dados.dadosDoSalao.getSalaoFuncionarios().get(_position).getServicosFuncionario();
        }
        return _servicosDoFuncionario;
    }

    private boolean validarCampos() {
        isCamposValidados = false;

        try {
            //Se todos os campos foram preenchidos, a verificação segue adiante.
            if (!funcionarioNome.getText().toString().isEmpty() &&
                    !trabalhoInicio.getText().toString().isEmpty() &&
                    !trabalhoFim.getText().toString().isEmpty() &&
                    !intervaloInicio.getText().toString().isEmpty() &&
                    !intervaloFim.getText().toString().isEmpty()
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

    private void criarFuncionario(String _funcionarioNome,
                                  String _trabalhoInicio,
                                  String _trabalhoFim,
                                  String _intervaloInicio,
                                  String _intervaloFim,
                                  Boolean _dom,
                                  Boolean _seg,
                                  Boolean _ter,
                                  Boolean _qua,
                                  Boolean _qui,
                                  Boolean _sex,
                                  Boolean _sab) {

        ArrayList<String> _servicos = servicosAdicionados;
        Funcionarios funcionario = new Funcionarios(_funcionarioNome,
                                                    _servicos,
                                                    _dom,
                                                    _seg,
                                                    _ter,
                                                    _qua,
                                                    _qui,
                                                    _sex,
                                                    _sab,
                                                    _trabalhoInicio,
                                                    _trabalhoFim,
                                                    _intervaloInicio,
                                                    _intervaloFim
                                                    );

        //Verificação se o atributo 'SalaoFuncionarios' já foi inicializado ou não.
        if (Dados.dadosDoSalao.getSalaoFuncionarios() == null) {
            Dados.dadosDoSalao.setSalaoFuncionarios(new ArrayList<Funcionarios>());
            Dados.dadosDoSalao.getSalaoFuncionarios().add(funcionario);
        } else {
            Dados.dadosDoSalao.getSalaoFuncionarios().add(funcionario);
        }

        ConfigFirebase.getRefFirebaseTeste().child("Saloes").child(Dados.dadosDoSalao.getSalaoNome()).child("salaoFuncionarios").setValue(Dados.dadosDoSalao.getSalaoFuncionarios());
        Toast.makeText(CriarFuncionarioActivity.this, "Funcionário adicionado.", Toast.LENGTH_SHORT).show();
        finish();
    }

    //------------É NECESSÁRIO PASSAR COMO PARAMETRO UMA LISTA DE STRING REPRESENTANDO OS SERVIÇOS AINDA!---------------
    private void editarFuncionario(int _position,
                                   String _funcionarioNome,
                                   String _trabalhoInicio,
                                   String _trabalhoFim,
                                   String _intervaloInicio,
                                   String _intervaloFim,
                                   Boolean _dom,
                                   Boolean _seg,
                                   Boolean _ter,
                                   Boolean _qua,
                                   Boolean _qui,
                                   Boolean _sex,
                                   Boolean _sab) {

        ArrayList<String> _servicos = servicosDoFuncionario;
        _servicos = adicionarServicos(_servicos, servicosAdicionados);
        _servicos = removerServicos(_servicos, servicosRemovidos);
        Funcionarios funcionario = new Funcionarios(_funcionarioNome,
                                                    _servicos,
                                                    _dom,
                                                    _seg,
                                                    _ter,
                                                    _qua,
                                                    _qui,
                                                    _sex,
                                                    _sab,
                                                    _trabalhoInicio,
                                                    _trabalhoFim,
                                                    _intervaloInicio,
                                                    _intervaloFim
                                                    );

        Dados.dadosDoSalao.getSalaoFuncionarios().set(_position, funcionario);
        ConfigFirebase.getRefFirebaseTeste().child("Saloes").child(Dados.dadosDoSalao.getSalaoNome()).child("salaoFuncionarios").setValue(Dados.dadosDoSalao.getSalaoFuncionarios());
        Toast.makeText(CriarFuncionarioActivity.this, "Funcionário alterado.", Toast.LENGTH_SHORT).show();
        finish();
    }

    private boolean funcionarioComMesmoNome(String _funcionarioNome) {
        if(Dados.dadosDoSalao.getSalaoFuncionarios() != null && !Dados.dadosDoSalao.getSalaoFuncionarios().isEmpty()) {
            for (Funcionarios funcionario : Dados.dadosDoSalao.getSalaoFuncionarios()) {
                if (_funcionarioNome.equals(funcionario.getNomeFuncionario())) {
                    return true;
                }
            }
        }
        return false;
    }

    private ArrayList<String> adicionarServicos(ArrayList<String> _servicos, ArrayList<String> _servicosAAdicionar) {
        for (String _servico : _servicosAAdicionar) {
            if(!_servicos.contains(_servico)){
                _servicos.add(_servico);
            }
        }
        return _servicos;
    }

    private ArrayList<String> removerServicos(ArrayList<String> _servicos, ArrayList<String> _servicosARemover) {
        for (String _servico : _servicosARemover){
            _servicos.remove(_servico);
        }
        return _servicos;
    }

}
