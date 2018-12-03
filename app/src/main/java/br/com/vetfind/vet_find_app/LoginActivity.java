package br.com.vetfind.vet_find_app;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import br.com.vetfind.vet_find_app.DAO.VeterinarioDAO;
import br.com.vetfind.vet_find_app.modelo.Usuario;
import br.com.vetfind.vet_find_app.modelo.Veterinario;

public class LoginActivity extends Activity {

    private ProgressDialog load;

    private Button btnLogin;
    private Button btnLoginF;
    private Button btnRegistrar;
    private EditText txtEmail;
    private EditText txtSenha;
    private Button txtEsqSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtEmail = findViewById(R.id.email);
        txtSenha = findViewById(R.id.senha);
        btnLogin = (Button) findViewById(R.id.btnEntrar);
        btnLoginF = (Button) findViewById(R.id.btnEntrarFacebook);
        txtEsqSenha = (Button) findViewById(R.id.txtEsqueceuSenha);
        btnRegistrar = (Button) findViewById(R.id.btn_registrar);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginActivity.GetJson g = new LoginActivity.GetJson();
                g.execute();
                //startActivity(new Intent(LoginActivity.this, MapaActivity.class));
            }
        });

        btnLoginF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentVaiPraLista = new Intent(LoginActivity.this, ListaActivity.class);
                startActivity(intentVaiPraLista);
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

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentVaiProFormulario = new Intent(LoginActivity.this, RegisterFormActivity.class);
                startActivity(intentVaiProFormulario);
            }
        });
    }

    private class GetJson extends AsyncTask<Void, Void, Usuario>
    {
        @Override
        protected void onPreExecute()
        {
            load = ProgressDialog.show(LoginActivity.this,
                    "Por favor Aguarde ...", "Logando no servidor...");
        }

        @Override
        protected Usuario doInBackground(Void... params)
        {
            Utils util = new Utils();
            Usuario user = new Usuario();
            user.setEmail(txtEmail.getText().toString());
            user.setSenha(txtSenha.getText().toString());

            //se estiver rodando no emulador usar o IP 10.0.2.2 Se for no celular 127.0.0.1
            return util.validaUsuario(user);
        }

        @Override
        protected void onPostExecute(Usuario usuario)
        {
            if (usuario.getId() != null) {
                Toast.makeText(LoginActivity.this, "Bem vindo " + usuario.getNome() + "!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(LoginActivity.this, MapaActivity.class));
            } else
                Toast.makeText(LoginActivity.this, "Login Inválido", Toast.LENGTH_SHORT).show();

            load.dismiss();
        }
    }
}
