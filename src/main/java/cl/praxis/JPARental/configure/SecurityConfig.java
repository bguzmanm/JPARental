package cl.praxis.JPARental.configure;

import cl.praxis.JPARental.model.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

/*  @Bean
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
  }*/

  private final UserDetailsService userDetailsService;

  public SecurityConfig(UserDetailsService userDetailsService) {
    this.userDetailsService = userDetailsService;
  }

  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
    return authenticationConfiguration.getAuthenticationManager();
  }

  @Bean
  public AuthenticationProvider authenticationProvider(UserDetailsServiceImpl userDetailService){
    DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
    provider.setPasswordEncoder(passwordEncoder());
    provider.setUserDetailsService(userDetailService);
    return provider;
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
            /**
             * CSRF (Cross-Site Request Forgery) es un tipo de ataque en el que
             * un atacante engaña a un usuario autenticado para que realice
             * acciones no deseadas en una aplicación web en la que está autenticado.
             * Estos ataques pueden ser muy peligrosos porque permiten al
             * atacante realizar acciones en nombre del usuario sin
             * su conocimiento ni consentimiento.
             */
            //.csrf((csrf) -> csrf.disable()) // <- deshabilita CSRF. Está bien dejarlo así en tiempo de desarrollo,
            // pero no en producción.
            .csrf(csrf -> csrf.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())) // <- así debe
            // quedar en producción.
            .authorizeHttpRequests((authz) -> authz
                    .requestMatchers("/js/**", "/css/**").permitAll()
                    .requestMatchers("/films/**").hasAnyRole("USER", "ADMIN")
                    .requestMatchers("/films/**").hasAnyRole("USER", "ADMIN")
                    .requestMatchers("/actors/**").hasAnyRole("ADMIN")
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
