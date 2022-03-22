FROM node:16-alpine3.14 as build-step
COPY . /app
WORKDIR /app
RUN npm install
RUN npm run build

FROM nginx:stable-alpine
EXPOSE 80
COPY --from=build-step /app/dist/customer-phones-frontend /usr/share/nginx/html

