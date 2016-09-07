package br.com.up.caronaup;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.zxing.integration.android.IntentIntegrator;

import java.util.ArrayList;
import java.util.List;

public class DetalhesViagemActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnInfoWindowClickListener, GoogleMap.InfoWindowAdapter {

    private GoogleApiClient client;
    private final Activity activity = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_viagem);

        this.setTitle("Detalhes Viagem");

        CardView cardViewVeiculo = (CardView) findViewById(R.id.cardViewVeiculoDetalhesViagem);
        CardView cardViewHorario = (CardView) findViewById(R.id.cardViewHorarioDetalhesViagem);
        CardView cardViewLocal = (CardView) findViewById(R.id.cardViewLocalDetalhesViagem);
        CardView cardViewMaps = (CardView) findViewById(R.id.cardViewMApsDetalhesViagem);

        cardViewVeiculo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), DetalhesVeiculoActivity.class);
                startActivity(i);
            }
        });


        ListaPassageiroFragment fragmentPassageiro = new ListaPassageiroFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container_detalhes_viagem, fragmentPassageiro);
        fragmentTransaction.commit();

        MapView mapView = (MapView) findViewById(R.id.map_card_detalhes_viagem);

        mapView.onCreate(null);
        mapView.getMapAsync(this);


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();

        Button button = (Button) findViewById(R.id.buttonRecebimentoDetalhesViagem);
        button.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {


                IntentIntegrator integrator = new IntentIntegrator(activity);
                integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
                integrator.setPrompt("Posicione sob código");
                integrator.setCameraId(0);
                integrator.setBeepEnabled(false);
                integrator.setBarcodeImageEnabled(false);
                integrator.initiateScan();


            }

        });

    }

    @Override
    public View getInfoWindow(Marker marker) {
        return null;
    }

    @Override
    public View getInfoContents(Marker marker) {
        return null;
    }

    @Override
    public void onInfoWindowClick(Marker marker) {

    }

    @Override
    public void onMapReady(final GoogleMap googleMap) {
        LatLng positivo1 = new LatLng(-25.44603573, -49.35308039);
        googleMap.addMarker(new MarkerOptions()
                .title("Wyllian Borges")
                .position(positivo1));
        LatLng positivo2 = new LatLng(-25.44560946, -49.35438931);
        googleMap.addMarker(new MarkerOptions()
                .title("Eduardo Carnasciali")
                .position(positivo2));
        googleMap.setOnInfoWindowClickListener(this);
        googleMap.setInfoWindowAdapter(this);

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }

        LatLng local = new LatLng(-25.44560946, -49.35438931);

        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(local, 16));

    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Maps Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://br.com.up.caronaup/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Maps Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://br.com.up.caronaup/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }

    public List<ItemPassageiro> getSetPassageiroList() {
        List<ItemPassageiro> listAux = new ArrayList<>();
        ItemPassageiro p = new ItemPassageiro("001", "Gustavo Duarte", "Administração", R.drawable.wyll_perf);
        listAux.add(p);

        ItemPassageiro p2 = new ItemPassageiro("002", "Thiago Castro", "Sistemas de Informação", R.drawable.edu_perf);
        listAux.add(p2);

        ItemPassageiro p3 = new ItemPassageiro("002", "Diego Costa", "Sistemas de Informação", R.drawable.wyll_perf);
        listAux.add(p3);

        return (listAux);
    }
}
