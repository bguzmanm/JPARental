package cl.praxis.JPARental.configure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  @Bean
  public UserDetailsService userDetailsService() {
    UserDetails user1 = User.withUsername("user1")
            .password(passwordEncoder().encode("1234"))
            .roles("USER")
            .build();

    UserDetails user2 = User.withUsername("user2")
            .password(passwordEncoder().encode("1234"))
            .roles("USER")
            .build();

    UserDetails admin1 = User.withUsername("admin1")
            .password(passwordEncoder().encode("1234"))
            .roles("ADMIN")
            .build();

    return new InMemoryUserDetailsManager(user1, user2, admin1);
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
            //.csrf((csrf) -> csrf.disable())
            .csrf(csrf -> csrf.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
            .authorizeHttpRequests((authz) -> authz
                    .requestMatchers("/js/**", "/css/**").permitAll()
                    .requestMatchers("/films/**").hasAnyRole("USER", "ADMIN", "GOMA")
                    .requestMatchers("/actors/**").hasAnyRole("USER", "ADMIN")
                    .requestMatchers("/categories/**").hasRole("ADMIN")
                    .anyRequest().authenticated()
            ).formLogin((form) -> form
                    .loginPage("/login")
                    .defaultSuccessUrl("/", true)
                    .failureUrl("/login?error=true")
                    .permitAll()
            )
            .logout((logout) -> logout
                    .logoutSuccessUrl("/login?logout")
                    .invalidateHttpSession(true)
                    .deleteCookies("JSESSIONID")
                    .permitAll());

    return http.build();
  }
}
