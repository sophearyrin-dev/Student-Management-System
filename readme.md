### Student Management System

## Architecture


## Using Jib
With Jib, if we want to build docker image, we don't need docker install on our local machine.
We install docker on our machine to build docker image locally before we pushing it to dockerhub
To use Jib library:
open pom.xml and add below code:
```html
<plugin>
    <groupId>com.google.cloud.tools</groupId>
    <artifactId>jib-maven-plugin</artifactId>
    <version>2.5.2</version>
    <configuration>
        <from>
            <image>openjdk:15</image>
        </from>
        <container>
            <ports>
                <port>8080</port>
            </ports>
            <format>OCI</format>
        </container>
    </configuration>
</plugin>
```
If you have any issues with jib refer to:
https://github.com/GoogleContainerTools/jib/blob/master/docs/faq.md
or have issue with https://github.com/GoogleContainerTools/jib/blob/master/docs/faq.md#what-should-i-do-when-the-registry-responds-with-unauthorized

- Build Local Image with Jib
```
  ./mvnw jib:dockerBuild -Djib.to.image=studentmgt:v1
```

- To run docker container:
```
docker run --name studentmgt -p 8080:8080 studentmgt:v1
```

- Build docker image to dockerhub
  Login to docker: docker login
- Push image to dockerhub:
```
  ./mvnw clean install jib:build -Djib.to.image=sophearyrin/studentmgtproject:latest
```
If you push to dockerhub and faced any error, try this:
```
./mvnw clean install jib:build -Djib.to.image=sophearyrin/studentmgtproject:latest -D jib.to.auth.username=sophearyrin -Djib.to.auth.password=yourpassword
```
To pull image from docker hub
```
docker pull sophearyrin/studentmgtproject
```

To show all container:
```
docker ps -a
```

Run again by using image from docker hub:
```
docker run -p 8080:8080 sophearyrin/studentmgtproject
```

Maven Profiles

Commands used:
```
./mvnw clean install -P build-frontend -P jib-push-to-dockerhub -Dapp.image.tag=2
./mvnw clean install -P build-frontend -P jib-push-to-local -Dapp.image.tag=latest
```


## AWS And ElasticBenstalk
Go to: https://aws.amazon.com/
Then create your account -> Elastic Benstalk -> Create sample application
- How to upload our application to Elastic Beanstalk
Create elasticbenstalk foloder in our project -> create docker-compose.yml
- What is docker compose?
Docker compose is a tool for defining and running multiple container docker application. With compose we use yaml file to config our applicatoin’s service. Then with single command we can create and strat all services form your configuration.
Write code below:
```html
docker-compose.yml
  version: "3.8"
  services:
  backend:
  image: "sophearyrin/studentmgtproject:latest"
  ports:
  - "80:8080"
  restart: "always"
```
Then we take the docker-compose.yml upload to Elastic Beanstalk
Check it out: http://studentmgtproject-env.eba-xe3x22t9.us-east-2.elasticbeanstalk.com/







