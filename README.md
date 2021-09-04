# beerstock

API REST para gerenciamento de estoque

***O projeto tem como finalidade entender a aplicação de testes em uma API utilizando JUnit, Mockito e Hamcrest.***

## Enpoints

Uri|Método|Descrição|Request Body|Response Body|Status
---|------|---------|------------|-------------|------
/api/v1/beers|GET|obter todos os registros|n/a|coleção de Bear|200
/api/v1/beers/{name}|GET|buscar um registro pelo nome|n/a|Bear|200/404
/api/v1/beers|POST|criar um novo registro|Bear sem o id|Bear|201/400
/api/v1/beers/{id}|DELETE|remover um registro do banco de dados|n/a|n/a|204/404
/api/v1/beers/{id}/increment|PATCH|adicionar ao estoque|"quantity": int|Bear com estoque atualizado|200/400/404
/api/v1/beers/{id}/decrement|PATCH|remover do estoque|"quantity": int|Bear com estoque atualizado|200/400/404

 - **Bear**

```Json
    {
        "name": "Colorado appia",
        "brand": "Colorado",
        "max": 20,
        "quantity": 10,
        "type": "LAGER"
    }
```

 - **Tipos**

```Json
    {
        "type": "LAGER",
        "type": "MALZBIER",
        "type": "WITBIER",
        "type": "WEISS",
        "type": "ALE",
        "type": "IPA",
        "type": "STOUT"
    }
```
