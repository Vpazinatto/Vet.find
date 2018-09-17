package br.com.vetfind.vet_find_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.com.vetfind.vet_find_app.DAO.ClienteDAO;
import br.com.vetfind.vet_find_app.DAO.VeterinarioDAO;
import br.com.vetfind.vet_find_app.modelo.Usuario;
import br.com.vetfind.vet_find_app.modelo.Veterinario;

public class RegisterVetFormActivity extends AppCompatActivity {

    private EditText nome;
    private EditText senha;
    private EditText email;
    private EditText nascimento;
    private EditText telefone;
    private EditText endereco;
    private EditText sobrenome;
    private Button btn_concluido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_vet_form);

        btn_concluido = findViewById(R.id.btnConc);

        btn_concluido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nome = (EditText) findViewById(R.id.txtPNome);
                sobrenome = (EditText) findViewById(R.id.txtUNome);
                email = (EditText) findViewById(R.id.txtEmail);
                senha = (EditText) findViewById(R.id.txtSenha);
                endereco = (EditText) findViewById(R.id.txtEndereco);
                nascimento = (EditText) findViewById(R.id.txtNasc);
                telefone = (EditText) findViewById(R.id.txtTelefone);

                VeterinarioDAO dao = new VeterinarioDAO(RegisterVetFormActivity.this);
                Veterinario veterinario = new Veterinario();
                veterinario.setNome(nome.getText().toString());
                veterinario.setEmail(email.getText().toString());
                veterinario.setSenha(senha.getText().toString());
                veterinario.setNascimento(nascimento.getText().toString());
                veterinario.setTelefone(telefone.getText().toString());
                veterinario.setEndereco(endereco.getText().toString());
                dao.insertVeterinario(veterinario);

                dao.close();

                Intent intentVaiProMapa = new Intent(RegisterVetFormActivity.this, MapaActivity.class);
                startActivity(intentVaiProMapa);

                Toast.makeText(RegisterVetFormActivity.this, "Veterianrios " + veterinario.getNome() + " Incluido!", Toast.LENGTH_SHORT).show() ;
            }
        });
    }
}
