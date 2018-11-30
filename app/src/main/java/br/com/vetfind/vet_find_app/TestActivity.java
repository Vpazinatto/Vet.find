package br.com.vetfind.vet_find_app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import br.com.vetfind.vet_find_app.modelo.Usuario;

public class TestActivity extends AppCompatActivity {

    private TextView txtTeste;
    private Button btn_testar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        btn_testar = findViewById(R.id.btnConc);
        txtTeste = findViewById(R.id.txtTeste);

        btn_testar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils util = new Utils();

                Usuario user = util.getUsuario("http://192.168.0.1:3000/usuarios");
                txtTeste.setText(user.getNome());

            }
        });
    }
}
