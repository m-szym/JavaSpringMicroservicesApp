FROM eclipse-temurin:21-jre
LABEL author="msz"
LABEL title="aui-gateway"

ENV VERSION="0.0.1-SNAPSHOT"

ENV SERVER_PORT=8080

ENV SPRING_DATASOURCE_URL=jdbc:h2:mem:simple-rpg-gateway
ENV SPRING_DATASOURCE_DRIVERCLASSNAME=org.h2.Driver
ENV SPRING_DATASOURCE_USERNAME=admin
ENV SPRING_DATASOURCE_PASSWORD=password

ENV SPRING_JPA_DATABASE_PLATFORM=org.hibernate.dialect.H2Dialect
ENV SPRING_JPA_GENERATE_DDL=true
ENV SPRING_JPA_HIBERNATE_DDL_AUTO=update

ENV SPRING_H2_CONSOLE_enabled=true

ENV AUI_GATEWAY_HOST=localhost:8080
ENV AUI_MISSIONS_URL=http://localhost:8081
ENV AUI_TARGETS_URL=http://localhost:8082

ENV EUREKA_CLIENT_SERVICEURL_DEFAULTZONE=http://localhost:8085/eureka
ENV EUREKA_INSTANCE_APPNAME=aui-gateway
ENV EUREKA_INSTANCE_INSTANCEID=8080
ENV EUREKA_INSTANCE_LEASE_RENEWAL_INTERVAL_IN_SECONDS=2
ENV EUREKA_INSTANCE_LEASE_EXPIRATION_DURATION_IN_SECONDS=5

EXPOSE 8080

COPY target/gateway-${VERSION}.jar /opt/gateway/gateway.jar

CMD ["java", "-jar", "/opt/gateway/gateway.jar"]
