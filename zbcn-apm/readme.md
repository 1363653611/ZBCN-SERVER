# APM - 服务性能监控和排查
- 全称：Application Performance Monitor，当然也有叫 Application Performance Management tools

## 使用zipkin 链路追踪
- 跳转到 lib目录下
- zipkin 启动命令
```shell
java -jar zipkin-server-2.9.4-exec.jar --STORAGE_TYPE=elasticsearch --ES_HOSTS=localhost:9200  --ES_USERNAME=elastic --ES_PASSWORD=123456
```


# 参考
- https://www.cnblogs.com/xiaoqi/p/apm.html