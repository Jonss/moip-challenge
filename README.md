# moip-challenge

Onde fazer download do projeto?
----------

Acesse http://bit.ly/1MIsbvY , clique em ```View Raw`` e baixe o .jar.

Como executar?
-------------

Via terminal, acesse o diretório onde o .jar está. Execute o comando:

```
java -jar moip-log.jar 
```

Será solicitado o nome do arquivo. Caso não esteja no mesmo diretório, só faça a referência completa.
Ex.:

```
meuDiretorio\xpto\lambda-lambda-lambda\log.txt
```


#Linha de raciocínio

Precisei retirar das linhas as urls acessadas e os status responses, então criei dois ArrayLists de String para cada um. Após isso inseri os valores com as quantidades usando um Map. Adiciono os valores (```url - quantidade``` e ``` status response - quantidade ```). Passo isso para uma lista.

Faço a impressão usando um foreach da API do Java 8.


#Testes

Testam o padrão da url e verificam os retornos baseados em um arquivo menor, apenas para teste.

 






 
