# Build Angular
FROM node:23 AS builder

WORKDIR /src

# Install Angular CLI
RUN npm i -g @angular/cli

# Copy srcs into container
COPY angular.json .
COPY package.json .
COPY package-lock.json .
COPY tsconfig.app.json .
COPY tsconfig.spec.json .
COPY tsconfig.json .
COPY public public
COPY src src

# Install the packages from package.json -> node_module
RUN npm ci

# Build the Angular application -> dist/day32-workshop/browser
RUN ng build

# Copy Angular to Caddy
FROM caddy:2-alpine

LABEL maintainer="chuk"

WORKDIR /www

# Copy the Angular artifacts and the Caddyfile
COPY --from=builder /src/dist/day32-workshop/browser html
COPY Caddyfile .

ENV PORT=8080
EXPOSE ${PORT}

SHELL [ "/bin/sh", "-c" ]
ENTRYPOINT caddy run --config ./Caddyfile
