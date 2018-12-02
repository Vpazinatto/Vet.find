package br.com.vetfind.vet_find_app;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.sql.Date;

import br.com.vetfind.vet_find_app.modelo.Usuario;

public class RegisterFormActivity extends Activity {

    private ProgressDialog load;

    private ImageButton btn_facebook;
    private EditText txtNome;
    private EditText txtSobreNome;
    private EditText txtEmail;
    private EditText txtNascimento;
    private EditText txtCPF;
    private EditText txtSenha;
    private Button btn_concluido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_form);

        btn_facebook = (ImageButton) findViewById(R.id.register_facebook);

        txtNome = findViewById(R.id.txtPNome);
        txtSobreNome = findViewById(R.id.txtUNome);
        txtEmail = findViewById(R.id.txtEmail);
        txtNascimento = findViewById(R.id.txtNasc);
        txtCPF = findViewById(R.id.txtCPF);
        txtSenha = findViewById(R.id.txtSenha);
        btn_concluido = findViewById(R.id.btnConc);

        btn_concluido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetJson insert = new GetJson();
                insert.execute();
            }
        });
    }

    private class GetJson extends AsyncTask<Void, Void, Boolean>
    {
        @Override
        protected void onPreExecute()
        {
            load = ProgressDialog.show(RegisterFormActivity.this,
                    "Por favor Aguarde ...", "Inserindo Usuário...");
        }

        @Override
        protected Boolean doInBackground(Void... params)
        {
            Utils util = new Utils();
            Usuario user = new Usuario();
            user.setNome(txtNome.getText().toString() + " " + txtSobreNome.getText().toString());
            user.setEmail(txtEmail.getText().toString());
            user.setNascimento(txtNascimento.getText().toString());
            if (user.isCPF(txtCPF.getText().toString()))
                user.setCPF(txtCPF.getText().toString());
            else
                return false;
            user.setSenha(txtSenha.getText().toString());

            //se estiver rodando no emulador usar o IP 10.0.2.2 Se for no celular 127.0.0.1
            return util.insereUsuario(user);
        }

        @Override
        protected void onPostExecute(Boolean ok)
        {
            if (ok) {
                Toast.makeText(RegisterFormActivity.this, "Usuário Incluido!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(new Intent(RegisterFormActivity.this, LoginActivity.class)));
            } else
                Toast.makeText(RegisterFormActivity.this, "Erro ao inserir usuário", Toast.LENGTH_SHORT).show();

            load.dismiss();
        }
    }
}
