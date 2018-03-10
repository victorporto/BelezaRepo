package belezaapp.studiovictor.com.belezaapp.CustomArrayAdapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import belezaapp.studiovictor.com.belezaapp.ClassesDeDados.Servicos;
import belezaapp.studiovictor.com.belezaapp.Config.ConfigFirebase;
import belezaapp.studiovictor.com.belezaapp.Config.Dados;
import belezaapp.studiovictor.com.belezaapp.CriarServicoActivity;
import belezaapp.studiovictor.com.belezaapp.R;

public class LstServicoAdapter extends BaseAdapter implements ListAdapter{

    private ArrayList<Servicos> listaServicos = new ArrayList<Servicos>();
    private Context context;

    public LstServicoAdapter(ArrayList<Servicos> _listaServicos, Context context) {
        this.listaServicos = _listaServicos;
        this.context = context;
    }

    @Override
    public int getCount() {
        return listaServicos.size();
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
            view = inflater.inflate(R.layout.layout_listadeservicos, null);
        }

        //Handle TextView and display string from your list
        TextView lstServicoNome = (TextView)view.findViewById(R.id.id_LstServicoNome);
        TextView lstServicoTempo = (TextView)view.findViewById(R.id.id_LstServicoTempo);
        TextView lstServicoPreco = (TextView)view.findViewById(R.id.id_LstServicoPreco);

        lstServicoNome.setText(listaServicos.get(position).getNomeServico());
        lstServicoTempo.setText(listaServicos.get(position).getTempoServico() + " min");
        lstServicoPreco.setText("R$ " + listaServicos.get(position).getPrecoServico());

        //Handle buttons and add onClickListeners
        ImageButton btnEditar = (ImageButton) view.findViewById(R.id.id_LstServicoBtnEditar);
        ImageButton btnDeletar = (ImageButton) view.findViewById(R.id.id_LstServicoBtnDeletar);

        btnEditar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //Possibilidade de usar CriarServicoActivity?
                Intent intent = new Intent(context, CriarServicoActivity.class);
                intent.putExtra("editar", true);
                intent.putExtra("position", position);
                context.startActivity(intent);
                //Redirecionar para EditarServicoActivity, e passar a 'position' para a activity, pois o que será alterado vai ser o Dados.dadosDoSalao.getSalaoServicos()
                //Na EditarServicoActivity é que serão alterados os dados.
                notifyDataSetChanged();
            }
        });
        btnDeletar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //Remover do Dados.dadosDoSalao.getServicosSalao() o Servico na posição correta e depois reordenar o ArrayList<Servicos>()
                int tamanho = Dados.dadosDoSalao.getSalaoServicos().size();
                tamanho--;
                Dados.dadosDoSalao.getSalaoServicos().remove(position);

                //Necessário remover a ÚLTIMA POSIÇÃO do Firebase, fazendo com que os dois ArrayList<Servicos> possuam as mesmas posições e tamanhos.
                ConfigFirebase.getRefFirebaseTeste().child("Saloes").child(Dados.dadosDoSalao.getSalaoNome()).child("salaoServicos").child(Integer.toString(tamanho)).removeValue();

                //Subir o Dados.dadosDoSalão.getServicosSalao() no Firebase.
                ConfigFirebase.getRefFirebaseTeste().child("Saloes").child(Dados.dadosDoSalao.getSalaoNome()).child("salaoServicos").setValue(Dados.dadosDoSalao.getSalaoServicos());
                listaServicos.remove(position);
                notifyDataSetChanged();
            }
        });

        return view;
    }

}
