import br.infnet.CriarUsuario;
import br.infnet.Insercao;
import br.infnet.ReqListarUsuario;
import br.infnet.TesteServiceCompleto;
import br.infnet.dto.UsuarioDTOInput;
import br.infnet.service.UsuarioService;
import lombok.SneakyThrows;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestesUnitarios {

    UsuarioService usuarioService = new UsuarioService();

    @Test
    public void testarInsercao() {

        UsuarioDTOInput usuarioDTOInput = new UsuarioDTOInput();
        usuarioDTOInput.setId(1);
        usuarioDTOInput.setNome("Jorge");
        usuarioDTOInput.setSenha("654654");

        Insercao testeInsercao = new Insercao();

        int tamanhoLista = testeInsercao.inserirUsuario(usuarioDTOInput);

        assertEquals(1, tamanhoLista);
    }

    @Test
    public void testarServiceCompleto() {

        TesteServiceCompleto testeService = new TesteServiceCompleto();

        UsuarioDTOInput usuarioDTOInput = new UsuarioDTOInput();
        usuarioDTOInput.setId(1);
        usuarioDTOInput.setNome("Jorge");
        usuarioDTOInput.setSenha("654654");

       boolean isEmty =  testeService.testarServiceCompleto(usuarioDTOInput);

       assertTrue( isEmty );
    }

    @Test
    public void testListagemUsuarios() throws IOException {

        String apiUrl = "http://localhost:4567/usuarios";

        ReqListarUsuario reqListarUsuario = new ReqListarUsuario();

        int resposta = reqListarUsuario.testListagemUsuarios(apiUrl);

        assertEquals(200, resposta);
    }

    @Test
    @SneakyThrows
    public void testeCriacaoUsuarioFornecidoPorApiExterna() {
        String apiUrl = "https://randomuser.me/api/";

        CriarUsuario criarUsuario = new CriarUsuario();

        criarUsuario.testeCriacaoUsuarioFornecidoPorApiExterna(apiUrl);

    }

    @Test
    @SneakyThrows
    public void testeCriacaoUsuarioComJSONeJSONarray() {
        String apiUrl = "https://randomuser.me/api/";

        CriarUsuario criarUsuario = new CriarUsuario();

        int resposeCode2 = criarUsuario.testeCriacaoUsuarioComJSONeJSONarray(apiUrl);

        assertEquals(201, resposeCode2);


    }


}
