package br.com.vetfind.vet_find_app;

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

    //Responsavel por carregar o Objeto JSON
    public static String getUsuarioFromApi(String url)
    {
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

    public static Boolean validateUserFromApi(String url, Usuario usuario) {
        try {
            URL apiEnd = new URL(url);
            String response = "";
            BufferedReader reader=null;
            String text = "";

            conexao = (HttpURLConnection) apiEnd.openConnection();
            conexao.setRequestMethod("POST");
            conexao.setDoOutput(true);

            HashMap<String, String> params = new HashMap<String,String>();
            params.put("nome", usuario.getEmail());
            params.put("senha",usuario.getSenha());

            OutputStreamWriter wr = new OutputStreamWriter(conexao.getOutputStream());
            wr.write(String.valueOf(params));
            wr.flush();



            int responseCode=conexao.getResponseCode();
            if (responseCode == HttpsURLConnection.HTTP_OK) {
                reader = new BufferedReader(new InputStreamReader(conexao.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String line = null;

                // Read Server Response
                while((line = reader.readLine()) != null)
                {
                    // Append server response in string
                    sb.append(line + "\n");
                }


                text = sb.toString();
                return true;
            } else
                return false;

        } catch (MalformedURLException e){
            e.printStackTrace();
        } catch(UnknownHostException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }

        return true;
    }

    private static String getPostDataString(HashMap<String, String> params) throws UnsupportedEncodingException{
        StringBuilder result = new StringBuilder();
        boolean first = true;
        for(Map.Entry<String, String> entry : params.entrySet()){
            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
        }

        return result.toString();
    }

    private static String converterInputStreamToString(InputStream is)
    {
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