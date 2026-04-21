FROM node:22-alpine
WORKDIR /app

# Копируем package.json из подпапки frontend-crm
COPY frontend-crm/package*.json ./

# Устанавливаем зависимости
RUN npm install

# Копируем весь код из подпапки frontend-crm
COPY frontend-crm/ .

# Собираем приложение
RUN npm run build

EXPOSE 8082
CMD ["npm", "start"]