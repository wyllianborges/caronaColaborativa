package br.com.up.caronaup;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        HomeFragment fragment = new HomeFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Profile profile = Profile.getCurrentProfile();
        if (profile != null) {

            View header = navigationView.getHeaderView(0);
            TextView textNomeUsuarioMenu = (TextView) header.findViewById(R.id.textNomeUsuario);
            ImageView imageProfile = (ImageView) header.findViewById(R.id.imageView);
            textNomeUsuarioMenu.setText(profile.getName());
            String imageProfileProfileUrl = "https://graph.facebook.com/" + profile.getId() + "/picture?type=large";
            Picasso.with(getApplicationContext()).load(imageProfileProfileUrl).into(imageProfile);
        }

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Carpool");
            builder.setMessage("Deseja fechar o aplicativo?");
            builder.setPositiveButton("Não", null);
            builder.setNegativeButton("Sim", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });

            builder.show();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.nav_home) {
            HomeFragment fragHome = new HomeFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragHome);
            fragmentTransaction.commit();
        } else if (id == R.id.nav_perfil) {
            //Chama a tela de perfil
            PerfilFragment fragPerfil = new PerfilFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragPerfil);
            fragmentTransaction.commit();
        } else if (id == R.id.nav_viagens) {
            ListaViagemFragment fragm = new ListaViagemFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragm);
            fragmentTransaction.commit();
        } else if (id == R.id.nav_caronas) {
            //Chama a tela de listagem de Caronas
            ListaCaronaFragment fragmentCarona = new ListaCaronaFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragmentCarona);
            fragmentTransaction.commit();
        } else if (id == R.id.nav_manage) {
            //Chama a tela de listagem de Veiculos
            ListaVeiculoFragment fragmentVeiculo = new ListaVeiculoFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragmentVeiculo);
            fragmentTransaction.commit();
        } else if (id == R.id.nav_minha_conta) {
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


    public List<ItemCarro> getSetCarList(int qtd) {
        String[] brands = new String[]{"Gallardo", "Vyron", "Corvette", "Pagani Zonda", "Porsche 911 Carrera", "BMW 720i", "DB77", "Mustang", "Camaro", "CT6"};
        String[] models = new String[]{"2007 | 2 Passageiros", "2012 | 1 Passageiro", "2015 | 2 Passageiros", "2012 | 2 Passageiros", "2013 | 2 Passageiros", "2016 | 2 Passageiros", "2013 | 2 Passageiros", "2011 | 3 Passageiros", "2013 | 2 Passageiros", "2014 | 4 Passageiros"};
        int[] photos = new int[]{R.drawable.gallardo, R.drawable.vyron, R.drawable.corvette, R.drawable.paganni_zonda, R.drawable.porsche_911, R.drawable.bmw_720, R.drawable.db77, R.drawable.mustang, R.drawable.camaro, R.drawable.ct6};
        List<ItemCarro> listAux = new ArrayList<>();

        for (int i = 0; i < qtd; i++) {
            ItemCarro c = new ItemCarro(models[i % models.length], brands[i % brands.length], photos[i % models.length]);
            listAux.add(c);
        }
        return (listAux);
    }

    public List<ItemCarona> getSetCaronaList() {

        List<ItemCarona> listAux = new ArrayList<>();
        ItemCarona item1 = new ItemCarona("001", "22/08/2016", "14:00", "Rua Martin Afonso, 1310, Bigorrilho. Curitiba - PR", "Universidade Positivo - Campus Ecoville");
        listAux.add(item1);

        ItemCarona item2 = new ItemCarona("002", "24/08/2016", "17:00", "Rua Pe Anchieta, 1892, Bigorrilho. Curitiba - PR", "Universidade Positivo - Campus Batel");
        listAux.add(item2);

        ItemCarona item3 = new ItemCarona("003", "25/08/2016", "17:00", "Rua Marechal Deodoro, 558, Centro. Curitiba - PR", "Universidade Positivo - Campus Ecoville");
        listAux.add(item3);

        return (listAux);
    }

    public List<ItemViagem> getSetViagemList() {

        List<ItemViagem> listAux = new ArrayList<>();
        ItemViagem item1 = new ItemViagem("001", "22/08/2016", "14:00", "Rua Martin Afonso, 1310, Bigorrilho. Curitiba - PR", "Universidade Positivo - Campus Ecoville", "2/4");
        listAux.add(item1);

        ItemViagem item2 = new ItemViagem("002", "24/08/2016", "17:00", "Rua Pe Anchieta, 1892, Bigorrilho. Curitiba - PR", "Universidade Positivo - Campus Batel", "1/3");
        listAux.add(item2);

        ItemViagem item3 = new ItemViagem("003", "25/08/2016", "17:00", "Rua Marechal Deodoro, 558, Centro. Curitiba - PR", "Universidade Positivo - Campus Ecoville", "2/3");
        listAux.add(item3);

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