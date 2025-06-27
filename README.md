# myURLparser

myURLparser is a Java/Spring Boot-based framework for distributed URL ingestion, processing, and management. It is designed for scalable batch processing of URLs, supporting both SQL and Cassandra backends, and provides integration with Kafka and Redis for efficient message passing and caching.

## Features

- **Batch URL ingestion**: Accepts batches of URLs via HTTP endpoints.
- **Kafka Integration**: Publishes and consumes URL processing messages using Kafka.
- **Multi-backend Support**: Offers both SQL and Cassandra versions for flexible persistence.
- **Spring Boot Microservice**: Easily configurable and extensible Java microservice architecture.
- **Thread Pool Processing**: Utilizes thread pool executors for concurrent URL processing.
- **Redis Caching**: Optional Redis support for caching and fast lookup.
- **Docker Support**: Includes Dockerfiles for containerized deployments.
- **Command Scripts**: Provides scripts for starting services, Kafka, Redis, and Cassandra, as well as managing topics.

## Architecture Overview

- **URLFeederService**: Exposes a REST endpoint (`/batch`) to receive sets of URLs for processing.
- **KafkaMessageListener**: Listens to a Kafka topic (`html_topic`) and triggers URL processing.
- **ThreadpoolConfig**: Manages thread pool executors for parallel processing.
- **URL Model**: Tracks each URL’s metadata, including processing count and timestamps.
- **Configurable Backends**: Choose between SQL or Cassandra for persistent storage.

## Quick Start

### Prerequisites

- Java 21
- Maven
- Docker (for running containers)
- Kafka
- Redis (optional)
- Cassandra (for Cassandra version)

### Build & Run

#### SQL Version

```sh
cd URL.feederservice/SQL.version/sourcecode/
mvn clean install -DskipTests
docker build -f Dockerfile -t url-feeder-service:latest .
docker run --name url-feeder-service -p8085:8085 url-feeder-service
```

#### Cassandra Version

```sh
cd URL.feederservice/Cassandra.version/sourcecode/
mvn clean install -DskipTests
docker build -f Dockerfile -t url-feeder-service:latest .
docker run --name url-feeder-service -p8085:8085 url-feeder-service
```

### Supporting Services

- Start Kafka and create the topic:

```sh
docker run --name kafka -p9092:9092 kafka
kafka-topics.sh --create --zookeeper 172.17.0.1:2181 --replication-factor 1 --partitions 1 --topic html-topic
```

- Start Redis (optional):

```sh
docker run --name myredis -p6379:6379 redis
```

- Start Cassandra (for Cassandra version):

```sh
docker run --name cassandra -p 9042:9042 -d cassandra
```

## Usage

- Send a batch of URLs (as a set of URL objects) to the `/batch` endpoint.
- URLs are published to a Kafka topic for processing.
- The service processes each URL, updates its metadata, and persists it to the configured backend.

## Configuration

- Configure service properties (such as `baseUrl` for the feeder service) in your `application.properties` or using environment variables.
- Thread pool sizes and Kafka topics can be tuned via configuration.

## Folder Structure

- `HTMLprocessor/` - Main Spring Boot application and worker logic.
- `URL.feederservice/SQL.version/` - SQL-backed service version.
- `URL.feederservice/Cassandra.version/` - Cassandra-backed service version.

## Example: Submitting URLs

```json
POST /batch
[
  {
    "url": "https://example.com",
    "ContentType": "text/html"
  },
  {
    "url": "https://another.com",
    "ContentType": "application/json"
  }
]
```

## License

[MIT](LICENSE) (or your license info here)

---

*This README was generated with the help of project source code and may need further details according to your deployment specifics.*
