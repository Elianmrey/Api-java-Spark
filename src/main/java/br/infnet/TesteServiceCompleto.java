package br.infnet;

import br.infnet.dto.UsuarioDTOInput;
import br.infnet.service.UsuarioService;

public class TesteServiceCompleto {

    UsuarioService usuarioService = new UsuarioService();

    public boolean testarServiceCompleto(UsuarioDTOInput usuarioDTOInput) {


        System.out.println("Inserindo Usuario com id 1 e nome Jorge");
        usuarioService.inserir(usuarioDTOInput);
        System.out.println("Buscando Usuario com id 1 ");
        System.out.println(usuarioService.buscar(1));

        usuarioDTOInput.setNome("João");
        usuarioDTOInput.setId(40);

        System.out.println("Alterando Usuario com id 1 para nome: João id: 40 ");
        usuarioService.alterar(usuarioDTOInput, 1);

        System.out.println("Listando usuarios já alterados" + usuarioService.listar());

        System.out.println("Buscando usuario alterado agora com id 40" + usuarioService.buscar(40));



        System.out.println("Deletando usuario agora com id 40");
        usuarioService.excluir(40);
        if (usuarioService.listar().isEmpty()) {
            System.out.println("Usuario Deletado com sucesso");
        }

     return usuarioService.listar().isEmpty();
    }
}
