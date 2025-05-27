# Embalagem Service

Microserviço em Spring Boot que calcula caixas ideais para pedidos.

## Pré-requisitos

- Docker instalado

## Como rodar com Docker

```bash
# Construa a imagem
docker build -t embalagem-service .

# Rode o container
docker run --rm -p 8080:8080 embalagem-service

# Acesse a API no navegador
http://localhost:8080
```

## Testando

### Swagger UI

Acesse pelo navegador:

http://localhost:8080/swagger-ui.html  
ou  
http://localhost:8080/swagger-ui/index.html

### Exemplo de payload para POST /api/embalagem

```json
[
  {
    "orderId": "pedido1",
    "products": [
      { "id": "p1", "height": 10, "width": 20, "length": 30 },
      { "id": "p2", "height": 15, "width": 15, "length": 15 },
      { "id": "p3", "height": 25, "width": 30, "length": 10 }
    ]
  }
]
```
