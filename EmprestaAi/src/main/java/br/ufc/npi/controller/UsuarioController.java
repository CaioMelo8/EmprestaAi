package br.ufc.npi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.ufc.npi.beans.Usuario;
import br.ufc.npi.service.UsuarioService;

@Controller
@RequestMapping(path = "/usuario/")
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@RequestMapping(path = "/cadastrarUsuario/")
	public ModelAndView cadastrarUsuario(){
		ModelAndView model = new ModelAndView("cadastroUsuario");
		List<Usuario> usuarios = usuarioService.buscarTodosUsuarios();
		
		model.addObject("usuarios", usuarios);
		
		return model;
	}
	
	@RequestMapping(path = "/salvarUsuario/", method = RequestMethod.POST)
	public String salvarUsuario(Usuario usuario){
		usuario.setRole("ROLE_USUARIO");
		usuario.setAtivo(true);
		
		usuarioService.salvarUsuario(usuario);
		
		return "redirect:/usuario/cadastrarUsuario/";
	}
	
	@RequestMapping(path = "/perfil/{idUsuario}", method = RequestMethod.GET)
	public ModelAndView perfilUsuario(@PathVariable("idUsuario") Integer idUsuario, 
										@RequestParam(name = "erro", required = false) String erro,
											@RequestParam(name = "erroRemocao", required = false) String erroRemocao){
		
		ModelAndView model = new ModelAndView("perfilUsuario");
		
		Usuario usuario = usuarioService.buscarUsuario(idUsuario);
		
		model.addObject("usuario", usuario);
		model.addObject("erro", erro);
		model.addObject("erroRemocao", erroRemocao);
		
		return model;
	}
}
