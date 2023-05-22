docker pull postgres
docker run -itd -e POSTGRES_USER=chipolino -e POSTGRES_PASSWORD=bestPa55wor# -p 5432:5432 --name postgresql postgres