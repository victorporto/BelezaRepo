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

import belezaapp.studiovictor.com.belezaapp.ClassesDeDados.Servicos;
import belezaapp.studiovictor.com.belezaapp.Config.ConfigFirebase;
import belezaapp.studiovictor.com.belezaapp.Config.Dados;
import belezaapp.studiovictor.com.belezaapp.CriarServicoActivity;
import belezaapp.studiovictor.com.belezaapp.R;

/**
 * Created by Victor on 13/03/2018.
 */

public class LstServicoDoFuncionarioAdapter extends BaseAdapter implements ListAdapter {

    private ArrayList<String> listaServicosDoSalao = new ArrayList<String>();
    private ArrayList<String> listaServicosDoFuncionario = new ArrayList<String>();
    private Context context;

    public LstServicoDoFuncionarioAdapter(ArrayList<String> _listaServicosDoSalao, Context context) {
        this.listaServicosDoSalao = _listaServicosDoSalao;
        this.context = context;
    }

    public LstServicoDoFuncionarioAdapter(ArrayList<String> _listaServicosDoSalao, ArrayList<String> _listaServicosDoFuncionario, Context context) {
        this.listaServicosDoSalao = _listaServicosDoSalao;
        this.listaServicosDoFuncionario = _listaServicosDoFuncionario;
        this.context = context;
    }

    @Override
    public int getCount() {
        return listaServicosDoSalao.size();
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
            view = inflater.inflate(R.layout.layout_listadeservicosdofuncionario, null);
        }

        //Handle TextView and display string from your list
        TextView nomeServicoDoFuncionario = (TextView)view.findViewById(R.id.id_NomeServicoDoFuncionario);
        CheckBox checkBoxServicoDoFuncionario = (CheckBox) view.findViewById(R.id.id_checkBoxServicoDoFuncionario);

        nomeServicoDoFuncionario.setText(listaServicosDoSalao.get(position));

        if(listaServicosDoFuncionario != null && !listaServicosDoFuncionario.isEmpty()){
            checkBoxServicoDoFuncionario.setChecked(marcarCheckBox(nomeServicoDoFuncionario.getText().toString()));
        }

        return view;
    }

    private Boolean marcarCheckBox(String _nomeServico) {
        for (String _servicoFuncionario: listaServicosDoFuncionario){
            if(_servicoFuncionario.equals(_nomeServico)) {
                return true;
            }
        }
        return false;
    }

}
