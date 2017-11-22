package br.ufc.npi.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.ufc.npi.beans.Objeto;
import br.ufc.npi.beans.Usuario;
import br.ufc.npi.service.ObjetoService;
import br.ufc.npi.service.UsuarioService;

@Controller
@RequestMapping(path = "/objeto")
public class ObjetoController {

	private static final String MSG_ERRO_REMOCAO = "erroRemocao";

	private static final String MSG_ERRO = "erro";

	private static final String MSG_ERRO_LIMITE_OBJETOS = "O usuário já atingiu o limite de objetos.";

	private static final String MSG_ERRO_OBJ_EMPRESTADO = "O objeto não pode ser removido, pois está emprestado no momento.";

	private static final String USUARIO_REDIRECT_HOME = "redirect:/usuario/home/";

	private static final int MAX_OBJETOS = 10;

	@Autowired
	private ObjetoService objetoService;

	@Autowired
	private UsuarioService usuarioService;

	@RequestMapping(path = "/cadastrarObjeto", method = RequestMethod.POST)
	public ModelAndView cadastrarObjeto(Objeto objeto, HttpSession session) {
		final ModelAndView model = new ModelAndView(USUARIO_REDIRECT_HOME);

		final Usuario usuarioAutenticado = (Usuario) session.getAttribute(UsuarioController.getCampoUsuario());

		if (usuarioAutenticado.getObjetos().size() == MAX_OBJETOS) {
			final String erro = MSG_ERRO_LIMITE_OBJETOS;
			model.addObject(MSG_ERRO, erro);
		} else {
			objeto.setUsuarioDono(usuarioAutenticado);
			usuarioAutenticado.getObjetos().add(objeto);

			this.objetoService.salvarObjeto(objeto);
			this.usuarioService.salvarUsuario(usuarioAutenticado);
		}

		return model;
	}

	@RequestMapping(path = "/removerObjeto/{idObjeto}", method = RequestMethod.GET)
	public ModelAndView removerObjeto(@PathVariable("idObjeto") Integer idObjeto) {
		final Objeto objeto = this.objetoService.buscarObjeto(idObjeto);
		final Usuario usuario = objeto.getUsuarioDono();

		final ModelAndView model = new ModelAndView(USUARIO_REDIRECT_HOME);

		if (objeto.getEmprestimo() != null) {
			final String erro = MSG_ERRO_OBJ_EMPRESTADO;
			model.addObject(MSG_ERRO_REMOCAO, erro);
		} else {
			usuario.getObjetos().remove(objeto);

			this.usuarioService.salvarUsuario(usuario);
			this.objetoService.removerObjeto(objeto);
		}

		return model;
	}
}
