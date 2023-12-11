package br.infnet.service;

import br.infnet.dto.UsuarioDTOInput;
import br.infnet.dto.UsuarioDTOOutput;
import br.infnet.model.Usuario;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class UsuarioService {


    List<Usuario> listaUsuarios = new ArrayList<>();


    ModelMapper mapeador = new ModelMapper();

    public List<UsuarioDTOOutput> listar() {

       List<UsuarioDTOOutput> listaUsuarioDTOOutput = new ArrayList();
      for(Usuario usuario : listaUsuarios) {
          listaUsuarioDTOOutput.add(mapeador.map(usuario, UsuarioDTOOutput.class));
      }
      return listaUsuarioDTOOutput;

    }
    public void inserir(UsuarioDTOInput usuarioDTOInput) {
        Usuario novoUsuario = mapeador.map(usuarioDTOInput, Usuario.class);
        listaUsuarios.add(novoUsuario);
    }

    public void alterar(UsuarioDTOInput usuarioDtoInput,int id) {

          Usuario usuarioExiste =  listaUsuarios.stream()
                     .filter(usuarioEncontrado -> usuarioEncontrado.getId() == id)
                     .findFirst()
                     .orElse(null);

          if(usuarioExiste != null) {
              mapeador.map(usuarioDtoInput, usuarioExiste);
          }else {

              throw new RuntimeException("Usuário com ID: " + id + " não encontrado");
          }

    }

    public UsuarioDTOOutput buscar(int id)
    {
        Usuario usuarioExiste =  listaUsuarios.stream()
                .filter(usuarioEncontrado -> usuarioEncontrado.getId() == id)
                .findFirst()
                .orElse(null);

        if(usuarioExiste == null) {
            throw new RuntimeException("Usuário com ID: " + id + "não encontrado");
        }
          return  mapeador.map(usuarioExiste,UsuarioDTOOutput.class);
    }


    public void excluir(int id) {
        listaUsuarios.removeIf(usuarioEncontrado -> usuarioEncontrado.getId() == id);
    }
}
