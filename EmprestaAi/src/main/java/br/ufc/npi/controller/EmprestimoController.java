package br.ufc.npi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.ufc.npi.beans.Emprestimo;
import br.ufc.npi.beans.Objeto;
import br.ufc.npi.beans.Usuario;
import br.ufc.npi.service.EmprestimoService;
import br.ufc.npi.service.ObjetoService;
import br.ufc.npi.service.UsuarioService;

@Controller
@RequestMapping(path = "/emprestimo/")
public class EmprestimoController {	
	
	@Autowired
	private UsuarioService usuarioService;	
	
	@Autowired
	private EmprestimoService emprestimoService;
	
	@Autowired
	private ObjetoService objetoService;
	
	@RequestMapping(path = "/{idUsuario}")
	public ModelAndView realizarEmprestimo(@PathVariable("idUsuario") Integer idUsuario){
		ModelAndView model = new ModelAndView("cadastroEmprestimo");
				
		Usuario usuario = usuarioService.buscarUsuario(idUsuario);
		
		List<Usuario> emprestantes = usuarioService.buscarTodosUsuariosExceto(idUsuario);		
		List<Emprestimo> emprestimos = emprestimoService.buscarEmprestimosPorEmprestador(usuario);
		
		model.addObject("usuario", usuario);
		model.addObject("emprestantes", emprestantes);
		model.addObject("emprestimos", emprestimos);
		
		return model;
	}
	
	@RequestMapping(path = "/cadastrarEmprestimo/{idUsuarioEmprestante}", method = RequestMethod.POST)
	public String cadastrarEmprestimo(@PathVariable("idUsuarioEmprestante") Integer idUsuarioEmprestador, Emprestimo emprestimo){
		Usuario usuarioEmprestador = usuarioService.buscarUsuario(idUsuarioEmprestador);
		
		Objeto objeto = emprestimo.getObjeto();
		objeto.setEmprestimo(emprestimo);
			
		emprestimo.setEmprestador(usuarioEmprestador);
		
		emprestimoService.salvarEmprestimo(emprestimo);
		objetoService.salvarObjeto(objeto);
		
		return "redirect:/emprestimo/"+idUsuarioEmprestador;
	}
	
	@RequestMapping(path = "/confirmarDevolucao/{idEmprestimo}")
	public String confirmarDevolucao(@PathVariable("idEmprestimo") Integer idEmprestimo){
		
		Emprestimo emprestimo = emprestimoService.buscarEmprestimo(idEmprestimo);
		Usuario emprestador = emprestimo.getEmprestador();
		Objeto objeto = emprestimo.getObjeto();	
		objeto.setEmprestimo(null);
		
		objetoService.salvarObjeto(objeto);
		emprestimoService.removerEmprestimo(emprestimo);
		
		return "redirect:/emprestimo/"+emprestador.getId();
	}
}
