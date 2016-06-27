package br.com.up.caronaup;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    //        implements NavigationView.OnNavigationItemSelectedListener, OnMapReadyCallback {
    //
//    private GoogleMap mMap;
//    private SupportMapFragment sMapFragment;
    private NavigationView navigationView = null;
    private Toolbar toolbar = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        HomeFragment fragment = new HomeFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();


        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Profile profile = Profile.getCurrentProfile();
        if (profile != null) {

            View header = navigationView.getHeaderView(0);
            TextView textNomeUsuario = (TextView) header.findViewById(R.id.textNomeUsuario);
            TextView textEmail = (TextView) header.findViewById(R.id.textNomeUsuario);
            ImageView imageProfile = (ImageView) header.findViewById(R.id.imageView);

            textNomeUsuario.setText(profile.getName());
            textEmail.setText("teste@facebook.com");
            String imageProfileProfileUrl = "https://graph.facebook.com/" + profile.getId() + "/picture?type=large";
            Picasso.with(getApplicationContext()).load(imageProfileProfileUrl).into(imageProfile);
        }

//        sMapFragment.getMapAsync(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        FragmentManager sFm = getSupportFragmentManager();

        int id = item.getItemId();

//        if (sMapFragment.isAdded())
//            sFm.beginTransaction().hide(sMapFragment).commit();


        if (id == R.id.nav_perfil) {

        } else if (id == R.id.nav_pagar) {
            //Chama a tela de Pagamento
            PagamentoFragment fragment = new PagamentoFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragment);
            fragmentTransaction.commit();

        } else if (id == R.id.nav_receber) {
            //Chama a tela de Recebimento
            RecebimentoFragment fragment = new RecebimentoFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragment);
            fragmentTransaction.commit();

        } else if (id == R.id.nav_gallery) {
            MinhasViagensFragment fragm = new MinhasViagensFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragm);
            fragmentTransaction.commit();

        } else if (id == R.id.nav_slideshow) {
            MinhasCaronasFragment fragment = new MinhasCaronasFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragment);
            fragmentTransaction.commit();

        } else if (id == R.id.nav_manage) {
            //Chama a tela de listagem de veiculos
            ListaVeiculoFragment fragmentVeiculo = new ListaVeiculoFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragmentVeiculo);
            fragmentTransaction.commit();

        } else if (id == R.id.nav_credits) {
            //Chama a tela de demonstrativo da conta
            ExtratoFragment fragm = new ExtratoFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragm);
            fragmentTransaction.commit();
        } else if (id == R.id.nav_sair) {
            //Sair
            FacebookSdk.sdkInitialize(getApplicationContext());
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

//    @Override
//    public void onMapReady(GoogleMap googleMap) {
//        mMap = googleMap;
//
//        // Add a marker in Sydney and move the camera
//        LatLng sydney = new LatLng(-25.44646649, -49.356637);
//        LatLng carona = new LatLng(-25.443026, -49.352654);
//        //mMap.addMarker(new MarkerOptions()
//        //        .position(sydney).title("Carona")
//        //        .snippet("This is a line break")
//        //        .snippet("segunda parte"));
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Carona").snippet("Condutor: Geucimar" + "\n" + "Jovemmm" + "\n" + "2nd Line Text" + "\n" + "3rd Line Text").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
//        mMap.addMarker(new MarkerOptions().position(carona).title("Carona2").snippet("Condutor: Francisco" + "\n" + "Jovemmm" + "\n" + "2nd Line Text" + "\n" + "3rd Line Text").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
//
//
//        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            // TODO: Consider calling
//            //    ActivityCompat#requestPermissions
//            // here to request the missing permissions, and then overriding
//            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//            //                                          int[] grantResults)
//            // to handle the case where the user grants the permission. See the documentation
//            // for ActivityCompat#requestPermissions for more details.
//            return;
//        }
//        mMap.setMyLocationEnabled(true);
//
//
//    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

        if (result != null) {
            if (result.getContents() == null) {
                Toast.makeText(this, "Calcelado", Toast.LENGTH_LONG).show();
            } else {

                Toast.makeText(this, result.getContents(), Toast.LENGTH_LONG).show();
            }


        }
    }


    public List<Carro> getSetCarList(int qtd) {
        String[] brands = new String[]{"Gallardo", "Vyron", "Corvette", "Pagani Zonda", "Porsche 911 Carrera", "BMW 720i", "DB77", "Mustang", "Camaro", "CT6"};
        String[] models = new String[]{"2007 | 2 Passageiros", "2012 | 1 Passageiro", "2015 | 2 Passageiros", "2012 | 2 Passageiros", "2013 | 2 Passageiros", "2016 | 2 Passageiros", "2013 | 2 Passageiros", "2011 | 3 Passageiros", "2013 | 2 Passageiros", "2014 | 4 Passageiros"};
        int[] photos = new int[]{R.drawable.gallardo, R.drawable.vyron, R.drawable.corvette, R.drawable.paganni_zonda, R.drawable.porsche_911, R.drawable.bmw_720, R.drawable.db77, R.drawable.mustang, R.drawable.camaro, R.drawable.ct6};
        List<Carro> listAux = new ArrayList<>();

        for (int i = 0; i < qtd; i++) {
            Carro c = new Carro(models[i % models.length], brands[i % brands.length], photos[i % models.length]);
            listAux.add(c);
        }
        return (listAux);
    }

    public List<ItemExtrato> getSetListExtrato() {

        List<ItemExtrato> list = new ArrayList<>();

        ItemExtrato saldo = new ItemExtrato("saldo", "Saldo Atual", "Saldo em 27/06/2016", "47,40");
        list.add(saldo);

        ItemExtrato item1 = new ItemExtrato("-", "Carona", "Pagamento de carona em 20/06/2016", "2,20");
        list.add(item1);

        ItemExtrato item2 = new ItemExtrato("+", "Carona", "Recebimento de carona em 17/06/2016", "2,00");
        list.add(item2);

        ItemExtrato item3 = new ItemExtrato("-", "Carona", "Pagamento de carona em 16/06/2016", "2,20");
        list.add(item3);

        ItemExtrato item4 = new ItemExtrato("-", "Carona", "Pagamento de carona em 15/06/2016", "2,20");
        list.add(item4);

        ItemExtrato item5 = new ItemExtrato("-", "Carona", "Pagamento de carona em 15/06/2016", "2,20");
        list.add(item5);

        ItemExtrato item6 = new ItemExtrato("-", "Carona", "Pagamento de carona em 14/06/2016", "2,20");
        list.add(item6);

        ItemExtrato item7 = new ItemExtrato("-", "Carona", "Pagamento de carona em 13/06/2016", "2,20");
        list.add(item7);

        ItemExtrato item8 = new ItemExtrato("-", "Carona", "Pagamento de carona em 10/06/2016", "2,20");
        list.add(item8);
        
        ItemExtrato item9 = new ItemExtrato("-", "Carona", "Pagamento de carona em 10/06/2016", "2,20");
        list.add(item9);

        ItemExtrato item11 = new ItemExtrato("+", "Carona", "Recebimento de carona em 08/06/2016", "2,00");
        list.add(item11);

        ItemExtrato item12 = new ItemExtrato("+", "Carona", "Recebimento de carona em 08/06/2016", "2,00");
        list.add(item12);

        ItemExtrato item10 = new ItemExtrato("-", "Carona", "Pagamento de carona em 07/06/2016", "2,20");
        list.add(item10);

        ItemExtrato compra = new ItemExtrato("+", "Credito Paypal", "Compra de crédito em 14/05/2016", "50,00");
        list.add(compra);

        return (list);
    }
}