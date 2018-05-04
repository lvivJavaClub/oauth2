# oauth2

## cli
CLI based Google OAuth2 application example.

[Instructions](/cli/README.md)

## servlet
Servlet based Google OAuth2 application with.

[Instructions](/servlet/README.md)

# Oauth2 Docker containers

## Maven plugin
Used [docker plugin](https://github.com/fabric8io/docker-maven-plugin)

Please review [User manuals](http://dmp.fabric8.io/)

# How to run Oauth2 in docker containers
**To start Oauth2 application in container:**
1) check in you have installed docker-compose(docker-compose version), if to install it

2) in module `auth-server` execute mvn clean install - it will create docker image
3) in module `spring` execute mvn clean install - it will create docker image
4) check if images are created (`docker images` -> there should be(by default): _docker-com.lohika.jclub.oauth2-spring_, _docker-com.lohika.jclub.oauth2-auth-server_)
5) **START application** run docker-compose up from directory compose
6) (OPTIONAL) also you can run it from `oauth2` execute mvn clean install, 
    but there are some restrictions:
    Current docker plugin support '2' version of docker-compose, networks don't work properly, maybe others;

7) **START application** run docker-compose down from directory compose