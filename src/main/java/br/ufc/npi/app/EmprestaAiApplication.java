package br.ufc.npi.app;

import java.util.Properties;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import br.ufc.quixada.npi.service.SendEmailService;

@SpringBootApplication
public class EmprestaAiApplication {

	private static final String MAIL_DEBUG = "mail.debug";
	private static final String MAIL_SMTP_SSL_TRUST = "mail.smtp.ssl.trust";
	private static final String MAIL_SMTP_STARTTLS_ENABLE = "mail.smtp.starttls.enable";
	private static final String MAIL_SMTP_AUTH = "mail.smtp.auth";
	private static final String MAIL_TRANSPORT_PROTOCOL = "mail.transport.protocol";

	private JavaMailSenderImpl jmsi;

	public static void main(String[] args) {
		SpringApplication.run(EmprestaAiApplication.class, args);
	}

	@Bean
	public JavaMailSenderImpl mailSender() {
		final int port = 587;
		final String username = "caiomelo100@hotmail.com";
		final String smtpMailHost = "smtp-mail.outlook.com";
		final String encoding = "UTF-8";
		final String mailProtocol = "smtp";

		final Properties prop = new Properties();
		prop.setProperty(MAIL_TRANSPORT_PROTOCOL, mailProtocol);
		prop.setProperty(MAIL_SMTP_AUTH, "true");
		prop.setProperty(MAIL_SMTP_STARTTLS_ENABLE, "true");
		prop.setProperty(MAIL_SMTP_SSL_TRUST, smtpMailHost);
		prop.setProperty(MAIL_DEBUG, "true");

		this.jmsi = new JavaMailSenderImpl();
		this.jmsi.setDefaultEncoding(encoding);
		this.jmsi.setHost(smtpMailHost);
		this.jmsi.setPort(port);
		this.jmsi.setUsername(username);
		this.jmsi.setPassword(null);
		this.jmsi.setJavaMailProperties(prop);

		return this.jmsi;
	}

	@Bean
	public SendEmailService sendEmailService() {
		return new SendEmailService(this.jmsi);
	}
}
