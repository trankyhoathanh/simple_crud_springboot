FROM  --platform=$BUILDPLATFORM maven:3.8.5-eclipse-temurin-17 AS cross
WORKDIR /backend_source
COPY pom.xml /backend_source/pom.xml
RUN mvn dependency:go-offline

COPY src /backend_source/src
RUN mvn install

CMD ["mvn", "spring-boot:run"]

FROM cross as prod-docker
RUN mkdir -p target/dependency
WORKDIR /backend_source/target/dependency
RUN jar -xf ../*.jar

FROM eclipse-temurin:17-jre-focal

EXPOSE 8080
VOLUME /tmp
ARG DEPENDENCY=/backend_source/target/dependency
COPY --from=prod-docker ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=prod-docker ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=prod-docker ${DEPENDENCY}/BOOT-INF/classes /app
ENTRYPOINT ["java","-cp","app:app/lib/*","com.obcompany.simplecrud.Application"]
