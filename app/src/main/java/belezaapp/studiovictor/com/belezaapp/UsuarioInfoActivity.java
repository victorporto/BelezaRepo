package belezaapp.studiovictor.com.belezaapp;

import android.database.Cursor;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class UsuarioInfoActivity extends AppCompatActivity {

    DatabaseHelper meuDB;
    private TextView txtNome, txtCpf, txtEmail, txtSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario_info);

        //Esconde a 'ActionBar' da 'SplashScreenActivity'
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        txtNome = (TextView) findViewById(R.id.id_txtNome);
        txtCpf = (TextView) findViewById(R.id.id_txtCpf);
        txtEmail = (TextView) findViewById(R.id.id_txtEmail);
        txtSenha = (TextView) findViewById(R.id.id_txtSenha);

        Bundle extras = getIntent().getExtras();
        String email = extras.getString("emailClicado");

        if (extras != null) {
            try {

                meuDB = new DatabaseHelper(this);
                Cursor cursor = meuDB.pegarDados(email);
                txtNome.setText(txtNome.getText().toString() + cursor.getString(cursor.getColumnIndex("usuario_nome")));
                txtCpf.setText(txtCpf.getText().toString() + cursor.getString(cursor.getColumnIndex("usuario_cpf")));
                txtEmail.setText(txtEmail.getText().toString() + cursor.getString(cursor.getColumnIndex("usuario_email")));
                txtSenha.setText(txtSenha.getText().toString() + cursor.getString(cursor.getColumnIndex("usuario_senha")));

            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            Toast.makeText(getApplicationContext(), "Ops! Algo inesperado ocorreu ao recuperar informações do usuário.", Toast.LENGTH_LONG).show();
        }
    }
}
