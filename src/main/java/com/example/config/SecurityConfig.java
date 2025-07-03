package com.example.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthFilter;

    // Internamente, el AuthenticationManager usa el UserDetailsService que Spring haya detectado como @Service en tu aplicación.
    /*
     Spring Security internamente usa el UserDetailsService (en tu caso CustomUserService) para cargar el usuario desde la base de datos, usando el username (tu email).
    */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    // encripta la contraseña ingresada para ser comparada con la de la database
    /*
     Luego, Spring usa el PasswordEncoder (el BCryptPasswordEncoder) para comparar la contraseña ingresada (request.password()) con la contraseña encriptada que viene del usuario de la base de datos que fue envolvida en CustomUser.
     */
    @Bean
    BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(AbstractHttpConfigurer::disable)
            .cors(Customizer.withDefaults()) // HABILITAR y CREAMOS EN LINK DE CONFIGURACION , QUE SERIA LO DE LA LINEA 70 PARA ABAJO: UrlBasedCorsConfigurationSource
            .authorizeHttpRequests(auth -> auth
                    .requestMatchers("/api/v1/auth/**").permitAll() // Ingresar sin token
                    .requestMatchers("/api/imagen/**").permitAll() //Para que la img se guarde en uploads/img
                    .requestMatchers("/img/**").permitAll()//Del WebConfig que mapee que sea desde img      
                    .requestMatchers("/api/usuario/privado").hasAuthority("ADMIN")
                    .requestMatchers("/api/usuario/publico").hasAuthority("RECEP")
                    .requestMatchers("/api/usuario/**").hasAuthority("RECEP")

                    .requestMatchers("/api/profesores/**").hasAuthority("ADMIN") // o "ADMIN"
                    .requestMatchers("/api/carrera/**").hasAuthority("ADMIN")

                    
                    .requestMatchers("/api/EstudianteUniversidad/**").permitAll()//ESto por el momento estara como permitAll , para probar
                    .anyRequest().authenticated()) // cualquier otra ruta no mencionada necesita que el usuario sea autenticado pero sin importar si es admin o client
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    UrlBasedCorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList( "http://localhost:4200"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("*")); // PARA LOS ENCABEZADOS , EL * PERMITE TODO LOS ENCABEZADOS
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

}
