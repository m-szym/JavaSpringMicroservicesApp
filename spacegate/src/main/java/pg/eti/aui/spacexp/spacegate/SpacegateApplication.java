package pg.eti.aui.spacexp.spacegate;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.cloud.gateway.route.RouteLocator;



@SpringBootApplication
public class SpacegateApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpacegateApplication.class, args);
    }

    @Bean
    public RouteLocator routeLocator(
            RouteLocatorBuilder builder,
            @Value("${aui.missions.url}") String missionsUrl,
            @Value("${aui.targets.url}") String targetsUrl,
            @Value("${aui.gateway.host}") String gatewayHostUrl
    ) {
        return builder
                .routes()
                .route("targets", route -> route
                        .host(gatewayHostUrl)
                        .and()
                        .path(
                                "/api/targets/{uuid}",
                                "/api/targets"
                        )
                        .uri(targetsUrl)
                )
                .route("characters", route -> route
                        .host(gatewayHostUrl)
                        .and()
                        .path(
                                "/api/missions/{uuid}",
                                "/api/missions",
                                "/api/missions/**",
                                "/api/targets/{uuid}/characters",
                                "/api/targets/{uuid}/characters/**"
                        )
                        .uri(missionsUrl)
                )
                .build();

    }
}
