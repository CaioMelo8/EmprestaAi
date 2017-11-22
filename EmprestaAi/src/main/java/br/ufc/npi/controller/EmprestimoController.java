package br.ufc.npi.controller;

import br.ufc.npi.beans.Emprestimo;
import br.ufc.npi.beans.Objeto;
import br.ufc.npi.beans.Usuario;
import br.ufc.npi.service.EmprestimoService;
import br.ufc.npi.service.ObjetoService;
import br.ufc.npi.service.UsuarioService;
import br.ufc.quixada.npi.model.Email;
import br.ufc.quixada.npi.model.Email.EmailBuilder;
import br.ufc.quixada.npi.service.SendEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping(path = "/emprestimo")
public class EmprestimoController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private EmprestimoService emprestimoService;

    @Autowired
    private ObjetoService objetoService;

    @Autowired
    private SendEmailService emailService;

    @RequestMapping(path = {"", "/"}, method = RequestMethod.GET)
    public ModelAndView realizarEmprestimo(HttpSession session) {
        ModelAndView model = new ModelAndView("cadastroEmprestimo");
        Usuario usuarioAutenticado = (Usuario) session.getAttribute("usuario");

        Usuario usuario = usuarioService.buscarUsuario(usuarioAutenticado.getId());
        session.setAttribute("usuario", usuario);

        List<Usuario> emprestantes = usuarioService.buscarTodosUsuariosExceto(usuario.getId());

        model.addObject("usuario", usuario);
        model.addObject("emprestantes", emprestantes);

        return model;
    }

    @RequestMapping(path = "/cadastrarEmprestimo", method = RequestMethod.POST)
    public String cadastrarEmprestimo(Emprestimo emprestimo, HttpSession session) {
        Usuario usuarioAutenticado = (Usuario) session.getAttribute("usuario");

        Usuario usuarioEmprestador = usuarioService.buscarUsuario(usuarioAutenticado.getId());

        Objeto objeto = emprestimo.getObjeto();
        objeto.setEmprestimo(emprestimo);

        emprestimo.setEmprestador(usuarioEmprestador);

        emprestimoService.salvarEmprestimo(emprestimo);
        objetoService.salvarObjeto(objeto);

        this.enviarEmailCadastroEmprestimo(emprestimo);

        session.setAttribute("usuario", usuarioEmprestador);

        return "redirect:/emprestimo/";
    }

    @RequestMapping(path = "/confirmarDevolucao/{idEmprestimo}", method = RequestMethod.GET)
    public String confirmarDevolucao(@PathVariable("idEmprestimo") Integer idEmprestimo) {

        Emprestimo emprestimo = emprestimoService.buscarEmprestimo(idEmprestimo);

        Objeto objeto = emprestimo.getObjeto();
        objeto.setEmprestimo(null);

        objetoService.salvarObjeto(objeto);
        emprestimoService.removerEmprestimo(emprestimo);

        this.enviarEmailConfirmacaoDevolucao(emprestimo);

        return "redirect:/emprestimo/";
    }

    public void enviarEmailCadastroEmprestimo(Emprestimo emprestimo) {
        Usuario remetente = emprestimo.getEmprestador();
        Usuario destinatario = emprestimo.getEmprestante();
        Objeto objeto = emprestimo.getObjeto();

        String corpoEmail = "Olá, " + destinatario.getNome() + "\n\n"
                + "Um objeto foi emprestado para você!\n\n"
                + "Detalhes\n\n"
                + "Nome do usuário: " + remetente.getNome() + "\n"
                + "Email: " + remetente.getEmail() + "\n"
                + "Telefone: " + remetente.getTelefone() + "\n\n"
                + "Objeto\n"
                + objeto.getNome() + "\n"
                + "Descrição: " + objeto.getDescricao() + "\n\n"
                + "Data de empréstimo: " + emprestimo.getDataEmprestimo() + "\n"
                + "Data de devolução: " + emprestimo.getDataDevolucao() + "\n\n"
                + "Email enviado automatiamente. Não responda a este email.\n\n"
                + "EmprestaAi";

        EmailBuilder emailBuilder = new EmailBuilder(remetente.getNome(), remetente.getEmail(), "EmprestaAi - Novo empréstimo cadastrado", destinatario.getEmail(), corpoEmail);
        Email email = new Email(emailBuilder);

        emailService.sendEmail(email);
    }

    public void enviarEmailConfirmacaoDevolucao(Emprestimo emprestimo) {
        Usuario remetente = emprestimo.getEmprestador();
        Usuario destinatario = emprestimo.getEmprestante();
        Objeto objeto = emprestimo.getObjeto();

        String corpoEmail = "Olá, " + destinatario.getNome() + "\n\n"
                + "O objeto que lhe foi emprestado foi devolvido com sucesso!\n\n"
                + "Detalhes\n\n"
                + "Nome do usuário: " + remetente.getNome() + "\n"
                + "Objeto\n"
                + objeto.getNome() + "\n"
                + "Descrição: " + objeto.getDescricao() + "\n\n"
                + "Email enviado automaticamente. Não responda a este email.\n\n"
                + "Atenciosamente,\nEmprestaAi";

        EmailBuilder emailBuilder = new EmailBuilder(remetente.getNome(), remetente.getEmail(), "EmprestaAi - Devolução confirmada com sucesso", destinatario.getEmail(), corpoEmail);
        Email email = new Email(emailBuilder);

        emailService.sendEmail(email);
    }
}
