# ---------- Builder stage (GraalVM) ----------
FROM ghcr.io/graalvm/graalvm-community:25 as builder
ARG GRADLE_USER_HOME=/root/.gradle
WORKDIR /workspace

# Install native-image tool (if not already present)
RUN gu install native-image || true

# Copy gradle wrapper and settings first to leverage caching
COPY gradlew gradlew
COPY gradle gradle
COPY settings.gradle settings.gradle
COPY build.gradle build.gradle
COPY gradle.properties gradle.properties

# Grant execution for wrapper
RUN chmod +x ./gradlew

# Download dependencies (warm cache) - skip tests for speed
RUN ./gradlew --no-daemon dependencies || true

# Copy the rest of the source
COPY . .

# Build native image using Gradle nativeCompile task
# This will produce: build/native/nativeCompile/<executable>
RUN ./gradlew nativeCompile --no-daemon --stacktrace

# ---------- Runtime stage ----------
FROM debian:bookworm-slim
WORKDIR /app

# Copy native executable from builder (name may vary; adjust if needed)
COPY --from=builder /workspace/build/native/nativeCompile/* /app/app

# Ensure executable and minimal runtime deps
RUN chmod +x /app/app

EXPOSE 8080
ENTRYPOINT ["/app/app"]
