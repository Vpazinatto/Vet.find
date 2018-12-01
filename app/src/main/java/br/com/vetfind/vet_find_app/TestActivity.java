package br.com.vetfind.vet_find_app;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import br.com.vetfind.vet_find_app.modelo.Usuario;

public class TestActivity extends Activity {

    private ProgressDialog load;

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
                GetJson g = new GetJson();
                g.execute();
            }
        });
    }


}
