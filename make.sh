cd eureka-server/
mvn clean && mvn package -DskipTests
docker build . -t eureka-server
cd ..

cd config-server/
mvn clean && mvn package -DskipTests
docker build . -t config-server
cd ..

cd api-gateway/
mvn clean && mvn package -DskipTests
docker build . -t api-gateway
cd ..

cd movie-service/
mvn clean && mvn package -DskipTests
docker build . -t movie-service
cd ..

cd catalog-service/
mvn clean && mvn package -DskipTests
docker build . -t catalog-service
cd ..



cd serie-service/
mvn clean && mvn package -DskipTests
docker build . -t serie-service
cd ..

docker-compose up
