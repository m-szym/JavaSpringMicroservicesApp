package pg.eti.aui.spacexp.targets;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
public class TargetsApplication {

    public static void main(String[] args) {
        SpringApplication.run(TargetsApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate(@Value("${aui.missions.url}") String baseUrl) {
        return new RestTemplateBuilder().rootUri(baseUrl).build();
    }
}
