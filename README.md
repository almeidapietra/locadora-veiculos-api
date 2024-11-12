## Configuração do PostgreSQL

Inclusão do arquivo `pg_hba.conf` com configurações de autenticação recomendadas para o PostgreSQL.


### Como Usar

1. Copie o arquivo `pg_hba.conf` para o diretório de configuração do PostgreSQL:




# TYPE  DATABASE        USER            ADDRESS                 METHOD
# "local" is for Unix domain socket connections only
local   all             all                                     trust
# IPv4 local connections:
host    all             all             0.0.0.0/0               md5
# IPv6 local connections:
host    all             all             ::1/128                 md5
# Allow replication connections from localhost, by a user with the
# replication privilege.
local   replication     all                                     trust
host    replication     all             127.0.0.1/32            trust
host    replication     all             ::1/128                 trust

