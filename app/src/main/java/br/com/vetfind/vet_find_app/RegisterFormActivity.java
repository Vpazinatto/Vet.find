package br.com.vetfind.vet_find_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import br.com.vetfind.vet_find_app.DAO.UsuarioDAO;
import br.com.vetfind.vet_find_app.modelo.Usuario;

public class RegisterFormActivity extends AppCompatActivity {

    private ImageButton btn_facebook;
    private String nome;
    private String senha;
    private String email;
    private String nascimento;
    private Button btn_concluido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_form);

        btn_facebook = (ImageButton) findViewById(R.id.register_facebook);

        //btn_facebook.setBackgroundDrawable(R.drawable.register_button_facebook);

        nome = findViewById(R.id.txtPNome).toString() + " " + findViewById(R.id.txtUNome).toString();
        email = findViewById(R.id.txtEmail).toString();
        senha = findViewById(R.id.txtSenha).toString();
        nascimento = findViewById(R.id.txtNasc).toString();
        btn_concluido = findViewById(R.id.btnConc);

        btn_concluido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UsuarioDAO dao = new UsuarioDAO(RegisterFormActivity.this);
                Usuario usuario = new Usuario();
                usuario.setNome(nome);
                usuario.setEmail(email);
                usuario.setSenha(senha);
                usuario.setNascimento(nascimento);
                dao.insertUsuario(usuario);

                dao.close();

                Intent intentVaiProLogin = new Intent(RegisterFormActivity.this, LoginActivity.class);
                startActivity(intentVaiProLogin);

                Toast.makeText(RegisterFormActivity.this, "Usuario " + usuario.getNome() + " Incluido!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
