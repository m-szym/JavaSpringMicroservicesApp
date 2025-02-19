FROM eclipse-temurin:21-jre
LABEL authors="msz"
LABEL title="aui-discovery"

ENV VERSION="0.0.1-SNAPSHOT"

ENV SERVER_PORT=8085

ENV EUREKA_INSTANCE_HOSTNAME=localhost

ENV EUREKA_CLIENT_FETCH_REGISTRY=false
ENV EUREKA_CLIENT_REGISTER_WITH_EUREKA=false
ENV EUREKA_CLIENT_SERVICEURL_DEFAULTZONE=http://localhost:8085/eureka

ENV EUREKA_SERVER_ENABLE_SELF_PRESERVATION=false
ENV EUREKA_SERVER_EVICTION_INTERVAL_TIMER_IN_MS=2000

EXPOSE 8761

COPY target/eureka-discovery-${VERSION}.jar /opt/eureka-discovery/eureka-discovery.jar

CMD ["java", "-jar", "/opt/simple-rpg-eureka/simple-rpg-eureka.jar"]
