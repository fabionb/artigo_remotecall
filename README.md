# Chamadas remotas em contexto transacional de banco de dados
Este repositório foi utilizado para a escrita do artigo sobre como chamadas remotas em contexto transacional de banco de dados impactam o tempo de resposta / escalabilidade da aplicação.

## Requisitos
Rodar um Oracle com o seguinte comando:
```
docker run -d --name oracle --network host -e ORACLE_ALLOW_REMOTE=true oracleinanutshell/oracle-xe-11g
```

## Execução
Para rodar o código, executar o seguinte comando:
```
./gradlew bootRun
```

## URL's
Utilizar as seguintes URL's para verificar o comportamento dependendo de onde o contexto transacional do banco é aberto / fechado:

http://localhost:8081/testTransactionalOutside/3000

http://localhost:8081/testTransactionalCorrect/3000
