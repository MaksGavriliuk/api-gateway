package org.example.apigateway.config;


import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.discovery.DiscoveryLocatorProperties;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@EnableDiscoveryClient
public class ApiGatewayConfiguration {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder, DiscoveryLocatorProperties properties) {
        return builder.routes()
                .route("academic-performance-service", r -> r
                        .path("/academic-performance/**")
                        .filters(f -> f
                                .addResponseHeader("X-Powered-By", "DanSON Gateway Service")
                        )
                        .uri("lb://academic-performance-service")
                )
//                .route("students-service", r -> r
//                        .path("/students/**")
//                        .filters(f -> f
//                                .addResponseHeader("X-Powered-By", "DanSON Gateway Service")
//                        )
//                        .uri("lb://students")
//                )
                .build();
    }

}