# Stage 1: Build stage (если требуется оптимизация)
FROM node:20-alpine AS builder

WORKDIR /app

COPY . .

# Stage 2: Production stage
FROM nginx:1.25-alpine

# Копируем статические файлы из текущей директории
COPY --from=builder /app /usr/share/nginx/html

# Копируем пользовательский конфиг Nginx
COPY nginx.conf /etc/nginx/conf.d/default.conf

EXPOSE 80

CMD ["nginx", "-g", "daemon off;"]
