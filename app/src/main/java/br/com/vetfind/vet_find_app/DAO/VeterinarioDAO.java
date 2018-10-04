package br.com.vetfind.vet_find_app.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import br.com.vetfind.vet_find_app.modelo.Raca;
import br.com.vetfind.vet_find_app.modelo.Usuario;
import br.com.vetfind.vet_find_app.modelo.Veterinario;

public class VeterinarioDAO extends SQLiteOpenHelper{

    public VeterinarioDAO(Context context) {
        super(context, "Veterinarios", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE Veterinarios (id INTEGER PRIMARY KEY AUTOINCREMENT, nome TEXT NOT NULL, nascimento TEXT NOT NULL, email TEXT NOT NULL, senha TEXT NOT NULL, caminhoFoto TEXT, endereco TEXT NOT NULL, telefone TEXT NOT NULL)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "";
        switch (oldVersion) {
            case 1:
                sql = "ALTER TABLE Veterinarios ADD COLUMN telefone TEXT";
                db.execSQL(sql);
        }
        onCreate(db);
    }

    @NonNull
    private ContentValues pegaDadosVeterinario(Veterinario veterinario) {
        ContentValues dados = new ContentValues();
        dados.put("nome", veterinario.getNome());
        dados.put("email", veterinario.getEmail());
        dados.put("nascimento", veterinario.getNascimento());
        dados.put("senha", veterinario.getSenha());
        dados.put("telefone", veterinario.getTelefone());
        dados.put("caminhoFoto", veterinario.getCaminhoFoto());
        dados.put("endereco", veterinario.getEndereco());
        return dados;
    }

    public void insertVeterinario(Veterinario veterinario) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues dados = pegaDadosVeterinario(veterinario);

        db.insert("Veterinarios", null, dados);
    }

    public void deleteUsuario(Veterinario veterinario) {
        SQLiteDatabase db = getWritableDatabase();
        String[] params = {veterinario.getId().toString()};
        db.delete("Veterinarios", "id = ?", params);
    }

    public List<Veterinario> getVeterinarios() {
        String sql = "SELECT * FROM Veterinarios";
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(sql, null);

        List<Veterinario> veterinarios = new ArrayList<Veterinario>();
        while(c.moveToNext()) {
            Veterinario veterinario = new Veterinario();
            veterinario.setId(c.getLong(c.getColumnIndex("id")));
            veterinario.setNome(c.getString(c.getColumnIndex("nome")));
            veterinario.setEmail(c.getString(c.getColumnIndex("email")));
            veterinario.setNascimento(c.getString(c.getColumnIndex("nascimento")));
            veterinario.setSenha(c.getString(c.getColumnIndex("senha")));
            veterinario.setCaminhoFoto(c.getString(c.getColumnIndex("caminhoFoto")));
            veterinario.setEndereco(c.getString(c.getColumnIndex("endereco")));
            veterinarios.add(veterinario);
        }
        c.close();
        return veterinarios;
    }


}
