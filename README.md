[![Java CI with Maven](https://github.com/BudgetBuddyApp/backend/actions/workflows/ci.yml/badge.svg)](https://github.com/BudgetBuddyApp/backend/actions/workflows/ci.yml)

# Backend of the MoneyTracker project

---
Todo:

- [x] Docs (wiki, see Notion)
- [ ] Swagger
- [ ] Security ([Zitadel](https://github.com/zitadel/zitadel) or [Keycloak](https://github.com/keycloak/keycloak))
- [ ] TLS/SSL
- [x] Nginx (docker)

---

### IMPORTANT!

Before starting work, you need to:

1. Create a `.env` file and specify the necessary environment variables in it (see `config/env/template.env`)
2. Optionally, generate self-signed certificates (see `config/certs/generate_certs.sh`)

### Generation of the test coverage report

```shell
mvn clean test jacoco:report
```

> The minimum test coverage requirement for the project is **50%**.

## Docker
Two compose scripts are used, each with its own profile: `backend` and `idp`.  
_This needs to be taken into account!_

### Run in Docker

```shell
mvn clean install
```

```shell
# Run backend along with the IdP
docker compose --profile "*" up -d --build
```

```shell
# e.g. see backend logs
docker logs -f backend
```

---

# Getting Started

### Reference Documentation

For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/3.2.2/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/3.2.2/maven-plugin/reference/html/#build-image)
* [Spring Boot Testcontainers support](https://docs.spring.io/spring-boot/docs/3.2.2/reference/html/features.html#features.testing.testcontainers)
* [Testcontainers Postgres Module Reference Guide](https://java.testcontainers.org/modules/databases/postgres/)
* [Liquibase Migration](https://docs.spring.io/spring-boot/docs/3.2.2/reference/htmlsingle/index.html#howto.data-initialization.migration-tool.liquibase)
* [Spring Web](https://docs.spring.io/spring-boot/docs/3.2.2/reference/htmlsingle/index.html#web)
* [JOOQ Access Layer](https://docs.spring.io/spring-boot/docs/3.2.2/reference/htmlsingle/index.html#data.sql.jooq)
* [Testcontainers](https://java.testcontainers.org/)

### Guides

The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)

### Testcontainers support

This project
uses [Testcontainers at development time](https://docs.spring.io/spring-boot/docs/3.2.2/reference/html/features.html#features.testing.testcontainers.at-development-time).

Testcontainers has been configured to use the following Docker images:

* [`postgres:latest`](https://hub.docker.com/_/postgres)

Please review the tags of the used images and set them to the same as you're running in production.

