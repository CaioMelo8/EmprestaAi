package br.ufc.npi.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

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
@RequestMapping(path = "/emprestimo")
public class EmprestimoController {	
	
	@Autowired
	private UsuarioService usuarioService;	
	
	@Autowired
	private EmprestimoService emprestimoService;
	
	@Autowired
	private ObjetoService objetoService;
	
	@RequestMapping(path = {"", "/"}, method = RequestMethod.GET)
	public ModelAndView realizarEmprestimo(HttpSession session){
		ModelAndView model = new ModelAndView("cadastroEmprestimo");
		Usuario usuarioAutenticado = (Usuario) session.getAttribute("usuario");
		
		Usuario usuario = usuarioService.buscarUsuario(usuarioAutenticado.getId());
		session.setAttribute("usuario", usuario);
		
		List<Usuario> emprestantes = usuarioService.buscarTodosUsuariosExceto(usuario.getId());	
		List<Emprestimo> emprestimos = emprestimoService.buscarEmprestimosPorEmprestador(usuario);
		
		model.addObject("usuario", usuario);
		model.addObject("emprestantes", emprestantes);
		model.addObject("emprestimos", emprestimos);
		
		return model;
	}
	
	@RequestMapping(path = "/cadastrarEmprestimo", method = RequestMethod.POST)
	public String cadastrarEmprestimo(Emprestimo emprestimo, HttpSession session){
		Usuario usuarioAutenticado = (Usuario) session.getAttribute("usuario");
		
		Usuario usuarioEmprestador = usuarioService.buscarUsuario(usuarioAutenticado.getId());
		
		Objeto objeto = emprestimo.getObjeto();
		objeto.setEmprestimo(emprestimo);
			
		emprestimo.setEmprestador(usuarioEmprestador);
		
		emprestimoService.salvarEmprestimo(emprestimo);
		objetoService.salvarObjeto(objeto);
		
		session.setAttribute("usuario", usuarioEmprestador);
		
		return "redirect:/emprestimo/";
	}
	
	@RequestMapping(path = "/confirmarDevolucao/{idEmprestimo}", method = RequestMethod.GET)
	public String confirmarDevolucao(@PathVariable("idEmprestimo") Integer idEmprestimo){
		
		Emprestimo emprestimo = emprestimoService.buscarEmprestimo(idEmprestimo);
		
		Objeto objeto = emprestimo.getObjeto();	
		objeto.setEmprestimo(null);
		
		objetoService.salvarObjeto(objeto);
		emprestimoService.removerEmprestimo(emprestimo);
		
		return "redirect:/emprestimo/";
	}
}
