package belezaapp.studiovictor.com.belezaapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import belezaapp.studiovictor.com.belezaapp.Config.ConfigFirebase;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        //Esconde a 'ActionBar'
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        //ConfigFirebase.getAuthFirebase().signOut();

        //Faz a transição com delay de 2 segundos.
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //Se o usuário ainda estiver logado no Firebase, o app deve ir para a 'MainActivity' imediatamente
                verifUsuarioLogado();
            }
        }, 500);
    }

    private void verifUsuarioLogado() {
        //Se for diferente de 'null' então há um usuário logado.
        if(ConfigFirebase.getAuthFirebase().getCurrentUser() != null) {
            Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        } else {
            Intent intent = new Intent(SplashScreenActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }

    }
}
