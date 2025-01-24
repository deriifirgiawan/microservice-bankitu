FROM node:22.13.0

WORKDIR /usr/src/app

COPY wait-for-it.sh /wait-for-it.sh

RUN chmod +x /wait-for-it.sh

COPY package*.json ./

RUN npm install

RUN npm uninstall bcrypt

RUN npm i bcrypt

COPY . .

RUN npm run build

CMD ["/wait-for-it.sh", "discovery-server:8761", "--timeout=60", "--", "npm", "run", "start:prod"]

EXPOSE 3000