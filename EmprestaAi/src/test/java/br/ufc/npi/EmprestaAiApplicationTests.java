package br.ufc.npi;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import br.ufc.npi.beans.Usuario;
import br.ufc.npi.repositorio.UsuarioRepositorio;
import br.ufc.npi.service.UsuarioService;

@RunWith(SpringRunner.class)
@DataJpaTest
@SpringBootTest(classes = EmprestaAiApplication.class)
public class EmprestaAiApplicationTests {

	@Autowired
	private UsuarioRepositorio usuarioRepositorio;

	@MockBean
	private UsuarioService usuarioService;

	@Test
	public void testeRepositorioSalvarUsuario() {
		Usuario usuario = new Usuario();
		usuario.setId(1);

		Usuario usuarioSalvo = usuarioRepositorio.save(usuario);

		Usuario usuarioBuscado = usuarioRepositorio.getOne(usuario.getId());

		assertNotNull(usuarioSalvo);
		assertEquals(usuario, usuarioBuscado);
	}

}
