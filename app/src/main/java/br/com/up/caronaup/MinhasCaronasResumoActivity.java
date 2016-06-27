package br.com.up.caronaup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MinhasCaronasResumoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_minhas_caronas_resumo);

        Button button = (Button) findViewById(R.id.buttonPagamentoResumo);
        button.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){
                //Intent i = new Intent(getApplicationContext(),MainpsActivity.class);
                Intent i = new Intent(getApplicationContext(), PagamentoActivity.class);
                startActivity(i);
                // startActivity(new Intent(MinhasCaronasActivity.this, MinhasCaronasList.class));
            }

        });

    }
}
