package br.ufc.npi;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import br.ufc.npi.beans.Usuario;
import br.ufc.npi.service.UsuarioService;

@Component
@ComponentScan(value = {"br.ufc.npi.service", "br.ufc.npi.beans"})
public class CustomAuthenticationSucessHandler implements AuthenticationSuccessHandler {
	
	private static Logger logger = LoggerFactory.getLogger(CustomAuthenticationSucessHandler.class);
	
	@Autowired
	private UsuarioService usuarioService;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, 
											HttpServletResponse response, 
												Authentication auth) 
													throws IOException, ServletException {
		
		HttpSession session = request.getSession();
		String nome = SecurityContextHolder.getContext().getAuthentication().getName();
				
		Usuario usuarioAutenticado = usuarioService.buscarUsuarioPorNome(nome);
		
		if (usuarioAutenticado != null){
			session.setAttribute("usuario", usuarioAutenticado);
			logger.info(usuarioAutenticado.getNome() + " se auteticou!");
		}
		else{
			logger.error("Erro ao autenticar usuario.");
		}
		
		response.sendRedirect("/usuario/home/");
		return;
	}

}
