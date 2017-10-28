package belezaapp.studiovictor.com.belezaapp;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        //Esconde a 'ActionBar' da 'SplashScreenActivity'
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        try {

        } catch (Exception e) {
            e.printStackTrace();
        }

        //Faz a transição com delay de 2 segundos.
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreenActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        }, 2000);

    }
}
