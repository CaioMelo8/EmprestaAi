package br.ufc.npi;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import br.ufc.quixada.npi.service.SendEmailService;

@SpringBootApplication
public class EmprestaAiApplication {
	
	@Autowired
	JavaMailSenderImpl jmsi;
	
	@Bean
	public JavaMailSenderImpl mailSender(){
		JavaMailSenderImpl jmsi = new JavaMailSenderImpl();			
		
		Properties prop = new Properties();
		prop.setProperty("mail.transport.protocol", "smtp");
		prop.setProperty("mail.smtp.auth", "true");
		prop.setProperty("mail.smtp.starttls.enable", "true");
		prop.setProperty("mail.smtp.ssl.trust", "smtp-mail.outlook.com");
		prop.setProperty("mail.debug", "true");
		
		jmsi.setDefaultEncoding("UTF-8");
		jmsi.setHost("smtp-mail.outlook.com");
		jmsi.setPort(587);
		jmsi.setUsername("caiomelo100@hotmail.com");
		jmsi.setPassword(null);
		jmsi.setJavaMailProperties(prop);
		
		return jmsi;
	}
	
	@Bean
	public SendEmailService sendEmailService(){
		SendEmailService emailService = new SendEmailService(jmsi);
		return emailService;
	}

	public static void main(String[] args) {
		SpringApplication.run(EmprestaAiApplication.class, args);
	}
}
