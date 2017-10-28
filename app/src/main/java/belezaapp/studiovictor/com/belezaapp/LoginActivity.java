package belezaapp.studiovictor.com.belezaapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginActivity extends AppCompatActivity {

    private Button botaoCriarConta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Elementos da tela
        botaoCriarConta = (Button) findViewById(R.id.id_botaoCriarConta);




        //'SetOnClickListeners'
        botaoCriarConta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, CriarContaActivity.class));
            }
        });

    }
}
