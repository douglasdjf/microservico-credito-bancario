
# Inicialização do RabbitMQ e Keycloak

```
docker compose up
```

### OBS: o comando anterior ja cria o network 'ms-network'

# Configuração Build Docker


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




## 5. Build do MicroServico Gateway

### 4.1 dentro da pasta principal do projeto microservico-gateway executar comando:

```
docker build --tag ms-gateway:1.0 .
```

### 4.2 Executar imagem criada anteriormente

```
docker run --name ms-gateway -p 8080:8080 --network ms-network -e EUREKA_SERVER=eureka-server -e KEYCLOAK_SERVER=keyclock -e KEYCLOAK_PORT=8080 ms-gateway:1.0 
```




# Acessos referências

### 1. RabbitMQ: http://localhost:8761/
### 2. KeyClok: http://localhost:8081/
### 3. API (Gateway): http://localhost:8080/



# Importar Realm Keyclock

### o arquivo 'keyclok-realm-export' pode ser importando no realm do Keyclok pois já está configurado para o cliente do projeto.

### OBS: depois da exportação e configuração é necessário ir no menu Realm Settings e atualizar na aba General o campo Frontend URL para a URL do docker : http://keyclock:8080



# Várias Instâncias de um microserviço no docker

### Basta apenas remover o parâmetro --name do container e adicionar o -P para porta aleatório. Exemplo:

```
docker run -P --network ms-network  -e RABBITMQ_SERVER=rabbitmq-3.8 -e EUREKA_SERVER=eureka-server ms-cartoes:1.0 
```