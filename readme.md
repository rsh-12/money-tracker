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