package br.com.up.caronaup;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnInfoWindowClickListener, GoogleMap.InfoWindowAdapter {

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
//        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).addApi(LocationServices.API).build();

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
        Location mytLocation = LocationServices.FusedLocationApi.getLastLocation(client);

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

        // Add a marker in Sydney and move the camera
        LatLng positivo1 = new LatLng(-25.44603573, -49.35308039);
        googleMap.addMarker(new MarkerOptions().title("001").position(positivo1));
        LatLng positivo2 = new LatLng(-25.44560946, -49.35438931);
        googleMap.addMarker(new MarkerOptions().title("002").position(positivo2));
        googleMap.setOnInfoWindowClickListener(this);
        googleMap.setInfoWindowAdapter(this);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        googleMap.setMyLocationEnabled(true);

        LatLng latLng = new LatLng(-25.44560946, -49.35438931);
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));

        // Zoom in the Google Map
        googleMap.animateCamera(CameraUpdateFactory.zoomTo(15));

        googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                Toast.makeText(getApplicationContext(), latLng.toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        if (marker.getTitle().equals("001")) {
//            AlertDialog.Builder builder = new AlertDialog.Builder(this);
//            builder.setTitle("Confimar Carona");
//            builder.setMessage("Aperte OK para confirmar a Carona");
//            builder.setPositiveButton("OK",
//                    new DialogInterface.OnClickListener() {
//                        public void onClick(DialogInterface dialog,
//                                            int which) {
//                            finish();
//
//                        }
//                    });
//            builder.show();

            Intent intent = new Intent(this, DetalhesCaronaViewActivity.class);
            startActivity(intent);

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

        if (marker.getTitle().equals("001")) {
            View v = getLayoutInflater().inflate(R.layout.info_window, null);
            ((TextView) v.findViewById(R.id.txt_nomeCondutorInfoWindow)).setText("Wyllian Borges");
            ((TextView) v.findViewById(R.id.txt_local)).setText("Rua Monsenhor Ivo Zanlorenzi, 158");
            ((TextView) v.findViewById(R.id.txt_destino)).setText("Universidade Positivo - Campus Osorio");
            ((TextView) v.findViewById(R.id.txt_capacidade)).setText("1/3");
            ((TextView) v.findViewById(R.id.txt_horario)).setText("17:30");
            ((TextView) v.findViewById(R.id.txt_data)).setText("26/09/2016");
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.wyll_perf);
            ((ImageView) v.findViewById(R.id.imageFotoPerfilInfoWindow)).setImageBitmap(bitmap);
            return v;
        } else {
            View v = getLayoutInflater().inflate(R.layout.info_window, null);
            ((TextView) v.findViewById(R.id.txt_nomeCondutorInfoWindow)).setText("Eduardo Carnasciali");
            ((TextView) v.findViewById(R.id.txt_local)).setText("R. Dep. Heitor Alencar Furtado, 244");
            ((TextView) v.findViewById(R.id.txt_destino)).setText("Universidade Positivo - Campus Osorio");
            ((TextView) v.findViewById(R.id.txt_capacidade)).setText("3/3");
            ((TextView) v.findViewById(R.id.txt_horario)).setText("Saída: 09:30");
            ((TextView) v.findViewById(R.id.txt_data)).setText("26/09/2016");
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.edu_perf);
            ((ImageView) v.findViewById(R.id.imageFotoPerfilInfoWindow)).setImageBitmap(bitmap);
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
