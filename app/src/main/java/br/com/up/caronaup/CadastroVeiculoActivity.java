package br.com.up.caronaup;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;


public class CadastroVeiculoActivity extends AppCompatActivity {

    private Button buttonSalvar;
    private Spinner spinnerMarcaVeiculo;
    private Spinner spinnerModeloVeiculo;
    private Spinner spinnerCorVeiculo;
    private Spinner spinnerAnoVeiculo;
    private Spinner spinnerCapacidadeVeiculo;
    private ArrayAdapter<CharSequence> adapterMarcaVeiculo;
    private ArrayAdapter<CharSequence> adapterModeloVeiculo;
    private ArrayAdapter<CharSequence> adapterCorVeiculo;
    private ArrayAdapter<CharSequence> adapterAnoVeiculo;
    private ArrayAdapter<CharSequence> adapterCapacidadeVeiculo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_cadastro_veiculo);

        spinnerModeloVeiculo = (Spinner) this.findViewById(R.id.spinnerModeloVeiculo);

        spinnerMarcaVeiculo = (Spinner) this.findViewById(R.id.spinnerMarcaVeiculo);
        adapterMarcaVeiculo = ArrayAdapter.createFromResource(getApplicationContext(), R.array.marcas_veiculo, R.layout.spinner_item);
        adapterMarcaVeiculo.setDropDownViewResource(R.layout.spinner_dropdown_item);
        spinnerMarcaVeiculo.setAdapter(adapterMarcaVeiculo);
        spinnerMarcaVeiculo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                adapterModeloVeiculo = ArrayAdapter.createFromResource(getApplicationContext(), R.array.modelos_veiculo, R.layout.spinner_item);
                adapterModeloVeiculo.setDropDownViewResource(R.layout.spinner_dropdown_item);
                spinnerModeloVeiculo.setAdapter(adapterModeloVeiculo);
                spinnerModeloVeiculo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        spinnerCorVeiculo = (Spinner) this.findViewById(R.id.spinnerCorVeiculo);
        adapterCorVeiculo = ArrayAdapter.createFromResource(getApplicationContext(), R.array.cor_veiculo, R.layout.spinner_item);
        adapterCorVeiculo.setDropDownViewResource(R.layout.spinner_dropdown_item);
        spinnerCorVeiculo.setAdapter(adapterCorVeiculo);
        spinnerCorVeiculo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(getApplicationContext(), parent.getItemIdAtPosition(position) + " selected", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerAnoVeiculo = (Spinner) this.findViewById(R.id.spinnerAnoVeiculo);
        adapterAnoVeiculo = ArrayAdapter.createFromResource(getApplicationContext(), R.array.ano_veiculo, R.layout.spinner_item);
        adapterAnoVeiculo.setDropDownViewResource(R.layout.spinner_dropdown_item);
        spinnerAnoVeiculo.setAdapter(adapterAnoVeiculo);
        spinnerAnoVeiculo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(getApplicationContext(), parent.getItemIdAtPosition(position) + " selected", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerCapacidadeVeiculo = (Spinner) this.findViewById(R.id.spinnerCapacidadeVeiculo);
        adapterCapacidadeVeiculo = ArrayAdapter.createFromResource(getApplicationContext(), R.array.capacidade_veiculo, R.layout.spinner_item);
        adapterCapacidadeVeiculo.setDropDownViewResource(R.layout.spinner_dropdown_item);
        spinnerCapacidadeVeiculo.setAdapter(adapterCapacidadeVeiculo);
        spinnerCapacidadeVeiculo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(getApplicationContext(), parent.getItemIdAtPosition(position) + " selected", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        buttonSalvar = (Button) this.findViewById(R.id.buttonSalvarCadastroVeiculo);
        buttonSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });


    }
}
