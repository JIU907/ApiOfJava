FROM adoptopenjdk:11.0.11_9-jdk-hotspot
# 构建参数
ARG JAR_FILE=apiOfJava-0.0.1-SNAPSHOT.jar
ARG PARAMS="-server "
ENV WORK_PATH="/tmp"
ENV VARS=""

# 执行Bash命令
RUN mkdir -p /var/bi/

# 设置workdir
WORKDIR ${WORK_PATH}
# 拷贝jar包
COPY build/libs/${JAR_FILE} ${WORK_PATH}/app.jar

ENTRYPOINT ["/bin/sh","-c","java ${PARAMS} ${VARS} -jar ${WORK_PATH}/app.jar"]