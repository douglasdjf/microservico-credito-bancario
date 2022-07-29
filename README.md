

## Configuração Docker


## Criar uma Network para a comunicação entre os containers

```
docker network create ms-network
```

## 1. Build do MicroServico EurekaServer

### 1.1 dentro da pasta principal do projeto eurekaserver executar comando:

```
docker build --tag ms-eureka-server:1.0 .
```

### 1.2 Executar imagem criada anteriormente

```
docker run --name eureka-server -p 8761:8761 --network ms-network  ms-eureka-server:1.0
```






## 2. Build do MicroServico Avaliador Crédito

### 2.1 dentro da pasta principal do projeto microservico-avaliadorcredito executar comando:

```
docker build --tag ms-avaliador-credito:1.0 .
```

### 2.2 Executar imagem criada anteriormente

```
docker run --name ms-avaliador-credito --network ms-network -e RABBITMQ_SERVER=rabbitmq-3.8 -e EUREKA_SERVER=eureka-server ms-avaliador-credito:1.0
```





## 3. Build do MicroServico Cartões

### 3.1 dentro da pasta principal do projeto microservico-cartoes executar comando:

```
docker build --tag ms-cartoes:1.0 .
```

### 3.2 Executar imagem criada anteriormente

```
docker run --name ms-cartoes --network ms-network  -e RABBITMQ_SERVER=rabbitmq-3.8 -e EUREKA_SERVER=eureka-server ms-cartoes:1.0 
```



## 4. Build do MicroServico Clientes

### 4.1 dentro da pasta principal do projeto microservico-clientes executar comando:

```
docker build --tag ms-clientes:1.0 .
```

### 4.2 Executar imagem criada anteriormente

```
docker run --name ms-clientes --network ms-network -e EUREKA_SERVER=eureka-server ms-clientes:1.0 
```
