package br.ufc.npi.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.ufc.npi.beans.Usuario;
import br.ufc.npi.service.UsuarioService;

@Controller
@RequestMapping(path = "/usuario")
public class UsuarioController {

	private static final String MSG_ERRO_REMOCAO = "erroRemocao";

	private static final String MSG_ERRO = "erro";

	private static final String CADASTRO_USUARIO = "cadastroUsuario";

	private static final String CAMPO_USUARIO = "usuario";

	@Autowired
	private UsuarioService usuarioService;

	public static String getCampoUsuario() {
		return UsuarioController.CAMPO_USUARIO;
	}

	@RequestMapping(path = "/cadastrarUsuario", method = RequestMethod.GET)
	public String cadastrarUsuario() {
		return CADASTRO_USUARIO;
	}

	@RequestMapping(path = { "", "/", "/home" }, method = RequestMethod.GET)
	public ModelAndView homeUsuario(@RequestParam(name = MSG_ERRO, required = false) String erro,
			@RequestParam(name = MSG_ERRO_REMOCAO, required = false) String erroRemocao, HttpSession session) {

		final ModelAndView model = new ModelAndView("homeUsuario");

		final Usuario usuarioAutenticado = (Usuario) session.getAttribute(CAMPO_USUARIO);
		final Usuario usuario = this.usuarioService.buscarUsuario(usuarioAutenticado.getId());

		session.setAttribute(CAMPO_USUARIO, usuario);

		model.addObject(MSG_ERRO, erro);
		model.addObject(MSG_ERRO_REMOCAO, erroRemocao);

		return model;
	}
}
