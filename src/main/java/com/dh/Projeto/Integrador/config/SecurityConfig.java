package com.dh.Projeto.Integrador.config;

import com.dh.Projeto.Integrador.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;



@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    //escreve configure e aceita a primeira opção
    //dentro dele vou montar as minhas regras
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable() // estou colocando isso pois os navegadores automaticamente abrem para
                //fazer login e nesse caso estou desabilitando pois quero usar o formulario que o spring boot gera
                .authorizeRequests()
                .antMatchers("/**").permitAll()
                .antMatchers("/user").hasRole("USUARIO")
                .anyRequest()
                .authenticated().and();
                //.formLogin();

    }


    //esse configure será um consultor de gerenciamento de autenticação
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider()); //vou indicar de onde vem a autenticação
    }


    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(bCryptPasswordEncoder);
        provider.setUserDetailsService(usuarioService);
        return provider;
    }


}



