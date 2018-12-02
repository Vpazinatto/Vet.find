package br.com.vetfind.vet_find_app;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

import br.com.vetfind.vet_find_app.modelo.Usuario;

public class ConnectionApi
{
    private static HttpURLConnection conexao;
    private static InputStream is;
    private static BufferedReader reader;

    //Responsavel por carregar o Objeto JSON
    public static String getUsuarioFromApi(String url) {
        String retorno = "";
        try
        {
            System.out.print("URL: " + url);
            URL apiEnd = new URL(url);

            conexao = (HttpURLConnection) apiEnd.openConnection();
            conexao.setRequestMethod("GET");
            conexao.connect();

            if(conexao.getResponseCode() < HttpURLConnection.HTTP_BAD_REQUEST)
                is = conexao.getInputStream();
            else
                is = conexao.getErrorStream();

            retorno = converterInputStreamToString(is);
            is.close();
            conexao.disconnect();

        } catch (MalformedURLException e){
            e.printStackTrace();
        } catch(UnknownHostException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }

        return retorno;
    }

    //Responsavel por validar usuário
    public static String validateUserFromApi(Usuario usuario) {
        try {
            conexao = (HttpURLConnection) new URL("http://10.0.2.2:3000/usuarios/login").openConnection();
            conexao.setRequestMethod("POST");
            conexao.setDoOutput(true);
            conexao.setRequestProperty("Content-Type", "application/json; charset=utf-8");

            JSONObject json = new JSONObject();
            json.accumulate("email", usuario.getEmail());
            json.accumulate("senha", usuario.getSenha());

            setPostRequestContent(conexao, json);

            int responseCode=conexao.getResponseCode();
            if (responseCode == HttpsURLConnection.HTTP_OK) {
                reader = new BufferedReader(new InputStreamReader(conexao.getInputStream()));
                return reader.readLine();
            } else
                return null;

        } catch (MalformedURLException e){
            e.printStackTrace();
        } catch(UnknownHostException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    //Responsavel por inserir usuário
    public static boolean insertUsuarioInApi(Usuario usuario) {
        try {
            conexao = (HttpURLConnection) new URL("http://10.0.2.2:3000/usuarios/usuario").openConnection();
            conexao.setRequestMethod("POST");
            conexao.setDoOutput(true);
            conexao.setRequestProperty("Content-Type", "application/json; charset=utf-8");

            String data = setDate(usuario.getNascimento());

            JSONObject json = new JSONObject();
            json.put("nome", usuario.getNome());
            json.put("nascimento", data);
            json.put("email", usuario.getEmail());
            json.put("CPF", usuario.getCPF());
            json.put("senha", usuario.getSenha());
            json.put("caminhoFoto", usuario.getCaminhoFoto());

            setPostRequestContent(conexao, json);

            int responseCode=conexao.getResponseCode();
            if (responseCode == HttpsURLConnection.HTTP_CREATED) {
                return true;
            } else
                return false;

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return false;
    }

    private static String setDate(String date) {
        String[] separada = date.split("/");
        date = separada[2] + "/" + separada[1] + "/" + separada[0];
        return date;
    }

    private static void setPostRequestContent(HttpURLConnection conn, JSONObject jsonObject) throws IOException {
        OutputStream os = conn.getOutputStream();
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
        writer.write(jsonObject.toString());
        Log.i(ConnectionApi.class.toString(), jsonObject.toString());
        writer.flush();
        writer.close();
        os.close();
    }

    private static String converterInputStreamToString(InputStream is) {
        StringBuffer buffer = new StringBuffer();
        try
        {
            BufferedReader br;
            String linha;

            br = new BufferedReader(new InputStreamReader(is));

            while((linha = br.readLine()) != null)
                buffer.append(linha);

            br.close();
        }catch(IOException e)
        {
            e.printStackTrace();
        }

        return buffer.toString();
    }
}