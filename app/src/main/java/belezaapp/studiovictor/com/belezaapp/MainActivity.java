package belezaapp.studiovictor.com.belezaapp;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper meuDB;
    private ArrayList<String> listaUsuarios = new ArrayList<String>();
    private ListView listViewMain;
    ArrayAdapter<String> adaptador;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Esconde a 'ActionBar' da 'SplashScreenActivity'
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        listViewMain = (ListView) findViewById(R.id.id_listViewMain);

        meuDB = new DatabaseHelper(this);
        Cursor cursor = meuDB.pegarTodosUsuarios();

        while (!cursor.isAfterLast()) {
            listaUsuarios.add(cursor.getString(cursor.getColumnIndex("usuario_email")));
            cursor.moveToNext();
        }

        adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listaUsuarios);
        listViewMain.setAdapter(adaptador);

        listViewMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String emailClicado = listViewMain.getItemAtPosition(i).toString();
                Intent intent = new Intent(MainActivity.this, UsuarioInfoActivity.class);
                intent.putExtra("emailClicado", emailClicado);
                startActivity(intent);
            }
        });

    }
}
