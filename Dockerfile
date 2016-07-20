FROM ubuntu
MAINTAINER leiyi1152

RUN add-apt-repository ppa:webupd8team/java
RUN apt-get update
RUN apt-get install -y vim wget curl oracle-java7-installer tomcat7

RUN update-alternatives --display java
RUN echo "JAVA_HOME=/usr/lib/jvm/java-7-oracle">> /etc/environment
RUN echo "JAVA_HOME=/usr/lib/jvm/java-7-oracle">> /etc/default/tomcat7


# 容器需要开放Tomcat 8080端口
EXPOSE 8080

# 设置Tomcat7初始化运行，SSH终端服务器作为后台运行
ENTRYPOINT service tomcat7 start && /usr/sbin/sshd -D