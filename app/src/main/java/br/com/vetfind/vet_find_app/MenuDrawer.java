package br.com.vetfind.vet_find_app;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;

public class MenuDrawer extends Activity {
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_drawer);
    }
}
