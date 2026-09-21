
本地新建.env文件：
```shell
MYSQL_ROOT_PASSWORD=your_root_password
MYSQL_PASSWORD=your_trading_password
```

```shell
# 启动 MySQL（后台运行）
docker compose up -d

# 查看容器状态
docker compose ps

# 查看数据库日志
docker compose logs -f mysql
```