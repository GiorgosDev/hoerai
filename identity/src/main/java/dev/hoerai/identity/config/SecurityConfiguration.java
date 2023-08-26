package dev.hoerai.identity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Objects;

import static dev.hoerai.identity.Constants.SENSOR_ROLE;
import static org.springframework.security.config.Customizer.withDefaults;

@EnableWebSecurity
@Configuration
@PropertySource("classpath:application.properties")
public class SecurityConfiguration {

    private static final String SENSOR_USER = "rest.sensor.user";
    private static final String SENSOR_PASSWORD = "rest.sensor.password";
    private final Environment env;

    public SecurityConfiguration(Environment env) {
        this.env = env;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        final UserDetails sensorAppUser = createUser(SENSOR_USER, SENSOR_PASSWORD, SENSOR_ROLE);
        return new InMemoryUserDetailsManager(sensorAppUser);
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests(authorizeRequests -> authorizeRequests.anyRequest()
                        .authenticated())
                .httpBasic(withDefaults())
                .formLogin(withDefaults())
                .csrf(AbstractHttpConfigurer::disable);
        return http.build();
    }


    private UserDetails createUser(String user, String pass, String role) {
        return User.withUsername(Objects.requireNonNull(getEnv().getProperty(user)))
                   .password(passwordEncoder().encode(Objects.requireNonNull(getEnv().getProperty(pass))))
                   .roles(role)
                   .build();
    }

    protected Environment getEnv() {
        return env;
    }
}
