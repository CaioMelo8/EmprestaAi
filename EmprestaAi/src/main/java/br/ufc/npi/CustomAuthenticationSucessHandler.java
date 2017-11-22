package br.ufc.npi;

import br.ufc.npi.beans.Usuario;
import br.ufc.npi.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
@ComponentScan(value = {"br.ufc.npi.service", "br.ufc.npi.beans"})
public class CustomAuthenticationSucessHandler implements AuthenticationSuccessHandler {

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

        if (usuarioAutenticado != null) {
            session.setAttribute("usuario", usuarioAutenticado);
        }

        response.sendRedirect("/usuario/home/");
    }

}
