# Hướng dẫn chạy project Shopping-Online-Backend
## Yêu cầu: 
- Cài đặt Docker & Docker compose

### 1. Để run project
```
docker-compose up -d --build --force-recreate
```

### 2. Truy cập
```
http:localhost:3001
```

### 2. Để stop project
```
docker-compose down
```

### 3. Khi có source code thay đổi
```
git pull origin master
docker-compose up -d --build --force-recreate
```