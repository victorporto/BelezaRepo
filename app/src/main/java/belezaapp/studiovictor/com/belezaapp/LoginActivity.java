package belezaapp.studiovictor.com.belezaapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import belezaapp.studiovictor.com.belezaapp.Config.ConfigFirebase;

public class LoginActivity extends AppCompatActivity {

    private Button botaoCriarConta, botaoLogin;
    private EditText campoEmail, campoSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //Esconde a 'ActionBar'
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();


        //Elementos da tela
        botaoCriarConta = (Button) findViewById(R.id.id_botaoCriarConta);
        botaoLogin = (Button) findViewById(R.id.id_botaoEntrar);
        campoEmail = (EditText) findViewById(R.id.id_campoEmail);
        campoSenha = (EditText) findViewById(R.id.id_campoSenha);


        //'SetOnClickListeners'
        botaoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = campoEmail.getText().toString(),
                        senha = campoSenha.getText().toString();
                logarUsuario(email, senha);
            }
        });

        botaoCriarConta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CriarContaActivity.class);
                startActivity(intent);
            }
        });
    }

    private void logarUsuario(String _email, String _senha) {
        try {
            ConfigFirebase.getAuthFirebase().signInWithEmailAndPassword(_email, _senha).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        telaPrincipal();
                    } else {
                        Toast.makeText(LoginActivity.this, "Email ou senha estão errados.", Toast.LENGTH_LONG).show();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    private void telaPrincipal() {
        Intent intentMainActivity = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intentMainActivity);
        finish();
    }

}