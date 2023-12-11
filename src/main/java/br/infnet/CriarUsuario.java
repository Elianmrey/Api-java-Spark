package br.infnet;

import br.infnet.dto.UsuarioDTOInput;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class CriarUsuario {

    @SneakyThrows
    public int testeCriacaoUsuarioFornecidoPorApiExterna(String apiUrl) {


        URL url = new URL(apiUrl);

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode();
        if (responseCode == 200) {
            System.out.println("Conexão com 'https://randomuser.me/api/' estabelecida corretamente");
        }

        StringBuilder response = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line);

        }
        connection.disconnect();

        ObjectMapper mapper = new ObjectMapper();


        JsonNode jsonNode = mapper.readTree(response.toString());
        String nome = jsonNode.at("/results/0/name/first").asText();
        String senha = jsonNode.at("/results/0/login/password").asText();

        //Quando gera Id Zero (0) é porque o numero é muito grande e teria que mudar
        // os tipos de dados das classes, o que foge das instruções do AT.
        int id = jsonNode.at("/results/0/id/value").asInt();


        UsuarioDTOInput novoUsuario = new UsuarioDTOInput();

        novoUsuario.setId(id);
        novoUsuario.setNome(nome);
        novoUsuario.setSenha(senha);

        JSONObject newJSON = new JSONObject();

        newJSON.put("id", novoUsuario.getId());
        newJSON.put("nome", novoUsuario.getNome());
        newJSON.put("senha", novoUsuario.getSenha());

        System.out.println(newJSON);


        // Postando usuario na API Desenvolvida

        String rota = "http://localhost:4567/usuarios";

        URL url2 = new URL(rota);

        HttpURLConnection conexao = (HttpURLConnection) url2.openConnection();

        conexao.setRequestProperty("Accept", "application/json");
        conexao.setDoOutput(true);
        conexao.setRequestMethod("POST");

        conexao.getOutputStream().write(newJSON.toString().getBytes());

        int resposeCode2 = conexao.getResponseCode();

        System.out.println("Codigo resposta" + resposeCode2);

        return resposeCode2;
    }

    @SneakyThrows
    public int testeCriacaoUsuarioComJSONeJSONarray(String apiUrl) {


        URL url = new URL(apiUrl);

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode();

        StringBuilder response = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line);

        }
        connection.disconnect();

        JSONObject jsonObject = new JSONObject(response.toString());
        JSONArray jsonArray = jsonObject.getJSONArray("results");


        String nome = jsonArray.getJSONObject(0)
                .getJSONObject("name")
                .getString("first");


        String senha = jsonArray.getJSONObject(0)
                .getJSONObject("login")
                .getString("password");

        //obtendo Id da API fornecida
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.readTree(response.toString());

        //Quando gera Id Zero (0) é porque o numero é muito grande e teria que mudar
        // os tipos de dados das classes, o que foge das instruções do AT.
        int id = jsonNode.at("/results/0/id/value").asInt();

        UsuarioDTOInput novoUsuario = new UsuarioDTOInput();

        novoUsuario.setId(id);
        novoUsuario.setNome(nome);
        novoUsuario.setSenha(senha);

        JSONObject newJSON = new JSONObject();

        newJSON.put("id", novoUsuario.getId());
        newJSON.put("nome", novoUsuario.getNome());
        newJSON.put("senha", novoUsuario.getSenha());

        System.out.println(newJSON);


        // Postando usuario na API Desenvolvida

        String rota = "http://localhost:4567/usuarios";

        URL url2 = new URL(rota);

        HttpURLConnection conexao = (HttpURLConnection) url2.openConnection();

        conexao.setRequestProperty("Accept", "application/json");
        conexao.setDoOutput(true);
        conexao.setRequestMethod("POST");

        conexao.getOutputStream().write(newJSON.toString().getBytes());

        int resposeCode2 = conexao.getResponseCode();

        System.out.println("Codigo resposta" + resposeCode2);

        return resposeCode2;


    }
}
