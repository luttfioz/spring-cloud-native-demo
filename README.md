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



--- Discovery Server ekleme.
spring-cloud-starter-netflix-eureka-server
@EnableEurekaServer ile server ayağa kalkar.


spring-cloud-starter-eureka clienta eklenerek, 
@EnableDiscoveryClient ile server ayağa kalkar.
​
projeleri calıstırdığımızda localhost:8761/ ile çalısan servislerimizi görebilriz.
spring-cloud-starter-eureka clienta eklenerek, 
@EnableDiscoveryClient ile server ayağa kalkar.
​
projeleri calıstırdığımızda localhost:8761/ ile çalısan servislerimizi görebilriz.
spring-cloud-starter-eureka clienta eklenerek, 
@EnableDiscoveryClient ile server ayağa kalkar.

Servisler arası data iletişimini FeignClient ile yapabiliriz. Microservis bağımsız Rest işlemlerimizde kullanabiliriz.
spring-cloud-starter-feign ekleyebiliriz.
@EnableFeignClient

Bir servisin feign ile client'ını bir interface oluşturuyoruz.
@FeignClient(url="http") verip de çağrılabilir. 
@FeignClient("customer-service") verip de çağrılabilir. 
JsonNode tipi ile gereric rest objelerini alabiliriz.


-----
Auth : token temelli olması gerekir.Session temelli değil.
OAuth2 ile token işlemini yapıyoruz. OAuth2 spring server yazıp, access token stringi üretiliyor. Cookie'de ya da Headerda token gönderimi yaplıabilir.

SimpleToken AuthServera gitmesi lazım. JWT gitmiyor. Simple serçilirse fazladan bir Rest servis çağırmak durumundasınız. JWT de ise boyut problemi vardır, AuthServer'a gitmesine gerek yoktur. JWT'yi revoke edemiyoruz, etmek için Redisle bir yapı kurmak gerekebilir.

-----

Discovery Server'ın Load Balancer seçimini farkını görebilmemiz için Logger ekleyip farklı instance'lara gidebileceğini görelim.
Farklı instance olabilmesi için port:0 verilir, otomatik port ataması yapılır. product-service ile denedik.

-----
Client Resiliency with Netflix Hystrix
Circuit Breaker = timeout'u düşürme mantığını kurar. Hata alıyorsa beklemeni önler.
spring-cloud-starter-hystrix ekleyebiliriz.
@HystrixCommand eklenir
Fallback için @HystrixCommand(fallbackMethod="..") eklenir
Sprin Retry eklenip denenebilir.

Bulkhead Pattern uygulamak için
HystrixCommand'a threadpool ayarı yapılabilir.

-----
Netflix Zuul
Routing islemleri yapılır.
spring-cloud-starter-zuul ekleyebiliriz.
@EnableZuulProxy

/routes endpointi ile hangi url nereye gidiyor görebiliriz.
Static routing de yapılabilir. Load Balancing islemi yapar.
Buna da circuit breaker eklenebilir.

-----
Gateway ekleme
zuul + eureka discovery + config client(cloud config) ekledi.
localhost:9999/order-service/orders ile gateway aracılığı ile cağrılmıs oldu.

docker run -p openzipkin/zipkin











































