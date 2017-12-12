package br.ufc.npi.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.ufc.npi.beans.Usuario;
import br.ufc.npi.service.UsuarioService;

@Controller
public class PrincipalController {

	private static final String LOGIN_REDIRECT = "redirect:/login";
	private static final String ROLE_USUARIO = "ROLE_USUARIO";
	private static final String CADASTRO_USUARIO = "cadastroUsuario";
	private static final String LOGIN = "login";
	private static final String USUARIO_HOME_REDIRECT = "redirect:/usuario/home";

	@Autowired
	private UsuarioService usuarioService;

	@RequestMapping(path = "/", method = RequestMethod.GET)
	public String index(HttpSession session) {
		return USUARIO_HOME_REDIRECT;
	}

	@RequestMapping(path = { "", "/login" }, method = { RequestMethod.GET, RequestMethod.POST })
	public String login() {
		return LOGIN;
	}

	@RequestMapping(path = { "/cadastro" }, method = { RequestMethod.GET })
	public String cadastro() {
		return CADASTRO_USUARIO;
	}

	@RequestMapping(path = "/salvarCadastro", method = RequestMethod.POST)
	public String salvarUsuario(Usuario usuario) {
		usuario.setRole(ROLE_USUARIO);
		usuario.setAtivo(true);

		this.usuarioService.salvarUsuario(usuario);

		return LOGIN_REDIRECT;
	}
}
