# Java Aop
![License](https://img.shields.io/github/license/oTalDoHud/ProjetoDashBoardVendas)

## O que é AOP?
AOP (Aspect-Oriented Programming) é um paradigma de programação 
que permite separar preocupações transversais ao código principal,
como logging, validação, segurança, em módulos independentes 
(aspects). Isso promove modularidade, reusabilidade e facilita 
a manutenção do código. Neste repositório, exploramos exemplos 
práticos de AOP em diferentes linguagens.

### Curl dos endpoints
Busca todos os Users sem disparar o monitoramento.

```bash
curl --request GET \
--url http://localhost:8080/api/users
```
Criamos o User e monitoramos o tempo de execução, local (class/method) e argumentos que foram recebidos.
```bash
curl --request POST \
--url http://localhost:8080/api/user \
--header 'Content-Type: application/json' \
--data '{
    "id": 1,
    "firstName": "Hudon",
    "lastName": "Vieira",
    "age": 22
}'
```

## Quando utilizar?
O AOP é útil quando você precisa separar preocupações transversais
(como logging, autenticação, caching) do fluxo principal do código
. Ele facilita a reutilização e a manutenção, pois permite aplicar
essas preocupações em várias partes do código de forma centralizada.
É benéfico quando a lógica de negócio se torna poluída com essas
preocupações, dificultando a compreensão e a evolução do sistema.
Além disso, o AOP é valioso em situações em que a equipe deseja
adicionar ou modificar comportamentos sem alterar o código
existente, melhorando a escalabilidade do projeto.

## Retorno dos logs
Logs organizados como explicado acima.

![Modelo_relacional](https://github.com/oTalDoHud/java_aop/blob/master/assets/log.png)

# Tecnologias utilizadas
- Java
- Spring Boot
- Aspect


## Autor
Hudson Lucas Teles Vieira

www.linkedin.com/in/otaldohud

hudson.lucas.vieira@gmail.com


