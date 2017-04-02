package br.ufc.npi;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private DataSource dataSource;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {		
		http
			.authorizeRequests()				
				.antMatchers("/webjars/**").permitAll()
				.anyRequest().authenticated()
				.and()
			.formLogin()
				.loginPage("/login").failureUrl("/login?error").defaultSuccessUrl("/", true)
				.usernameParameter("username")
				.passwordParameter("password")
				.permitAll()
				.and()
			.logout()				
				.logoutSuccessUrl("/login?logout");
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
		auth
			.inMemoryAuthentication()
				.withUser("user").password("123").roles("USER");
			
		auth.jdbcAuthentication().dataSource(dataSource)
			.usersByUsernameQuery("select nome, senha, ativo from usuario where nome=?")
			.authoritiesByUsernameQuery("select nome, role from usuario where nome=?");		
	}
}
