## 启动命令
```bash
java -Duser.timezone=UTC -Dspring.profiles.active=dev -Denv.filepath=path/to/your -jar ./business/target/nls-api.jar
```

## 1.配置

1. 新建一个以.env.[环境]的文件,如.env.dev,启动项目时会自动读取此env文件里配置的参数。
2. 可配置项
  ```htmml
   SERVER_PORT=18000
   SERVER_CONTEXT_PATH=/nls
   APPLICATION_NAME=nls-api
  ```

## 2.多语言支持

在请求时，header中带上accept-language参数就可以进行国际化，支持语言(简体中文 zh-CN，美式英语 en-US，日语 ja-JP)
