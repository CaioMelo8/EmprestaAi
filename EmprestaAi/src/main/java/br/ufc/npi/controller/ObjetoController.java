package br.ufc.npi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.ufc.npi.beans.Objeto;
import br.ufc.npi.beans.Usuario;
import br.ufc.npi.service.ObjetoService;
import br.ufc.npi.service.UsuarioService;

@Controller
@RequestMapping(path = "/objeto/")
public class ObjetoController {
	
	@Autowired
	private ObjetoService objetoService;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@RequestMapping(path = "/cadastrarObjeto/{idUsuario}")
	public ModelAndView cadastrarObjeto(@PathVariable("idUsuario") Integer idUsuario, 
									@RequestParam("nomeObjeto") String nomeObjeto,
										@RequestParam(name = "descricaoObjeto", required = false) String descricaoObjeto){
		
		ModelAndView model = new ModelAndView("redirect:/usuario/perfil/"+idUsuario);
		
		Usuario usuario = usuarioService.buscarUsuario(idUsuario);
		
		if (usuario.getObjetos().size() == 10){
			String erro = "O usuário já atingiu o limite de objetos.";
			model.addObject("erro", erro);
		}			
		else{
			Objeto objeto = new Objeto();
			objeto.setNome(nomeObjeto);
			objeto.setDescricao(descricaoObjeto);
			objeto.setUsuarioDono(usuario);
			
			usuario.getObjetos().add(objeto);
			
			objetoService.salvarObjeto(objeto);
			usuarioService.salvarUsuario(usuario);
		}		
		
		return model;
	}
}
