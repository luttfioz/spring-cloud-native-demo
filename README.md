# spring-cloud-native-demo

----
docker run ....  
docker run -i -t .... terminali açar  
docker run -d .... /bin/sh -c "....."  
docker ps -a = kapanmıs olan tüm containerleri listeler (java -jar görünür java uygulaması ise main commandda)  

-- hands on lab docker command  
docker run hello-world  
docker image ls  
docker rmi hello-world -- siler  
docker pull hello-world (günceli ceker)  
docker container ls = docker ps (çalısan containerleri listeler)  
docker ps -a (kapalıları da listeler)  
docker rm .... ile temizler  
docker run -it alpine  (shell acık kalır)  
docker run alpine /bin/echo "Hello world"  
docker logs ..container id  
docker version  

microservis yapmayınca docker'a gerek yok diye düşünmemek gerekir. Mono uygulamayı da dockerize edebiliriz. Yönetini kolaylaşır.  
image'lar immutable'dir, değişemez.

images:  
docker images #show all images  
docker import #creates an image from tarball  
docker build  #creates image from Dockerfile  
docker commit #creates image from a container  
docker rmi    #removes an image  
docker history #list changes of an image  


Layer ve Volume:  
Datayı Volume'a atmak gerekir, shared alan diyebiliriz, data paylaşımını sağlar. Container uçtuğunda başka container Volume'dan alır  
volumes:  
docker volume create db_data  
docker volume ls  
docker volume rm db_data  
docker volume prune (temizler)  


docker run -it -v dbdata:/my_db_folder alpine  
docker run -it -v dbdata:/my_db_folder2 alpine  

cd ile içine girip aşağıdakini yapınca paylaştığını göreceğiz.  
mkdir abc ya da echo "asd" > a.txt  


containerları prune etsek bile volumeler yaşamaya devam eder, özellikle silinmediği sürece.  

docker volume inspect dbdata  

Volume ile Container ile ilişkilendirme önemli, bizim işimize yarayacak bilgi budur.  

docker container inspect ..container id  

**2nd Day**  
docker run nginx  
docker run -p 8050:80 nginx  
docker logs ...contId -f ile loglara bakılabilir.  

localhost:8050 yazınca dışarıdan erişilmiş olur.  

dockerfile ile image olusturma  
docker build -t image-name  source-dir  
docker build -t demo-node . (demo-node adında root dizinde image olusturur)

docker search node  
docker images | grep demo-node
docker run -p 4567:8081 demo-node (dış porta açıldı. localhost:4567 ile node uygulamamız çalıstı)  
docker kill ..contId
  
mvn package docker:build ile javaya ekledigimiz dockerfile ve spotify docker plugin ile image olusturulur.

docker images (ile listelenir.)  
docker run -p 8070:8070 orderservice-edu (ile calıstırılır) (dış porta açıldı. localhost:8070 ile java mvc uygulamamız çalıstı)  

docker exec ..contId ile container'in içine girebilriz. 

Docker Composer ile yönetim sağlanabilir.  

docker service create --name who-am-i --publish 8000:8000 --replicas 3 training/whoami:latest  
(docker swarm init)  
docker service ls  
docker service scale who-am-i=2 (servisi 2 instance indirir)
docker service rm (servisi siler)

curl http://localhost:8000



-----------------------------------
***********************************
-----------------------------------

Do not use microservices if not ready
------
peak yaptığı dönemler var mı?
ölçeklenme ihtiyacı var mı?
development & deploy ihtiyacı var mı?

avantaj sağlamayabilir.
Golden Hammer patternı vardır, microservices herseyin çözümü değil.
REST : Resource işlemleri GET,POST,PUT,DELETE vs Method yöntemleri kullanılmalı

Architectural Principles:
Stateless servisler ölçeklenebilir. Stateful servisleri yapmak zordur.
HATEOAS yöntemini implement etmek.
OAUTH2 ile authentication ile yapılır

Spring Rest:
Design Principles : URI Design
security token'ı headerda olması gerekir. Body'da pure data olmalı

CAP Triangle

Config server default portu 8888dir  
localhost:8888/product-service/default
localhost:8888/product-service/prod
${MY_ENV:10} şeklinde yml dosyasında değer verilebilir.

Service Discovery and Eureka  default portu 8761dir  

feignClient

single point of failure: load balancer fail ise uygulamalar çalısmaz.  





--- Actuator Endpointleri ve Spring Actuator entegrasyonu

Spring Boot Admin diye bir proje var. Log seviyesini değiştirmemizi sağlıyor
