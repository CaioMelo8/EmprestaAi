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
	
	@Autowired
	private UsuarioService usuarioService;
	
	@RequestMapping(path = "/cadastrarUsuario", method = RequestMethod.GET)
	public String cadastrarUsuario(){
		return "cadastroUsuario";
	}
	
	@RequestMapping(path = {"", "/", "/home"}, method = RequestMethod.GET)
	public ModelAndView homeUsuario(@RequestParam(name = "erro", required = false) String erro,
										@RequestParam(name = "erroRemocao", required = false) String erroRemocao, 
											HttpSession session){
		
		ModelAndView model = new ModelAndView("homeUsuario");			
		
		Usuario usuarioAutenticado = (Usuario) session.getAttribute("usuario");
		Usuario usuario = usuarioService.buscarUsuario(usuarioAutenticado.getId());
		
		session.setAttribute("usuario", usuario);
		
		model.addObject("erro", erro);
		model.addObject("erroRemocao", erroRemocao);
		
		return model;
	}
}
