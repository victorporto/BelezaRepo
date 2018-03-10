package belezaapp.studiovictor.com.belezaapp.CustomArrayAdapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import belezaapp.studiovictor.com.belezaapp.ClassesDeDados.Funcionarios;
import belezaapp.studiovictor.com.belezaapp.Config.ConfigFirebase;
import belezaapp.studiovictor.com.belezaapp.Config.Dados;
import belezaapp.studiovictor.com.belezaapp.CriarFuncionarioActivity;
import belezaapp.studiovictor.com.belezaapp.R;

/**
 * Created by Moisés on 07/03/2018.
 */

public class LstFuncionariosAdapter extends BaseAdapter implements ListAdapter {

    private ArrayList<Funcionarios> listaFuncionarios = new ArrayList<Funcionarios>();
    private Context context;

    public LstFuncionariosAdapter(ArrayList<Funcionarios> _listaFuncionarios, Context context) {
        this.listaFuncionarios = _listaFuncionarios;
        this.context = context;
    }

    @Override
    public int getCount() {
        return listaFuncionarios.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    /*
    @Override
    public Object getItem(int pos) {
        return list.get(pos);
    }
    */

    @Override
    public long getItemId(int i) {
        return 0;
    }

    public void remove() {

    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.layout_listadefuncionarios, null);
        }

        //region ELEMENTOS DA VIEW
        TextView funcionarioNome = (TextView)view.findViewById(R.id.id_funcionarioNome);
        TextView horarioTrabalho = (TextView)view.findViewById(R.id.id_horarioTrabalho);
        TextView horarioIntervalo = (TextView)view.findViewById(R.id.id_horarioIntervalo);
        CheckBox dom = (CheckBox)view.findViewById(R.id.id_domCheckBox);
        CheckBox seg = (CheckBox)view.findViewById(R.id.id_segCheckBox);
        CheckBox ter = (CheckBox)view.findViewById(R.id.id_terCheckBox);
        CheckBox qua = (CheckBox)view.findViewById(R.id.id_quaCheckBox);
        CheckBox qui = (CheckBox)view.findViewById(R.id.id_quiCheckBox);
        CheckBox sex = (CheckBox)view.findViewById(R.id.id_sexCheckBox);
        CheckBox sab = (CheckBox)view.findViewById(R.id.id_sabCheckBox);
        //endregion

        //region Atribuindo Valores aos Elementos da View
        funcionarioNome.setText(listaFuncionarios.get(position).getNomeFuncionario());
            //Converter os horários para STRING
        horarioTrabalho.setText(listaFuncionarios.get(position).getHorarioDeTrabalhoInicio() + " - " + listaFuncionarios.get(position).getHorarioDeTrabalhoFim());
        horarioIntervalo.setText(listaFuncionarios.get(position).getHorarioDeIntervaloInicio() + " - " + listaFuncionarios.get(position).getHorarioDeIntervaloFim());
        dom.setChecked(listaFuncionarios.get(position).isDomingo());
        seg.setChecked(listaFuncionarios.get(position).isSegunda());
        ter.setChecked(listaFuncionarios.get(position).isTerca());
        qua.setChecked(listaFuncionarios.get(position).isQuarta());
        qui.setChecked(listaFuncionarios.get(position).isQuinta());
        sex.setChecked(listaFuncionarios.get(position).isSexta());
        sab.setChecked(listaFuncionarios.get(position).isSabado());
        //endregion

        //Butões e onClickListeners
        ImageButton btnEditar = (ImageButton) view.findViewById(R.id.id_funcionarioBtnEditar);
        ImageButton btnDeletar = (ImageButton) view.findViewById(R.id.id_funcionarioBtnDeletar);

        btnEditar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //A classe CriarFuncionariosActivity serve tanto para criar novos Funcionários quanto para editar funcionários
                // já existentes.
                //É passado a 'position' e o 'editar' com valor 'true' para a activity. Com tais informações
                // a CriarFuncionarioActivity consegue editar os valores já existentes.
                Intent intent = new Intent(context, CriarFuncionarioActivity.class);
                intent.putExtra("editar", true);
                intent.putExtra("position", position);
                context.startActivity(intent);
                notifyDataSetChanged();
            }
        });
        btnDeletar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //Remover do Dados.dadosDoSalao.getFuncionariosSalao() o Funcionario na posição correta e depois reordenar o ArrayList<Funcionarios>()
                int tamanho = Dados.dadosDoSalao.getSalaoFuncionarios().size();
                tamanho--;
                Dados.dadosDoSalao.getSalaoFuncionarios().remove(position);

                //Necessário remover a ÚLTIMA POSIÇÃO do Firebase, fazendo com que os dois ArrayList<Funcionarios> possuam as mesmas posições e tamanhos.
                ConfigFirebase.getRefFirebaseTeste().child("Saloes").child(Dados.dadosDoSalao.getSalaoNome()).child("salaoFuncionarios").child(Integer.toString(tamanho)).removeValue();

                //Subir o Dados.dadosDoSalão.getFuncionariosSalao() no Firebase.
                ConfigFirebase.getRefFirebaseTeste().child("Saloes").child(Dados.dadosDoSalao.getSalaoNome()).child("salaoFuncionarios").setValue(Dados.dadosDoSalao.getSalaoFuncionarios());
                listaFuncionarios.remove(position);
                notifyDataSetChanged();
            }
        });

        return view;
    }
}
