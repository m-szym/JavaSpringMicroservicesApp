package pg.eti.aui.spacexp.spacegate;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Collections;


import java.util.Collections;


@SpringBootApplication
@EnableDiscoveryClient
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
                        .uri("ds://targets")
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
                        .uri("ds://missions")
                )
                .build();
    }

    @Bean
    public GlobalFilter discoveryFilter(DiscoveryClient discoveryClient) {
        return new GlobalFilter() {
            @Override
            public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
                URI uri = exchange.getAttribute(ServerWebExchangeUtils.GATEWAY_REQUEST_URL_ATTR);
                if (uri != null && "ds".equals(uri.getScheme())) {
                    System.out.println(uri.getHost());
                    ServiceInstance instance = discoveryClient.getInstances(uri.getHost()).stream()
                            .findFirst()
                            .orElseThrow();
                    System.out.println(instance.getHost());
                    URI newUri = null;
                    try {
                        newUri = new URI(
                                instance.getScheme(),   // Updated scheme
                                uri.getUserInfo(),      // Keep the original user info
                                instance.getHost(),     // Updated host
                                instance.getPort(),     // Updated port
                                uri.getPath(),          // Keep the original path
                                uri.getQuery(),         // Keep the original query
                                uri.getFragment()       // Keep the original fragment
                        );
                    } catch (URISyntaxException e) {
                        throw new RuntimeException(e);
                    }
                    exchange.getAttributes().put(ServerWebExchangeUtils.GATEWAY_REQUEST_URL_ATTR, newUri);
                }
                return chain.filter(exchange);
            }
        };
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
