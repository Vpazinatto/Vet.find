package br.com.vetfind.vet_find_app;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

import br.com.vetfind.vet_find_app.modelo.Usuario;

public class Utils
{
    public Usuario getUsuario(String end)
    {
        String json;
        Usuario retorno;

        json = ConnectionApi.getUsuarioFromApi(end);
        Log.i("Resultado: ", json);
        retorno = parseJson(json);

        return retorno;
    }

    private Usuario parseJson(String json)
    {
        try
        {
            Usuario usuario = new Usuario();

            JSONObject jsonObj = new JSONObject(json);
            JSONArray array = jsonObj.getJSONArray("recordset");

            JSONObject obj = array.getJSONObject(0);

            //Criando um usuario a partir do JSON
            usuario.setId(obj.getLong("IDUsuario"));
            usuario.setNome(obj.getString("nome"));
            usuario.setNascimento(obj.getString("nascimento"));
            usuario.setEmail(obj.getString("email"));
            usuario.setCPF(obj.getString("CPF"));
            usuario.setCaminhoFoto(obj.getString("caminhoFoto"));

            return usuario;
        }catch (JSONException e)
        {
            e.printStackTrace();
            return null;
        }
    }
}