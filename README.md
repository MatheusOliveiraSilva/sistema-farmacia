Pharmacy:

Como fazer o projeto funcionar localmente:

pré-requisitos:

MySQL com um banco chamado pharmacy2

Java 8.

TomCat 7.0

--------------------------------------

Criar um banco de dados chamado pharmacy2

Alterar o hibernate.cfg.xml dentro da pasta src para os dados do seu ecossistema (exemplo abaixo)
-----------------------------------------------------------------------------------------
<hibernate-configuration>
  <session-factory>
    <property name="hibernate.connection.driver_class">com.mysql.jdbc.Driver</property>
    <property name="hibernate.connection.url">jdbc:mysql://localhost/pharmacy2</property>
    <property name="hibernate.connection.username">SeuUsuarioConexaoBD</property>
    <property name="hibernate.connection.password">SuaSenhaConexaoBD</property>
    <property name="hibernate.dialect">org.hibernate.dialect.MySQL5InnoDBDialect</property>
    <property name="hbm2ddl.auto">update</property>
</hibernate-configuration>
-----------------------------------------------------------------------------------------

Rodar o arquivo CreateTable que fica no pacote main  como um JavaApplication
para criar as tabelas do banco de dados.

E executar o projeto no TomCat e logar com o usuario padrao abaixo.
---------------------------------------------------------
cpf:   11111111111
senha: 12345678


Link do sistema: http://syspharmacy.azurewebsites.net/
