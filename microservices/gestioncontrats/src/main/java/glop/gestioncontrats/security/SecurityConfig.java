package glop.gestioncontrats.security;
// package glop.gestioncontrats;

// import java.beans.Customizer;
// import java.util.Arrays;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.web.SecurityFilterChain;
// import org.springframework.web.cors.CorsConfiguration;
// import org.springframework.web.cors.CorsConfigurationSource;
// import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

// @Configuration
// @EnableWebSecurity
// public class SecurityConfig {

//     @Bean
//     public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//         http
//         .csrf((csrf) -> csrf.disable())
//         .cors(cors -> cors.configurationSource(corsConfigurationSource()))
//         .authorizeHttpRequests((authz) -> authz

//                     .requestMatchers("/contract/**").hasRole("GESTIONNAIRE")
//                     .requestMatchers("/contract/getAllByClient/").hasRole("USER")
//                     .anyRequest().authenticated()
//             )
//         .formLogin()
//             .permitAll()
//         .and()
//         .logout()
//             .permitAll();
//         return http.build();
//     }

//     private CorsConfigurationSource corsConfigurationSource() {
//         CorsConfiguration configuration = new CorsConfiguration();
//         configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
//         configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
//         UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//         source.registerCorsConfiguration("/**", configuration);
//         return source;
//     }


// }

