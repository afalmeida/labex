# LABEX

- Api de gerenciamento de laboratorio e exame.

## Tecnologias e conceitos utilizados
- Java 8
- Spring boot
- Spring Data-JPA
- Spring Data-QUERY_NATIVE
- Lombok
- Documentação Swagger
- Junit
- Mockito
- Maven
- Mysql
- Desing Patters (Facade,Factory)
- Restful
- Docker

## Requisitos

- Maven
- JdK 8
- Docker
- docker-compose

## Instalação

Clonar este repositório

```
git clone https://github.com/afalmeida/labex.git
```

Acessar a pasta do projeto 

```
cd labex
```


Acessar a pasta do mysql para iniciar o BD 

```
cd mysql
```

Executar o docker compose, para fazer pull e executar a imagem do mysql, phpmyadmin(gerenciador do mysql)

```
docker-compose up -d
```

Acessar a pasta do projeto 

```
cd .. 
```

Instalar as dependências

```
mvn clean install
```

 Subir o ambiente

```
java -jar target/labex-0.0.1-SNAPSHOT.jar 
```

## Validar

Acessar resource-status, retorno tem que ser OK

```
http://localhost:8081/resource-status
```

## Documentacao
http://localhost:8081/swagger-ui.html


Mão na massa!
