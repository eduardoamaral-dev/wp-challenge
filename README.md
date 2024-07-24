## Como rodar:

 - Garanta que sua máquina possua Java 17 e Node instalados
 - No repositório da API, execute o comando "./gradlew run"
 - No repositório do frontend, execute o comando "npm i", e logo após o comando "npm run start"

## Swagger
Para visualizar o swagger, rode a api e acesse a ulr "[http://localhost:8080/swagger-ui/index.html#/pet-controller/getAllPets](http://localhost:8080/swagger-ui/index.html)"

## Observações
 - O campo "age" é uma string para armazenar valores como "2 anos e 3 meses" após o cálculo da data de nascimento
 - Inicialmente pensei em salvar as imagens em um Blob no banco de dados, porém os requisitos dizem para salvar a URL, então utilizei um tamanho de varchar que comportasse as url's que testei
