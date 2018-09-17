package br.com.vetfind.vet_find_app.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import br.com.vetfind.vet_find_app.modelo.Animal;
import br.com.vetfind.vet_find_app.modelo.Raca;

public class RacaDAO extends SQLiteOpenHelper {

    public RacaDAO(Context context) {
        super(context, "Raca", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE Racas (id INTEGER PRIMARY KEY, nome TEXT NOT NULL, idEspecie NOT NULL)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //onCreate(db);
    }

    @NonNull
    private ContentValues pegaDadosRaca(Raca raca) {
        ContentValues dados = new ContentValues();
        dados.put("nome", raca.getNome());
        return dados;
    }

    public void insertRaca(Raca raca) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues dados = pegaDadosRaca(raca);

        db.insert("Racas", null, dados);
    }

    public void deleteRaca(Raca raca) {
        SQLiteDatabase db = getWritableDatabase();
        String[] params = {raca.getId().toString()};
        db.delete("Racas", "id = ?", params);
    }

    public void updateRaca(Raca raca) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues dados = pegaDadosRaca(raca);

        String[] params =  {raca.getId().toString()};
        db.update("Racas", dados, "id = ?", params);
    }

    public List<Raca> getRacas() {
        String sql = "SELECT * FROM Racas";
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(sql, null);

        List<Raca> racas = new ArrayList<Raca>();
        while(c.moveToNext()) {
            Raca raca = new Raca();
            raca.setId(c.getLong(c.getColumnIndex("id")));
            raca.setNome(c.getString(c.getColumnIndex("nome")));
            racas.add(raca);
        }
        c.close();
        return racas;
    }

}
