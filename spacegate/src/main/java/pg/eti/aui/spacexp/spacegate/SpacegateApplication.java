package pg.eti.aui.spacexp.spacegate;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;


import java.util.Collections;


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

    @Bean
    public CorsWebFilter corsWebFilter() {

        final CorsConfiguration corsConfig = new CorsConfiguration();
        corsConfig.setAllowedOrigins(Collections.singletonList("*"));
        corsConfig.setMaxAge(3600L);
        corsConfig.setAllowedMethods(Arrays.asList("GET", "POST", "DELETE", "PUT"));
        corsConfig.addAllowedHeader("*");

        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfig);

        return new CorsWebFilter(source);
    }

}
