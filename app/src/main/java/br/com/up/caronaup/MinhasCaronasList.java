package br.com.up.caronaup;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MinhasCaronasList extends AppCompatActivity {

    private ArrayList<Model> productList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_minhas_caronas_list);

        productList = new ArrayList<Model>();
        ListView lview = (ListView) findViewById(R.id.listview);
        listviewAdapter adapter = new listviewAdapter(this, productList);
        lview.setAdapter(adapter);

        populateList();

        adapter.notifyDataSetChanged();

        lview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                String sno = ((TextView)view.findViewById(R.id.sNo)).getText().toString();
                String product = ((TextView)view.findViewById(R.id.product)).getText().toString();
                // String category = ((TextView)view.findViewById(R.id.category)).getText().toString();
                String price = ((TextView)view.findViewById(R.id.price)).getText().toString();

                //  Toast.makeText(getApplicationContext(), "S no : " + sno +"\n"
                //         +"Product : " + product +"\n"
                //         //+"Category : " +category +"\n"
                //       +"Price : " +price, Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(MinhasCaronasList.this, MinhasCaronasResumoActivity.class);
                startActivity(intent);
            }
        });
    }

    private void populateList() {

        Model item1, item2;

        item1 = new Model("Wyllian Borges", "R. Monsenhor Ivo Zanlorenzi, 158", "07:30");
        productList.add(item1);

        item2 = new Model("Eduardo", "r. Deputado Heitor Alencar Furtado, 244", "09:30");
        productList.add(item2);


    }

}
