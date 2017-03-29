package br.ufc.npi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.ufc.npi.service.UsuarioService;

@Controller
@RequestMapping(path = "/usuario/")
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@RequestMapping(path = "/cadastrar/")
	public String cadastrarUsuario(){
		return "cadastroUsuario";
	}
}
