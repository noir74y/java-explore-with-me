FROM amazoncorretto:11-alpine-jdk
#ENV JAVA_TOOL_OPTIONS -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:9092
COPY target/*.jar stat-svc-app.jar
ENTRYPOINT ["java","-jar","/stat-svc-app.jar"]
