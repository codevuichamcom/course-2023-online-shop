# Sử dụng image Node phiên bản 16 (hoặc phiên bản phù hợp)
FROM node:17-alpine
WORKDIR /app

# Sao chép tệp package.json và package-lock.json để cài đặt dependencies
COPY package*.json ./
RUN npm install

# Sao chép mã nguồn ứng dụng
COPY . .
EXPOSE 3001
CMD [ "npm","run","dev" ]

