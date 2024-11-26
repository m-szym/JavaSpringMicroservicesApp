FROM eclipse-temurin:21-jre
LABEL authors="msz"
LABEL title="aui-missions"

ENV VERSION="0.0.1-SNAPSHOT"

ENV SERVER_PORT=8080

ENV SPRING_DATASOURCE_URL=jdbc:h2:mem:aui-missions-db
ENV SPRING_DATASORUCE_DRIVERCLASSNAME=org.h2.Driver
ENV SPRING_DATASOURCE_USERNAME=admin
ENV SPRING_DATASOURCE_PASSWORD=password

ENV SPRING_JPA_DATABASE_PLATFORM=org.hibernate.dialect.H2Dialect
ENV SPRING_JPA_GENERATE_DDL=true
ENV SPRING_JPA_HIBERNATE_DDL_AUTO=update

ENV SPRING_H2_CONSOLE_ENABLED=true

ENV AUI_TARGETS_URL=http://localhost:8082

EXPOSE 8080

COPY target/missions-${VERSION}.jar opt/missions/missions.jar

CMD ["java", "-jar", "opt/missions/missions.jar"]
