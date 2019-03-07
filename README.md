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





