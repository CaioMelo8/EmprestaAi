package br.ufc.npi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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
	
	@RequestMapping(path = "/salvarUsuario/")
	public String salvarUsuario(@RequestParam("nomeUsuario") String nomeUsuario, 
			@RequestParam("emailUsuario") String emailUsuario, 
				@RequestParam("idadeUsuario") Integer idadeUsuario, @RequestParam("senhaUsuario") String senhaUsuario){
		
		
		Usuario usuario = new Usuario();
		usuario.setNome(nomeUsuario);
		usuario.setEmail(emailUsuario);
		usuario.setIdade(idadeUsuario);
		usuario.setSenha(senhaUsuario);
		
		usuarioService.salvarUsuario(usuario);
		
		return "redirect:/usuario/cadastrarUsuario/";
	}
	
	@RequestMapping(path = "/perfil/{idUsuario}")
	public ModelAndView perfilUsuario(@PathVariable("idUsuario") Integer idUsuario, 
										@RequestParam(name = "erro", required = false) String erro){
		
		ModelAndView model = new ModelAndView("perfilUsuario");
		
		Usuario usuario = usuarioService.buscarUsuario(idUsuario);
		
		model.addObject("usuario", usuario);
		model.addObject("erro", erro);
		
		return model;
	}	
}
