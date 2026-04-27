# CRUD simples de uma tabela de produtos
É meu primeiro projeto GITHUB, é algo simples e nem dá tanto trabalho. Desenvolvo via celular, então é mais difícil, mas não deu tanto trabalho

## Ambiente
* **Linguagem:** Java
* **Editor:** Qualquer IDE que suporte Java
* **Banco de Dados(DB):** PostgreSQL (JDBC)

## Requisitos
* **JDK** - _versão 17+_
* **PostgreSQL** - _18.2+_
* **Driver JDBC** - _arquivo .jar_

## Instalação
### Windows
**Download:** Vá ao site oficial do PostgreSQL e baixe o instalador da EDB. Execute o arquivo ".exe" 
**Durante download:** Durante o download, certifique-se de que o PostgreSQL Server e o pgAdmin 4 (ferramenta visual para ver o banco) estejam marcados.
**Senha:** O executável pedirá que crie uma senha _GUARDE ELA!_ Ela será necessária para a criação do Banco de dados.
**Porta:** Deixe como está.
**Pós-download:** Após instalar, pesquise por "Variáveis de Ambiente" no Windows e adicione o caminho da pasta bin ao seu Path. Isso permite usar o comando psql no CMD.

### Linux
**Atualixação:** No bash do terminal, use o comando _sudo apt update_.
**Download:** Agora digite : _sudo apt install postgresql_ dê enter e agora digite: _postgresql-contrib_.
**Verificação:** No bash: _sudo systemctl status_. Depois o comando _postgresql_.
**Finalização:** Agora o sistema operacional vai criar um usuário no sistema chamado "postgres". Se quiser entrar no banco use: _sudo -i -u postgres psql_.

### Mobile
**Requisitos:** Instale o Termux pela F-droid, depois o Acode, também pela F-droid.
**Atualização:** Entre no Termux, digite _termux-setup-storage_ e _pkg update && pkg upgrade -y_.
**Download:** Instale o Java e o PostgreSQL.

## Como usar
Cole o código na sua IDE, e execute, não se esqueça de quando rodar usar também o PostgreSQL, depois é só rodar. Troque a variável "user" pelo seu nome no banco de dados, e a sua senha também. Troque a última parte da url "/teste" por "/" e o nome do seu banco de dados.

## Comentários
> Se tiver qualquer dúvida, copie o código e pergunte à uma IA de confiança.
> Sinta-se livre para modificar o código!