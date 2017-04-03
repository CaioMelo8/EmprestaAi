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
	
	@Autowired
	private UsuarioService usuarioService;
	
	@RequestMapping(path = "/", method = RequestMethod.GET)
	public String index(HttpSession session){		
		return "index";
	}
	
	@RequestMapping(path = {"", "/login"}, method = {RequestMethod.GET, RequestMethod.POST})
	public String login(){
		return "login";
	}
	
	@RequestMapping(path = {"/cadastro"}, method = {RequestMethod.GET})
	public String codastro(){
		return "cadastroUsuario";
	}
	
	@RequestMapping(path = "/salvarCadastro", method = RequestMethod.POST)
	public String salvarUsuario(Usuario usuario){
		usuario.setRole("ROLE_USUARIO");
		usuario.setAtivo(true);
		
		usuarioService.salvarUsuario(usuario);
		
		return "redirect:/login";
	}
}
