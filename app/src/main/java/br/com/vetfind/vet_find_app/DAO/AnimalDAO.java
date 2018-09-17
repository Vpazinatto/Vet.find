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

public class AnimalDAO extends SQLiteOpenHelper{

    public AnimalDAO(Context context) {
        super(context, "Agenda", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE Animais (id INTEGER PRIMARY KEY, nome TEXT NOT NULL, idade TEXT NOT NULL, sexo TEXT NOT NULL, especie TEXT, raca TEXT, caminhoFoto TEXT)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "";
        switch (oldVersion) {
            case 1:
                sql = "ALTER TABLE Animais ADD COLUMN caminhoFoto TEXT";
                db.execSQL(sql);
        }
        //onCreate(db);
    }

    @NonNull
    private ContentValues pegaDadosAnimal(Animal animal) {

        ContentValues dados = new ContentValues();
        dados.put("nome", animal.getNome());
        dados.put("idade", animal.getIdade());
        dados.put("sexo", animal.getSexo());
        dados.put("especie", animal.getEspecie());
        dados.put("raca", animal.getRaca());
        dados.put("caminhoFoto", animal.getCaminhoFoto());
        return dados;
    }

    public void insertAnimal(Animal animal) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues dados = pegaDadosAnimal(animal);

        db.insert("Animais", null, dados);
    }

    public void deleteAnimal(Animal animal) {
        SQLiteDatabase db = getWritableDatabase();
        String[] params = {animal.getId().toString()};
        db.delete("Animais", "id = ?", params);
    }

    public void updateAnimal(Animal animal) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues dados = pegaDadosAnimal(animal);

        String[] params =  {animal.getId().toString()};
        db.update("Animais", dados, "id = ?", params);
    }

    public List<Animal> getAnimais() {
        String sql = "SELECT * FROM Animais";
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(sql, null);

        List<Animal> animais = new ArrayList<Animal>();
        while(c.moveToNext()) {
            Animal animal = new Animal();
            animal.setId(c.getLong(c.getColumnIndex("id")));
            animal.setNome(c.getString(c.getColumnIndex("nome")));
            animal.setIdade(c.getString(c.getColumnIndex("idade")));
            animal.setSexo(c.getString(c.getColumnIndex("sexo")));
            animal.setEspecie(c.getString(c.getColumnIndex("especie")));
            animal.setRaca(c.getString(c.getColumnIndex("raca")));
            animal.setCaminhoFoto(c.getString(c.getColumnIndex("caminhoFoto")));
            animais.add(animal);
        }
        c.close();
        return animais;
    }

}
