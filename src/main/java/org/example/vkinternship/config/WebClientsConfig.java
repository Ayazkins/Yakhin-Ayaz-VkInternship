package org.example.vkinternship.config;

import org.example.vkinternship.webClients.AlbumWebClientImpl;
import org.example.vkinternship.webClients.PostWebClientImpl;
import org.example.vkinternship.webClients.UserWebClientImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientsConfig {

    @Bean
    public WebClient webClient(@Value("${app.url.base}") String link) {
        return WebClient.create(link);
    }

    @Bean
    public AlbumWebClientImpl albumWebClient(WebClient webClient, @Value("${albums.url.base}") String link) {
        return new AlbumWebClientImpl(webClient, link);
    }

    @Bean
    public PostWebClientImpl postWebClient(WebClient webClient, @Value("${posts.url.base}") String link) {
        return new PostWebClientImpl(webClient, link);
    }

    @Bean
    public UserWebClientImpl userWebClient(WebClient webClient, @Value("${users.url.base}") String link) {
        return new UserWebClientImpl(webClient, link);
    }
}
