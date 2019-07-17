FROM adoptopenjdk/maven-openjdk11:latest

WORKDIR /home/tomek
RUN apt-get update
RUN apt-get install -y vim

RUN apt-get update
# make sure that locales package is available
RUN apt-get install --reinstall -y locales
# uncomment chosen locale to enable it's generation
RUN sed -i 's/# pl_PL.UTF-8 UTF-8/pl_PL.UTF-8 UTF-8/' /etc/locale.gen
# generate chosen locale
RUN locale-gen pl_PL.UTF-8
# set system-wide locale settings
ENV LANG pl_PL.UTF-8
ENV LANGUAGE pl_PL
ENV LC_ALL pl_PL.UTF-8
# verify modified configuration
RUN dpkg-reconfigure --frontend noninteractive locales


#COPY . .
##COPY ./m2 /root/.m2
#RUN mvn clean
#RUN mvn install
#CMD ["tail", "-f", "/dev/null"]
#CMD ["mvn", "spring-boot:run"]

COPY ./target/bibliography-0.0.1-SNAPSHOT.jar ./app.jar
CMD ["java", "-jar", "./app.jar"]
