package api.configs.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**") // Defina o padrão de URL para aplicar a configuração de CORS
                .allowedOrigins("https://localhost:4200") // Adicione aqui as origens permitidas
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Métodos permitidos
                .allowedHeaders("Content-Type", "Authorization") // Cabeçalhos permitidos
                .allowCredentials(true); // Permitir o uso de credenciais (cookies, cabeçalhos de autorização, etc)
    }
}