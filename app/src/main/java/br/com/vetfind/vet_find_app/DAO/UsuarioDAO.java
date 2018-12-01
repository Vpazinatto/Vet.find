package br.com.vetfind.vet_find_app.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import br.com.vetfind.vet_find_app.modelo.Animal;
import br.com.vetfind.vet_find_app.modelo.Usuario;

public class UsuarioDAO extends SQLiteOpenHelper{

    public UsuarioDAO(Context context) {
        super(context, "Login", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE Usuarios (id INTEGER PRIMARY KEY, nome TEXT NOT NULL, nascimento TEXT NOT NULL, email TEXT NOT NULL, senha TEXT NOT NULL, caminhoFoto TEXT)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "";
        switch (oldVersion) {
            case 1:
                sql = "ALTER TABLE Usuarios ADD COLUMN caminhoFoto TEXT";
                db.execSQL(sql);
        }
        //onCreate(db);
    }

    @NonNull
    private ContentValues pegaDadosUsuario(Usuario usuario) {

        ContentValues dados = new ContentValues();
        dados.put("nome", usuario.getNome());
        dados.put("email", usuario.getEmail());
        dados.put("nascimento", usuario.getNascimento());
        dados.put("senha", usuario.getSenha());
        dados.put("caminhoFoto", usuario.getCaminhoFoto());
        return dados;
    }

    public void insertUsuario(Usuario usuario) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues dados = pegaDadosUsuario(usuario);

        db.insert("Usuarios", null, dados);
    }

    public void deleteUsuario(Usuario usuario) {
        SQLiteDatabase db = getWritableDatabase();
        String[] params = {usuario.getId().toString()};
        db.delete("Usuarios", "id = ?", params);
    }

    public void updateUsuario(Usuario usuario) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues dados = pegaDadosUsuario(usuario);

        String[] params =  {usuario.getId().toString()};
        db.update("Usuarios", dados, "id = ?", params);
    }

    public List<Usuario> getClientes() {
        String sql = "SELECT * FROM Usuarios";
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(sql, null);

        List<Usuario> usuarios = new ArrayList<Usuario>();
        while(c.moveToNext()) {
            Usuario usuario = new Usuario();
            usuario.setId(c.getLong(c.getColumnIndex("id")));
            usuario.setNome(c.getString(c.getColumnIndex("nome")));
            usuario.setEmail(c.getString(c.getColumnIndex("email")));
            usuario.setNascimento(c.getString(c.getColumnIndex("nascimento")));
            usuario.setSenha(c.getString(c.getColumnIndex("senha")));
            usuario.setCaminhoFoto(c.getString(c.getColumnIndex("caminhoFoto")));
            usuarios.add(usuario);
        }
        c.close();
        return usuarios;
    }

    public boolean validaLogin(String senha, String email)
    {
        String sql = "SELECT * FROM Usuarios WHERE email = " + email;
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(sql, null);

        while (c.moveToNext()) {
            if (c.getString(c.getColumnIndex("email")).equals(email) && c.getString(c.getColumnIndex("senha")).equals(senha))
                return true;
            else
                return false;
        }
        return false;
    }
}
