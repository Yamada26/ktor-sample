# Multi-stage Dockerfile for building and running the Ktor app
# Builder: use official Gradle image to run the Gradle wrapper and produce the distribution
FROM gradle:8.4-jdk17 AS builder
WORKDIR /home/gradle/project

# Copy project files (rely on .dockerignore to keep build context small)
COPY . .

# Build the install distribution (skip tests to speed up builds in CI by default)
RUN ./gradlew installDist -x test --no-daemon

# Runtime: lightweight JRE image
FROM eclipse-temurin:17-jre-jammy

# Optional runtime JVM tuning
ENV JAVA_OPTS="-Xms256m -Xmx512m"

WORKDIR /app

# Copy the install distribution produced by the builder
COPY --from=builder /home/gradle/project/build/install/ktor-sample /app

# Expose default Ktor port (verify with your config/application.yaml)
EXPOSE 8080

# Use the generated start script from the distribution
ENTRYPOINT ["bin/ktor-sample"]
