#jdk8环境
FROM java:8
#挂载目录
VOLUME /tmp
#copy or add to image
COPY main-app.jar main-app.jar
#修改容器时间
RUN /bin/cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime && echo 'Asia/Shanghai' >/etc/timezone
#在容器里面找到这个包
RUN bash -c "touch /main-app.jar"
ENV PROFILES=$PROFILES
ENV JAVA_OPTS=$JAVA_OPTS
#暴露端口号
EXPOSE 8104
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -jar main-app.jar --spring.profiles.active=$PROFILES" ]
#ENTRYPOINT ["java","-jar","main-app.jar"]
