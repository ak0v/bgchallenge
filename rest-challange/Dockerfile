FROM google/cloud-sdk:alpine
RUN apk --update add openjdk8-jre 
ADD .$GOOGLE_APPLICATION_CREDENTIALS /usr/src/bulorosinterview-174d7aa0bacb.json 
ADD .$GOOGLE_CLOUD_PROJECT bulorosinterview
COPY bulorosinterview-174d7aa0bacb.json /usr/src/
COPY rest-challange-0.0.2-SNAPSHOT-spring-boot.jar /usr/src/restchallange/ 
RUN gcloud auth activate-service-account --key-file /usr/src/bulorosinterview-174d7aa0bacb.json 
WORKDIR /usr/src/restchallange
EXPOSE 8080
CMD ["java", "-jar", "rest-challange-0.0.2-SNAPSHOT-spring-boot.jar"]