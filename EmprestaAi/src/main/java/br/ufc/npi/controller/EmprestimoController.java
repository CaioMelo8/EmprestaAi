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
import br.ufc.quixada.npi.model.Email;
import br.ufc.quixada.npi.model.Email.EmailBuilder;
import br.ufc.quixada.npi.service.SendEmailService;

@Controller
@RequestMapping(path = "/emprestimo")
public class EmprestimoController {

	private static final String CAMPO_EMPRESTANTES = "emprestantes";

	private static final String EMPRESTIMO_REDIRECT = "redirect:/emprestimo/";

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private EmprestimoService emprestimoService;

	@Autowired
	private ObjetoService objetoService;

	@Autowired
	private SendEmailService emailService;

	@RequestMapping(path = { "", "/" }, method = RequestMethod.GET)
	public ModelAndView realizarEmprestimo(HttpSession session) {
		this.objetoService = new ObjetoService();
		final ModelAndView model = new ModelAndView("cadastroEmprestimo");
		final Usuario usuarioAutenticado = (Usuario) session.getAttribute(UsuarioController.getCampoUsuario());

		final Usuario usuario = this.usuarioService.buscarUsuario(usuarioAutenticado.getId());
		session.setAttribute(UsuarioController.getCampoUsuario(), usuario);

		final List<Usuario> emprestantes = this.usuarioService.buscarTodosUsuariosExceto(usuario.getId());

		model.addObject(UsuarioController.getCampoUsuario(), usuario);
		model.addObject(CAMPO_EMPRESTANTES, emprestantes);

		return model;
	}

	@RequestMapping(path = "/cadastrarEmprestimo", method = RequestMethod.POST)
	public String cadastrarEmprestimo(Emprestimo emprestimo, HttpSession session) {
		final Usuario usuarioAutenticado = (Usuario) session.getAttribute(UsuarioController.getCampoUsuario());

		final Usuario usuarioEmprestador = this.usuarioService.buscarUsuario(usuarioAutenticado.getId());

		final Objeto objeto = emprestimo.getObjeto();
		objeto.setEmprestimo(emprestimo);

		emprestimo.setEmprestador(usuarioEmprestador);

		this.emprestimoService.salvarEmprestimo(emprestimo);
		this.objetoService.salvarObjeto(objeto);

		this.enviarEmailCadastroEmprestimo(emprestimo);

		session.setAttribute(UsuarioController.getCampoUsuario(), usuarioEmprestador);

		return EMPRESTIMO_REDIRECT;
	}

	@RequestMapping(path = "/confirmarDevolucao/{idEmprestimo}", method = RequestMethod.GET)
	public String confirmarDevolucao(@PathVariable("idEmprestimo") Integer idEmprestimo) {
		final Emprestimo emprestimo = this.emprestimoService.buscarEmprestimo(idEmprestimo);

		final Objeto objeto = emprestimo.getObjeto();
		objeto.setEmprestimo(null);

		this.objetoService.salvarObjeto(objeto);
		this.emprestimoService.removerEmprestimo(emprestimo);

		this.enviarEmailConfirmacaoDevolucao(emprestimo);

		return EMPRESTIMO_REDIRECT;
	}

	public void enviarEmailCadastroEmprestimo(Emprestimo emprestimo) {
		final Usuario remetente = emprestimo.getEmprestador();
		final Usuario destinatario = emprestimo.getEmprestante();
		final Objeto objeto = emprestimo.getObjeto();

		final String corpoEmail =  "Olá, " + destinatario.getNome() + "\n\n"
				+ "Um objeto foi emprestado para você!\n\n"
				+ "Detalhes\n\n"
				+ "Nome do usuário: " + remetente.getNome() + "\n"
				+ "Email: " + remetente.getEmail() + "\n"
				+ "Telefone: " + remetente.getTelefone() + "\n\n"
				+ "Objeto\n" + objeto.getNome() + "\n"
				+ "Descrição: " + objeto.getDescricao() + "\n\n"
				+ "Data de empréstimo: " + emprestimo.getDataEmprestimo() + "\n"
				+ "Data de devolução: " + emprestimo.getDataDevolucao() + "\n\n"
				+ "Email enviado automatiamente. Não responda a este email.\n\n" + "EmprestaAi";

		final EmailBuilder emailBuilder = 
				new EmailBuilder(remetente.getNome(), remetente.getEmail(),
						"EmprestaAi - Novo empréstimo cadastrado", destinatario.getEmail(), corpoEmail);
		
		final Email email = new Email(emailBuilder);

		this.emailService.sendEmail(email);
	}

	public void enviarEmailConfirmacaoDevolucao(Emprestimo emprestimo) {
		final Usuario remetente = emprestimo.getEmprestador();
		final Usuario destinatario = emprestimo.getEmprestante();
		final Objeto objeto = emprestimo.getObjeto();

		final String corpoEmail = "Olá, " + destinatario.getNome() + "\n\n"
				+ "O objeto que lhe foi emprestado foi devolvido com sucesso!\n\n"
				+ "Detalhes\n\n"
				+ "Nome do usuário: " + remetente.getNome() + "\n"
				+ "Objeto\n"
				+ objeto.getNome() + "\n"
				+ "Descrição: " + objeto.getDescricao() + "\n\n"
				+ "Email enviado automaticamente. Não responda a este email.\n\n"
				+ "Atenciosamente,\nEmprestaAi";

		final EmailBuilder emailBuilder = 
				new EmailBuilder(remetente.getNome(), remetente.getEmail(), 
						"EmprestaAi - Devolução confirmada com sucesso", destinatario.getEmail(), corpoEmail);
		
		final Email email = new Email(emailBuilder);

		this.emailService.sendEmail(email);
	}
}
