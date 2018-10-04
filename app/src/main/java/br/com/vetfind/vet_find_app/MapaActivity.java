package br.com.vetfind.vet_find_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MapaActivity extends AppCompatActivity {

    private FloatingActionButton btnFiltro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa);

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction tx = manager.beginTransaction();
        tx.replace(R.id.map_view, new MapaFragment());
        tx.commit();

        btnFiltro = findViewById(R.id.fBtnFiltro);
        btnFiltro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentVaiProFiltro = new Intent(MapaActivity.this, FiltroActivity.class);
                startActivity(intentVaiProFiltro);
            }
        });
    }
}
