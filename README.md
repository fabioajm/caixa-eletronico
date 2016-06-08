# Project: Caixa Eletrônico
Projeto experimental utilizando microservices, spring boot, angular e gulp.

# ce-ui :8080
 Projeto front-end, contém o automatizador de tarefas Gulp, e as telas do sistema.
 Esse projeto utiliza spring-cloud-starter-zuul para redirecionar as requisições
 
# ce-crud :8082
 Projeto backend, contém o cadastro dos clientes.
 
# ce-core :8081
 Projeto backend, contém o caixa eletrônico que processa o saque dos clientes.
 
 
#Run project

'''git clone https://github.com/fabioajm/caixa-eletronico.git

cd caixa-eletronico
cd ce-core
start mvn spring-boot:run

cd ..\ce-core
start mvn spring-boot:run

cd ..\ce-crud
mvn spring-boot:run

acesse http://localhost:8080