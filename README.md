# Agrix 🧑‍🌾

## Sobre:
Projeto realizado no final do módulo de Java fornecido pela Trybe, concluído em Outubro de 2023. Trata-se de uma api responsável por gerenciar fazendas, usando o framework Spring do Java.


## 🛠️ Tecnologias e libs utilizadas:
- [Java EE](https://www.oracle.com/java/)
- [Spring Framework](https://spring.io/projects/spring-framework)
    - Spring Web
    - Spring Data
    - Spring Security
- [Mysql](https://www.mysql.com/)
- [JWT](https://jwt.io/)
- [JUnit](https://junit.org/junit5/)
- [Docker](https://www.docker.com/)

## ↗️ Endpoints:

Obs: Caso opte por fazer o download e instalação do projeto, você poderá observar os endpoints com mais detalhe 
no link: `http://localhost:8080/swagger-ui/index.html/`

### Login

```html
/auth/login
```

- Utiliza o método POST, realiza o login de usuário existente no banco de dados.
- Caso a requisição seja feita corretamente, retorna um objeto com um token de autorização, com o status http `200`. O token será necessário em outras requisições.
- Caso usuário e/ou senha estejam incorretos, retorna uma mensagem de erro, com o status hhtp `403`.
- O corpo da requisição deve obedecer o seguinte formato:

```json
    {
      "username": "string",
      "password": "string"
    }
```

### Person

```html
/persons
```
- utiliza o método POST, adiciona uma pessoa usuária ao banco de dados.
- Caso a requisição seja feita corretamente, retorna um status http `201` com um objeto contendo as informações do usuário criado.
- O corpo da requisição deve obedecer o seguinte formato:

```json
    {
      "username": "string",
      "password": "string",
      "role": "string"
    }
```

### Farm

```html
/farms
```
- Utilizando o método GET:
    - Lista as fazendas presentes no banco de dados. Necessário token de autorização para efetuar a operação e usuário com `role` ADMIN ou MANAGER.
    - Em caso de sucesso, retorna status http `200` junto de uma lista de fazendas presentes no banco de dados.
    - Caso o usuário não possua a autorização necessária ou caso a mesma esteja inválida, retorna uma mensagem de erro com o status http `403`.

- Utilizando o método POST:
    - Adiciona uma fazenda ao banco de dados. Necessário token de autorização para efetuar a operação.
    - Em caso de sucesso, retorna o status http `201` e um objeto com os dados da fazenda adicionada ao banco de dados.
    - Caso o usuário não possua a autorização necessária ou caso a mesma esteja inválida, retorna uma mensagem de erro com o status http `403`.
    - o corpo da requisição deve obedecer o seguinte formato:

```json
    {
      "name": "string",
      "size": 0
    }
```

```html
/farms/{farmId}/crops
```
- Utilizando o método GET:
  - Lista as plantações da fazenda indicada pelo id. Necessário token de autorização para efetuar a operação.
  - Em caso de sucesso, retorna o status http `200` e uma lista de objetos com os dados das plantações da fazenda.
  - Caso o usuário não possua a autorização necessária ou caso a mesma esteja inválida, retorna uma mensagem de erro com o status http `403`.
  - Se não for encontrado uma fazenda com o id especificado, retorna uma mensagem de erro com um status http `404`.

- Utilizando o método POST:
  - Adiciona uma plantação, presente no corpo da requisição, à fazenda indicada pelo id. Necessário token de autorização para efetuar a operação.
  - Em caso de sucesso, retorna o status http `201` e o objeto contendo os dados da operação realizada.
  - Caso o usuário não possua a autorização necessária ou caso a mesma esteja inválida, retorna uma mensagem de erro com o status http `403`.
  - Se não for encontrado uma fazenda com o id especificado, retorna uma mensagem de erro com um status http `404`.
  - o corpo da requisição deve obedecer o seguinte formato:

```json
    {
      "id": 0,
      "name": "string",
      "plantedArea": 0,
      "plantedDate": "2024-08-09",
      "harvestDate": "2024-08-09"
    }
```

```html
/farms/{id}
```

- Utiliza o método GET, retorna a fazenda especificada pelo id (caso exista). Necessário token de autorização para efetuar a operação.
- Em caso de sucesso, retorna o status http `200` com um objeto contendo os dados da fazenda.
- Caso o usuário não possua a autorização necessária ou caso a mesma esteja inválida, retorna uma mensagem de erro com o status http `403`.
- Se não for encontrado uma fazenda com o id especificado, retorna uma mensagem de erro com um status http `404`.

### Crop

```html
/crops
```
- Utiliza o método GET, Lista as plantações presentes no banco de dados. Necessário token de autorização para efetuar a operação e usuário com role ADMIN ou MANAGER.
- Em caso de sucesso, retorna o status http `200` com uma lista de objetos contendo os dados das plantações.
- Caso o usuário não possua a autorização necessária ou caso a mesma esteja inválida, retorna uma mensagem de erro com o status http `403`.

```html
/crops/{id}
```
- Utiliza o método GET, retorna a plantação indicada pelo id (Caso exista). Necessário token de autorização para efetuar a operação.
- Em caso de sucesso, retorna o status http `200` com um objeto contendo os dados das plantação.
- Caso o usuário não possua a autorização necessária ou caso a mesma esteja inválida, retorna uma mensagem de erro com o status http `403`.
- Se não for encontrado uma plantação com o id especificado, retorna uma mensagem de erro com um status http `404`.

```html
/crops/search
```
- Utiliza o método GET, Lista as plantações que estão com o campo `HarvestDate` entre as datas especificadas. Necessário token de autorização para efetuar a operação.
- Em caso de sucesso, retorna o status http `200` com uma lista de objetos contendo os dados das plantações.
- Caso o usuário não possua a autorização necessária ou caso a mesma esteja inválida, retorna uma mensagem de erro com o status http `403`.

```html
/crops/{cropId}/fertilizers
```
- Utiliza o método GET, Lista os fertilizantes da plantação indicada pelo id. Necessário token de autorização para efetuar a operação.
- Em caso de sucesso, retorna o status http `200` com uma lista de objetos contendo os dados dos fertilizantes da plantação indicada.
- Caso o usuário não possua a autorização necessária ou caso a mesma esteja inválida, retorna uma mensagem de erro com o status http `403`.
- Se não for encontrado uma plantação com o id especificado, retorna uma mensagem de erro com um status http `404`.

```html
/crops/{cropId}/fertilizers/{fertilizerId} 
```
- Utiliza o método POST, Associa um fertilizante à plantação, indicados pelo respectivos ids. Necessário token de autorização para efetuar a operação.
- Em caso de sucesso, retorna o status http `201` junto de uma mensagem de sucesso.
- Caso o usuário não possua a autorização necessária ou caso a mesma esteja inválida, retorna uma mensagem de erro com o status http `403`.
- Se não for encontrado uma plantação e/ou fertilizante com os ids especificados, retorna uma mensagem de erro com um status http `404`.

### Fertilizer

```html
/fertilizers
```
- Utilizando o método GET:
  - Retorna uma lista de fertilizantes presentes no banco de dados. Necessário token de autorização para efetuar a operação e usuário com role ADMIN.
  - Em caso de sucesso, retorna o status http `200` junto de uma lista de objetos contendo as informações dos fertilizantes.
  - Caso o usuário não possua a autorização necessária ou caso a mesma esteja inválida, retorna uma mensagem de erro com o status http `403`.

- Utilizando o método POST:
  - Adiciona um fertilizante ao banco de dados. Necessário token de autorização. Necessário token de autorização para efetuar a operação.
  - Em caso de sucesso, retorna o status http `201` e um objeto contendo as informações do fertilizante criado.
  - Caso o usuário não possua a autorização necessária ou caso a mesma esteja inválida, retorna uma mensagem de erro com o status http `403`.
  - O corpo da requisição deve obedecer o seguinte formato:

    ```json 
        {
          "name": "string",
          "brand": "string",
          "composition": "string"
        }
    ```

```html
/fertilizers/{id}
```
- Utiliza o método GET, Retorna o fertilizante especificado pelo id (caso exista). Necessário token de autorização para efetuar a operação.
- Em caso de sucesso, retorna o status http `200` e um objeto contendo os dados do fertilizante presente no banco de dados.
- Caso o usuário não possua a autorização necessária ou caso a mesma esteja inválida, retorna uma mensagem de erro com o status http `403`.
- Se não for encontrado um fertilizante com o id especificado, retorna uma mensagem de erro com um status http `404`.

## 👾Autor

 <a href="https://github.com/Gui-lfm">
 <img style="border-radius: 50%;" src="https://avatars.githubusercontent.com/u/72154970?v=4" width="100px;" alt=""/>
 <br />
 <sub><b>Guilherme Lucena</b></sub></a>

### ✉contato

<div>
  <a href="mailto:guilherme.lucena17@gmail.com" target="_blank"><img src="https://img.shields.io/badge/Gmail-D14836?style=for-the-badge&logo=gmail&logoColor=white" target="_blank"/></a>
  <a href="https://www.linkedin.com/in/gui-lucena/" target="_blank"><img src="https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white" target="_blank"/></a>
</div>
