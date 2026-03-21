# Этап 1: Сборка приложения
FROM gradle:8.11.1-jdk21-jammy AS builder

# Устанавливаем рабочую директорию внутри контейнера
WORKDIR /app

# Копируем ВСЕ файлы из текущей директории сервиса (reybo-web-app)
COPY account/ .

# Собираем приложение (создаётся исполняемый JAR) c пропуском тестов -x test
RUN chmod +x gradlew
RUN ./gradlew --version
RUN ./gradlew build -x test --no-daemon

# Этап 2: Финальный образ с JRE
FROM eclipse-temurin:21-jre-jammy

# Устанавливаем рабочую директорию
WORKDIR /app

# Копируем JAR-файл из builder-стадии
COPY --from=builder /app/build/libs/*.jar account.jar

# Порт приложения (используйте тот, который указан в application.properties)
EXPOSE 8765

# Запуск приложения
ENTRYPOINT ["java", "-jar", "account.jar"]