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
	
	@Autowired
	private ObjetoService objetoService;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@RequestMapping(path = "/cadastrarObjeto", method = RequestMethod.POST)
	public ModelAndView cadastrarObjeto(Objeto objeto, HttpSession session){
		ModelAndView model = new ModelAndView("redirect:/usuario/home/");
		
		Usuario usuarioAutenticado = (Usuario) session.getAttribute("usuario");
		
		Usuario usuario = usuarioService.buscarUsuario(usuarioAutenticado.getId());
		
		if (usuario.getObjetos().size() == 10){
			String erro = "O usuário já atingiu o limite de objetos.";
			model.addObject("erro", erro);
		}
		else{			
			objeto.setUsuarioDono(usuario);
			usuario.getObjetos().add(objeto);
			
			objetoService.salvarObjeto(objeto);
			usuarioService.salvarUsuario(usuario);
			
			session.setAttribute("usuario", usuario);
		}
		
		return model;
	}
	
	@RequestMapping(path = "/removerObjeto/{idObjeto}", method = RequestMethod.GET)
	public ModelAndView removerObjeto(@PathVariable("idObjeto") Integer idObjeto){				
		Objeto objeto = objetoService.buscarObjeto(idObjeto);
		Usuario usuario = objeto.getUsuarioDono();
		
		ModelAndView model = new ModelAndView("redirect:/usuario/home/");
		
		if (objeto.getEmprestimo() != null){
			String erro = "O objeto não pode ser removido, pois está emprestado no momento.";
			model.addObject("erroRemocao", erro);
		}
		else{
			usuario.getObjetos().remove(objeto);
			
			usuarioService.salvarUsuario(usuario);
			objetoService.removerObjeto(objeto);
		}
		
		return model;
	}
}
