package com.example.demoSpringSecureApp.SecurityConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

	    @Bean
	    public PasswordEncoder passwordEncoder() {
	    	return new BCryptPasswordEncoder();
	    }
	    
	    // Create two users:    
	     @Bean 
	     public UserDetailsService userDetailService( PasswordEncoder passwordEncoder) {
	    	 
	    	       UserDetails admin = User.builder()
	    	    		                   .username("admin")
	    	    		                   .password(passwordEncoder.encode("admin123"))
	    	    		                   .roles("ADMIN")
	    	    		                   .build();
	    	       
	    	       UserDetails student = User.builder()
	    	    		                   .username("student")
	    	    		                   .password(passwordEncoder.encode("studnet123"))
	    	    		                   .roles("STUDENT")
	    	    		                   .build();
	    	       
	    	       
	    	       return new InMemoryUserDetailsManager(admin, student);
	     }
	     
	     // Spring Security Configuration :
	     
	     @Bean
	     public SecurityFilterChain  securityFilterChain( HttpSecurity http) throws Exception{
	    	     
	    	      http.authorizeHttpRequests(auth ->  auth
	    	      
	    	         //public pages: 
	    	    		   .requestMatchers("/", "/login", "/error").permitAll()
	    	    		   .requestMatchers("/admin/**").hasRole("ADMIN")
	    	    		   .requestMatchers("/student/**").hasAnyRole("ADMIN", "STUDENT")
	    	    		   .anyRequest().authenticated())
	    	      
	    	      .formLogin(form -> form
	    	    		  .loginPage("/login")
	    	    		  .loginProcessingUrl("/login")
	    	    		  .successHandler((request, response, authentication)-> {
	    	    			  
	    	    			  boolean isAdmin = authentication.getAuthorities()
	    	    					                          .stream().anyMatch(authority ->  authority.getAuthority().equals("ROLE_ADMIN"));
	    	    			  
	    	    			  if(isAdmin) {
	    	    				  response.sendRedirect("/admin/admindashboard");
	    	    			  }else {
	    	    				  response.sendRedirect("/student/studentdashboard");
	    	    			  }
	    	    		  })
	    	    		  .failureUrl("/login?error")
	    	    		  .permitAll()
	    	    	)
	    	         .logout(out -> out.logoutSuccessUrl("/login?logout").permitAll());
	    	      
				  return http.build();
	     }
	     
}

