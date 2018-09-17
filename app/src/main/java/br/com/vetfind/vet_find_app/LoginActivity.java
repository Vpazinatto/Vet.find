package br.com.vetfind.vet_find_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import br.com.vetfind.vet_find_app.DAO.ClienteDAO;
import br.com.vetfind.vet_find_app.DAO.VeterinarioDAO;
import br.com.vetfind.vet_find_app.modelo.Veterinario;

public class LoginActivity extends AppCompatActivity {

    private Button login;
    private Button loginF;
    private Button registrar;
    private EditText txtEmail;
    private EditText txtSenha;
    private Button txtEsqSenha;
    private Button btn_addVet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        login = (Button) findViewById(R.id.btnEntrar);
        txtEsqSenha = (Button) findViewById(R.id.txtEsqueceuSenha);

        btn_addVet = findViewById(R.id.btn_addVet);
        btn_addVet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterVetFormActivity.class);
                startActivity(intent);
            }
        });

        txtEsqSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VeterinarioDAO dao = new VeterinarioDAO(LoginActivity.this);
                Veterinario vet1 = new Veterinario();
                List<Veterinario> lista = dao.getVeterinarios();


                Toast.makeText(LoginActivity.this, "Veterinario " + vet1.getNome() + " excluido!", Toast.LENGTH_SHORT).show();
            }
        });

        loginF = (Button) findViewById(R.id.btnEntrarFacebook);

        txtEmail = findViewById(R.id.email);
        txtSenha = findViewById(R.id.senha);

        final String email = txtEmail.toString();
        final String senha = txtSenha.toString();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentVaiProMapa = new Intent(LoginActivity.this, MapaActivity.class);
                startActivity(intentVaiProMapa);
            }
        });

        loginF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentVaiPraLista = new Intent(LoginActivity.this, ListaActivity.class);
                startActivity(intentVaiPraLista);
            }
        });

        registrar = (Button) findViewById(R.id.btn_registrar);

        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentVaiProFormulario = new Intent(LoginActivity.this, RegisterFormActivity.class);
                startActivity(intentVaiProFormulario);
            }
        });

    }
}