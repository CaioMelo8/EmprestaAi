package br.ufc.npi.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import br.ufc.npi.beans.Usuario;
import br.ufc.npi.controller.UsuarioController;
import br.ufc.npi.service.UsuarioService;

@Component
public class CustomAuthenticationSucessHandler implements AuthenticationSuccessHandler {
	private static final String USUARIO_HOME = "/usuario/home/";

	@Autowired
	private UsuarioService usuarioService;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication auth)
			throws IOException, ServletException {

		final HttpSession session = request.getSession();
		final String nome = SecurityContextHolder.getContext().getAuthentication().getName();

		final Usuario usuarioAutenticado = this.usuarioService.buscarUsuarioPorNome(nome);

		if (usuarioAutenticado != null) {
			session.setAttribute(UsuarioController.getCampoUsuario(), usuarioAutenticado);
		}

		response.sendRedirect(USUARIO_HOME);
	}

}
