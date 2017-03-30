package br.ufc.npi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.ufc.npi.beans.Emprestimo;
import br.ufc.npi.beans.Usuario;
import br.ufc.npi.service.EmprestimoService;
import br.ufc.npi.service.UsuarioService;

@Controller
@RequestMapping(path = "/emprestimo/")
public class EmprestimoController {	
	
	@Autowired
	private UsuarioService usuarioService;	
	
	@Autowired
	private EmprestimoService emprestimoService;
	
	@RequestMapping(path = "/{idUsuario}")
	public ModelAndView realizarEmprestimo(@PathVariable("idUsuario") Integer idUsuario){
		ModelAndView model = new ModelAndView("cadastroEmprestimo");
				
		Usuario usuario = usuarioService.buscarUsuario(idUsuario);
		
		List<Usuario> emprestantes = usuarioService.buscarTodosUsuarios(); 
		
		List<Emprestimo> emprestimos = emprestimoService.buscarEmprestimosPorEmprestador(idUsuario);
		
		model.addObject("emprestimos", emprestimos);
		model.addObject("emprestantes", emprestantes);
		model.addObject("usuario", usuario);		
		
		return model;
	}
	
	@RequestMapping(path = "/cadastrarEmprestimo/{idUsuarioEmprestante}", method = RequestMethod.POST)
	public String cadastrarEmprestimo(@PathVariable("idUsuarioEmprestante") Integer idUsuarioEmprestador, Emprestimo emprestimo){
		Usuario usuarioEmprestador = usuarioService.buscarUsuario(idUsuarioEmprestador);					
			
		emprestimo.setEmprestador(usuarioEmprestador);		
		
		emprestimoService.salvarEmprestimo(emprestimo);
		
		return "redirect:/emprestimo/"+idUsuarioEmprestador;
	}	
}
