package br.com.up.caronaup;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Cadastro1Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro1);

        Button confirmarButton = (Button) findViewById(R.id.buttonConfirmarCadastro1);
        Button cancelarButton = (Button) findViewById(R.id.buttonCancelarCadastro1);


        confirmarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Cadastro1Activity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        cancelarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Cadastro1Activity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}
