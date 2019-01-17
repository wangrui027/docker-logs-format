# Docker容器日志格式化

本程序使用`java`代码实现`Docker`容器的日志格式功能。

## 需求分析

通常我们使用`docker ps -a`命令获取容器`containerid`，使用`docker logs containerid`获取容器的运行日志信息，默认情况下日志内容实际上在`/var/lib/docker/containers/xxx/xxx-json.log`文件中（xxx为对应的containerid）以每行一个`json object`的形式存放，然鹅`json object`一旦数量庞大起来阅读不便，这些`json object`包含三个属性，分别是`log`、`stream`和`time`，使用table列出来岂不是更直观，遂有了本项目。

## 使用

1、执行`mvn clean compile assembly:single`，在`target`目录下生成可执行jar包`docker-logs-format-1.0-SNAPSHOT-jar-with-dependencies.jar`；

2、将jar包上传至Docker宿主或者从Docker宿主中下载Docker日志文件到本地；

3、执行`java -jar docker-logs-format-1.0-SNAPSHOT-jar-with-dependencies.jar logFile `（`logFile` 为日志文件路径），在日志文件同目录下会生成同名`html`文件，使用浏览器打开即可查看。



PS：如果没有`jdk`环境，请跳过步骤1，直接下载[可执行jar包](docker-logs-format-1.0-SNAPSHOT-jar-with-dependencies.jar)使用，jar包编译版本为`1.8`，运行请至少安装`jre1.8`版本。
