package br.infnet;

import br.infnet.dto.UsuarioDTOInput;
import br.infnet.service.UsuarioService;

public class Insercao {

    private int responseCode;

    UsuarioService usuarioService = new UsuarioService();
    public int inserirUsuario(UsuarioDTOInput usuarioDTOInput) {

        System.out.println("Inserindo Usuario com id "+ usuarioDTOInput.getId() +" e nome "+ usuarioDTOInput.getNome());

        usuarioService.inserir(usuarioDTOInput);

        return usuarioService.listar().size();

    }
}
