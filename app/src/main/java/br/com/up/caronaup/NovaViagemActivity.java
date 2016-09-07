package br.com.up.caronaup;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

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

import java.io.IOException;
import java.util.List;

public class NovaViagemActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnInfoWindowClickListener, GoogleMap.InfoWindowAdapter {

    private GoogleApiClient client;
    public TextView endereco;
    private Button buttonSalvar;

    Location myLocation;


    private Spinner spinnerCampus;
    private Spinner spinnerVeiculo;
    private ArrayAdapter<CharSequence> adapterCampus;
    private ArrayAdapter<CharSequence> adapterVeiculo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nova_viagem);

        this.setTitle("Nova viagem");

        endereco = (TextView) findViewById(R.id.txtEnderecoSelecionadoNovaViagem);

        final ScrollView hsv = (ScrollView) findViewById(R.id.scrollViewNovaViagem);
        final MapView mapView = (MapView) findViewById(R.id.map_nova_viagem);

        mapView.onCreate(null);
        mapView.getMapAsync(this);
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();

        spinnerCampus = (Spinner) this.findViewById(R.id.spinnerCampusNovaViagem);
        adapterCampus = ArrayAdapter.createFromResource(getApplicationContext(), R.array.campus, R.layout.spinner_item);
        spinnerCampus.setAdapter(adapterCampus);

        spinnerVeiculo = (Spinner) this.findViewById(R.id.spinnerVeiculoNovaViagem);
        adapterVeiculo = ArrayAdapter.createFromResource(getApplicationContext(), R.array.modelos_veiculo, R.layout.spinner_item);
        spinnerVeiculo.setAdapter(adapterVeiculo);

        final ScrollView mainScrollView = (ScrollView) findViewById(R.id.scrollViewNovaViagem);
        final CardView cardViewHome = (CardView) this.findViewById(R.id.cardViewNovaViagem);

        buttonSalvar = (Button) this.

                findViewById(R.id.buttonSalvarNovaViagem);

        buttonSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);


        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        locationManager.requestSingleUpdate(LocationManager.GPS_PROVIDER, new android.location.LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                myLocation = location;

            }

            @Override
            public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
                Toast.makeText(getApplicationContext(), "Status alterado", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onProviderEnabled(String arg0) {
                Toast.makeText(getApplicationContext(), "Provider Habilitado", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onProviderDisabled(String arg0) {
                Toast.makeText(getApplicationContext(), "Provider Desabilitado", Toast.LENGTH_LONG).show();
            }
        }, null);
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
    public void onMapReady(final GoogleMap googleMap) {

        googleMap.setOnInfoWindowClickListener(this);
        googleMap.setInfoWindowAdapter(this);

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        googleMap.setMyLocationEnabled(true);

        LatLng latLng;
        if (myLocation != null) {
            latLng = new LatLng(myLocation.getLatitude(), myLocation.getLongitude());
        } else {
            latLng = new LatLng(-25.44560946, -49.35438931);
        }

        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));

        // Zoom in the Google Map
        googleMap.animateCamera(CameraUpdateFactory.zoomTo(15));

        googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
//                Toast.makeText(getApplicationContext(), latLng.toString(), Toast.LENGTH_SHORT).show();

                googleMap.clear();
                googleMap.addMarker(new MarkerOptions()
                        .title("Ponto de encontro")
                        .position(latLng));

                Geocoder geocoder = new Geocoder(getApplicationContext());
                List myLocation = null;
                try {
                    //Obtendo os dados do endereço
                    myLocation = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1);
                } catch (IOException e) {
                    e.printStackTrace();
                }


                if (myLocation != null && myLocation.size() > 0) {
                    Address a = (Address) myLocation.get(0);
                    //Pronto! Vocêm tem o nome da cidade!
                    String city = a.getLocality();
                    String street = a.getAddressLine(0);

                    endereco.setText(street + " - " + city);
                } else {
                    endereco.setText(latLng.toString());
                }

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


}