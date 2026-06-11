# Random API

Simple Spring Boot REST API that exposes two endpoints:
- `GET /random` — returns a random integer between 1 and 100 as JSON `{"random": <number>}`
- `GET /health` — returns `{"status": "ok"}` for health checks

## Requirements
- Java 17+
- Maven 3.6+

## Build and Run

```bash
mvn clean package
java -jar target/random-api-0.0.1-SNAPSHOT.jar
```

The server starts on port 8000 (as required by the platform deployment contract).

## API Endpoints

| Endpoint | Method | Description |
|----------|--------|-------------|
| `/random` | GET | Returns a random number 1–100 |
| `/health` | GET | Health check (returns 200 OK) |

## Testing

```bash
mvn test
```
