[![Java CI with Maven](https://github.com/BudgetBuddyApp/backend/actions/workflows/ci.yml/badge.svg)](https://github.com/BudgetBuddyApp/backend/actions/workflows/ci.yml)

### Backend проекта MoneyTracker.

###### Предоставляет REST API для работы над расходами:
- Выборка данных: расходы по периодам, агрегация, поиск
- Добавление расходов
- Обновление расходов
- Удаление расходов

---
Todo:
- [ ] Docs (wiki, see Notion)
- [ ] Swagger
- [ ] Security (consider [Zitadel](https://github.com/zitadel/zitadel) or [Keycloak](https://github.com/keycloak/keycloak))
- [ ] TLS/SSL
- [ ] Nginx (docker)

---
### ВАЖНО!
Перед началом работы нужно:
1. Создать `.env` файл и прописать туда нужные переменные среды (см. `docker-compose.yml`)
2. Сгенерировать заранее self-signed SSL сертификаты (см. `./crt/generate_ssl.sh` )

### Генерация отчета о покрытии тестами
```shell
mvn clean test jacoco:report
```

> Минимальное требование к покрытию тестами проекта **50%**

### Запуск в докере
```shell
mvn clean install

docker-compose build

docker-compose up -d

# Логи
docker logs -f backend
```