#jdk8环境
FROM java:8
#挂载目录
VOLUME /tmp
#copy or add to image
COPY operation-app.jar operation-app.jar
#修改容器时间
RUN /bin/cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime && echo 'Asia/Shanghai' >/etc/timezone
#在容器里面找到这个包
RUN bash -c "touch /operation-app.jar"
ENV PROFILES=$PROFILES
ENV JAVA_OPTS=$JAVA_OPTS
#暴露端口号
EXPOSE 8106
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -jar operation-app.jar --spring.profiles.active=$PROFILES" ]
#ENTRYPOINT ["java","-jar","operation-app.jar"]
