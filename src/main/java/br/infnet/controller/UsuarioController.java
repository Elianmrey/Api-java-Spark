package br.infnet.controller;

import br.infnet.dto.UsuarioDTOInput;
import br.infnet.service.UsuarioService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;

import static spark.Spark.*;

@Controller
public class UsuarioController {


    private final UsuarioService usuarioService = new UsuarioService();

    private final ObjectMapper mapper = new ObjectMapper();

    public void respostaRequisicoes(){
        //listar
            get("/usuarios", (request, response) -> {
                response.type("application/json");
                response.status(200);
                String json = mapper.writeValueAsString(usuarioService.listar());
                return json;
            });

            //buscar
            get("/usuarios/:id", (request, response) -> {
                response.type("application/json");
                String idParam = request.params("id");
                int id = Integer.valueOf(idParam);
                String json = mapper.writeValueAsString(usuarioService.buscar(id));
                response.status(200);
                return json;
            });

            //remover
        delete("/usuarios/:id", (request, response) -> {
            response.type("application/json");
            String idParam = request.params("id");
            int id = Integer.valueOf(idParam);
            usuarioService.excluir(id);
            response.status(200);
            return "Usuario excluido com sucesso.";
        });

        //inserir
            post("/usuarios", (request, response) -> {

                UsuarioDTOInput usuarioDTOInput = mapper.readValue(request.body(), UsuarioDTOInput.class);
                usuarioService.inserir(usuarioDTOInput);
                response.type("application/json");
                response.status(201);
                return "Usuario criado com sucesso.";
            });

            //atualizar
            put("/usuarios", (request, response) -> {
                UsuarioDTOInput usuarioDTOInput = mapper.readValue(request.body(), UsuarioDTOInput.class);
                usuarioService.alterar(usuarioDTOInput, usuarioDTOInput.getId());
                response.type("application/json");
                response.status(200);
                return "Usuario alterado com sucesso.";
            });
    }
}
