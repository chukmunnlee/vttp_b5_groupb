FROM node:23

LABEL maintainer=chukmunnlee

## Build image
WORKDIR /app

# copy files int the image
COPY *.js .
COPY *.json .
COPY public public
COPY views views

# install the packages
RUN npm ci 

## Run image
ENV PORT=3000

EXPOSE ${PORT}

SHELL [ "/bin/sh", "-c" ]
ENTRYPOINT node main.js 