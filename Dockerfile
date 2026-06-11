# syntax=docker/dockerfile:1
FROM maven:3.9-eclipse-temurin-17 AS build

WORKDIR /app
COPY pom.xml ./
COPY src ./src
# Build the app only — do NOT compile or run tests in the production image.
# -DskipTests skips RUNNING tests but still COMPILES them, so a single
# uncompilable test file would fail the whole image build and block deploy.
# -Dmaven.test.skip=true skips both compiling and running tests. Test quality
# is verified separately in Phase 5, not in the deploy build.
RUN mvn -B -Dmaven.test.skip=true package

FROM eclipse-temurin:17-jre-jammy

WORKDIR /app
COPY --from=build /app/target/*.jar /app/app.jar

EXPOSE 8000

CMD ["java", "-jar", "/app/app.jar"]
