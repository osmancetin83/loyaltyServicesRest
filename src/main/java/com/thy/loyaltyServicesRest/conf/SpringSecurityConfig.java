/**
 * 
 */
package com.thy.loyaltyServicesRest.conf;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author o_cetin3
 *
 */
@Configuration
//@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	// Create 2 users for demo
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.inMemoryAuthentication()
                .withUser("user").password("{noop}restUser").roles("USER")
                .and()
                .withUser("admin").password("{noop}restAdmin").roles("USER", "ADMIN");

    }

    // Secure the endpoins with HTTP Basic authentication
    @Override
    protected void configure(HttpSecurity http) throws Exception {

    	//HTTP Basic authentication
    	http.httpBasic()
            .and()
            .authorizeRequests()
            .antMatchers(HttpMethod.GET, "/system/**").hasRole("USER")
            .antMatchers(HttpMethod.POST, "/system").hasRole("ADMIN")
            .antMatchers(HttpMethod.PUT, "/system/**").hasRole("ADMIN")
            .antMatchers(HttpMethod.PATCH, "/system/**").hasRole("ADMIN")
            .antMatchers(HttpMethod.DELETE, "/system/**").hasRole("ADMIN")
            .and()
            .csrf().disable()
            .formLogin().disable();
    }
    
//	@Autowired
//	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//		auth.inMemoryAuthentication().withUser("userId").password("passwd").authorities("ROLE_USER");
//	}


}
