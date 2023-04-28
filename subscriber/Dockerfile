FROM  --platform=$BUILDPLATFORM maven:3.8.5-eclipse-temurin-17 AS cross
WORKDIR /subscribers_source
COPY pom.xml /subscribers_source/pom.xml
RUN mvn dependency:go-offline

COPY src /subscribers_source/src
RUN mvn install

CMD ["mvn", "spring-boot:run"]

FROM cross as prod-docker
RUN mkdir -p target/dependency
WORKDIR /subscribers_source/target/dependency
RUN jar -xf ../*.jar

FROM eclipse-temurin:17-jre-focal

EXPOSE 8081
VOLUME /tmp
ARG DEPENDENCY=/subscribers_source/target/dependency
COPY --from=prod-docker ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=prod-docker ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=prod-docker ${DEPENDENCY}/BOOT-INF/classes /app
ENTRYPOINT ["java","-cp","app:app/lib/*","com.obcompany.subscribers.Application"]
