package com.leap92.bootcamp.finalProject.configuration;

import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.leap92.bootcamp.finalProject.model.User;
import com.leap92.bootcamp.finalProject.repositories.UserRepository;

@Configuration
@EnableWebMvcSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserRepository userRepository;
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	http
				.authorizeRequests().antMatchers("/register").permitAll();
    	http
                .authorizeRequests().anyRequest().authenticated();
        http
        		.csrf().disable()
                .formLogin().failureUrl("/login?error")
                .defaultSuccessUrl("/")
                .loginPage("/login")
                .permitAll()
                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login")
                .permitAll();
    }
 
    /*Extremely basic (and bad) implementation that takes the users in the DB and puts them in the AuthenticationManagerBuilder
     * "memory". The problem is that it does it in compilation time; couldn't find a simple way to add users
     * created in runtime (via the registration form) here
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    	/*
    	 * Adds a basic user (user-password) to login the first time
    	 * The server needs to be reset to add the registered users
    	 */
    	User user = new User("user","password");
    	userRepository.save(user);
    	auth.inMemoryAuthentication().withUser(user.getUsername()).password(user.getPassword());
    	
    	
    	Collection<User> userList = userRepository.findAll();
    	for (User u : userList) {
            auth.inMemoryAuthentication().withUser(u.getUsername()).password(u.getPassword()).roles("USER");
    	}
    }
    

}
