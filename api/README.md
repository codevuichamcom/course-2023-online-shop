# Hướng dẫn chạy project Shopping-Online-Backend
## Yêu cầu: 
- Cài đặt Docker

### 1. Để run project
```
docker-compose up -d
```

### 2. Để stop project
```
docker-compose down
```

### 3. Khi có source code thay đổi
```
docker-compose down
git pull origin master
docker-compose up -d
```