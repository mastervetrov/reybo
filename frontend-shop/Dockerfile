FROM node:22-alpine
WORKDIR /app

# Копируем package.json из подпапки frontend-crm
COPY frontend-shop/package*.json ./

# Устанавливаем зависимости
RUN npm install

# Копируем весь код из подпапки frontend-crm
COPY frontend-shop/ .

# Собираем приложение
RUN npm run build

EXPOSE 3000
CMD ["npm", "start"]