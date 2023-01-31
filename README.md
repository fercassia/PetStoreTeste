# PetStoreTeste
Testes automatizados utilizando o framework RESTAssurenced com Java 11.

API utilizada para a realização dos testes <a href="https://petstore.swagger.io/" target="_blank">Petstore</a>.

Os métodos utilizados na automatização foram: GET, POST e PUT.

Os principais cenários pra a automatização foram:

    •    Cadastrar novo pedido de pet com sucesso (POST /store/order)
    •    Pesquisar por um pet inexistente (GET /pet/{petId})
    •    Atualizar dados de um pet existente (PUT /pet)
    •    Pesquisar por pets com status “pending” (GET /pet/findByStatus)
    
* Para verificar os testes, entre na pasta: PetStoreTeste/PetStoreTeste/src/test/java/

* Para verificar as dependências utilizadas, basta abrir o arquivo pom.xml, que está neste caminho: PetStoreTeste/PetStoreTeste/

* Para visualizar o resultado dos testes que passaram, vá para a pasta: PetStoreTeste/resultado-testes-petstore/


