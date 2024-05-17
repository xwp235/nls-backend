# 启动命令
```bash
java -jar ./business/target/nls-api.jar  -Duser.timezone=UTC -Dspring.profiles.active=dev
```

# 配置

1. 新建一个以.env.[环境]的文件,如.env.dev,启动项目时会自动读取此env文件里配置的参数。
2. 可配置项
  ```htmml
   SERVER_PORT=18000
   SERVER_CONTEXT_PATH=/nls
  ```
