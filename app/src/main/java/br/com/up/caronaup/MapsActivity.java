package br.com.up.caronaup;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnInfoWindowClickListener, GoogleMap.InfoWindowAdapter {

    private GoogleMap mMap;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng positivo1 = new LatLng(-25.44603573, -49.35308039);
        mMap.addMarker(new MarkerOptions()
                .title("Wyllian Borges")
                .position(positivo1));
        LatLng positivo2 = new LatLng(-25.44560946, -49.35438931);
        mMap.addMarker(new MarkerOptions()
                .title("Eduardo Carnasciali")
                .position(positivo2));
        mMap.setOnInfoWindowClickListener(this);
        mMap.setInfoWindowAdapter(this);


        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(true);


    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        if (marker.getTitle().equals("Wyllian Borges")) {

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Confimar Carona");
            builder.setMessage("Aperte OK para confirmar a Carona");
            builder.setPositiveButton("OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,
                                            int which) {
                            //Intent i = new Intent(MapsActivity.this, MinhasCaronasResumoActivity.class);
                            Intent i = new Intent(MapsActivity.this, PagamentoActivity.class);
                            startActivity(i);
                        }
                    });
            builder.show();

            //Intent i = new Intent(MapsActivity.this, MinhasCaronasActivity.class);
            // Intent i = new Intent(MapsActivity.this, MinhasCaronasResumoActivity.class);
            // startActivity(i);

            /* Creditos newFragment = new Creditos();
            android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

            transaction.replace(R.id.fragment_container, newFragment);
            transaction.commit(); */
        } else {
            Context context = getApplicationContext();
            CharSequence mensagemLoka = "A carona já está lotada";
            int duration = Toast.LENGTH_LONG;

            Toast toast = Toast.makeText(context, mensagemLoka, duration);
            toast.show();
        }
    }

    @Override
    public View getInfoWindow(Marker marker) {
        return null;
    }

    @Override
    public View getInfoContents(Marker marker) {

        if (marker.getTitle().equals("Wyllian Borges")) {
            View v = getLayoutInflater().inflate(R.layout.info_window, null);
            ((TextView) v.findViewById(R.id.titulo)).setText(marker.getTitle());
            ((TextView) v.findViewById(R.id.txt_local)).setText("Rua Monsenhor Ivo Zanlorenzi, 158");
            ((TextView) v.findViewById(R.id.txt_capacidade)).setText("1/3");
            ((TextView) v.findViewById(R.id.txt_horario)).setText("Saída: 07:30");
            return v;
        } else {
            View v = getLayoutInflater().inflate(R.layout.info_window2, null);
            ((TextView) v.findViewById(R.id.titulo)).setText(marker.getTitle());
            ((TextView) v.findViewById(R.id.txt_local2)).setText("R. Dep. Heitor Alencar Furtado, 244");
            ((TextView) v.findViewById(R.id.txt_capacidade2)).setText("3/3");
            ((TextView) v.findViewById(R.id.txt_horario2)).setText("Saída: 09:30");

            return v;
        }

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
}
