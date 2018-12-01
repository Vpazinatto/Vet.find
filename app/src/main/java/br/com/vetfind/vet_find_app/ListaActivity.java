package br.com.vetfind.vet_find_app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import br.com.vetfind.vet_find_app.DAO.AnimalDAO;
import br.com.vetfind.vet_find_app.adapter.AnimaisAdapter;
import br.com.vetfind.vet_find_app.modelo.Animal;

public class ListaActivity extends Activity {

    private ListView listaAnimais;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        listaAnimais = (ListView) findViewById(R.id.lista_animais);

        listaAnimais.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> lista, View item, int position, long id) {
                Animal animal = (Animal) listaAnimais.getItemAtPosition(position);

                Intent intentVaiProFormulario = new Intent(ListaActivity.this, FormularioActivity.class);
                intentVaiProFormulario.putExtra("animal", animal);
                startActivity(intentVaiProFormulario);
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentVaiProFormulario = new Intent(ListaActivity.this, FormularioActivity.class);
                startActivity(intentVaiProFormulario);
            }
        });

        registerForContextMenu(listaAnimais);
    }

    @Override
    protected void onResume() {
        super.onResume();
        carregaLista();
    }

    private void carregaLista() {

        AnimalDAO dao = new AnimalDAO(this);
        List<Animal> animais = dao.getAnimais();
        dao.close();

        AnimaisAdapter adapter = new AnimaisAdapter(this, animais);
        listaAnimais.setAdapter(adapter);

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, final ContextMenu.ContextMenuInfo menuInfo) {

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        final Animal animal = (Animal) listaAnimais.getItemAtPosition(info.position);

        MenuItem deletar = menu.add("Deletar");
        deletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                AnimalDAO dao = new AnimalDAO(ListaActivity.this);
                dao.deleteAnimal(animal);
                dao.close();

                carregaLista();

                Toast.makeText(ListaActivity.this, "Animal " + animal.getNome() + " deletado!", Toast.LENGTH_SHORT).show();
                return false;
            }
        });

    }

}
