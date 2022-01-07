# merchants

./gradlew build
docker build -t episodeone/merchants-dev:0.1.0 .
kubectl apply -f merchants-dev-deployment.yaml 
