Create file hibernate.cfg.xml to create connection with database. 
Content file .xml:
<?xml version='1.0' encoding='utf-8'?>
<!DOCTYPE hibernate-configuration PUBLIC
        "-//Hibernate/Hibernate Configuration DTD//EN"
        "http://www.hibernate.org/dtd/hibernate-configuration-3.0.dtd">
<hibernate-configuration>
    <session-factory>
        <property name="connection.url"> <!--hibernate_students = nazwa bazy danych -->
            <!--początek adresu URL zaczyna się znakiem zapytania -->
            jdbc:mysql://localhost:3306/ArtifactIdOfProject?serverTimezone=Europe/Warsaw&amp;createDatabaseIfNotExist=true
            <!--createDatabaseIfNotExist=true = parametr powoduje swtworzenie bazy danych -->
            <!--oddzielenie kolejnych parametrów znakiem &amp; -->
        </property>
        <property name="connection.driver_class">com.mysql.cj.jdbc.Driver</property>
        <property name="hibernate.dialect">org.hibernate.dialect.MySQL5Dialect</property>
        <!-- powyższa linia służy do wskazania hibernatowi  sterownika bazy danych-->
        <property name="connection.username">username</property>
        <property name="connection.password">password</property>
        <!--DB schema will be updated if needed -->
        <!--Hibernate model to data definition language-->
        <!--create - jeĹ›li ustawiony czyĹ›ci bazÄ™ danych przed uruchomieniem i od nowa tworzy tabele-->
        <!--update - jeĹ›li ustawiony dopisuje brakujÄ…ce elementy/tabele/kolumny do bazy -->
        <!--create-drop - uruchom connector, stwĂłrz tabele i kolumny, a po zakoĹ„czeniu aplikacji dropuj wszystkiego -->
        <!--validate - weryfikuje poprawnoĹ›Ä‡ bazy -->
        <!--ustawienie definiuje czy hibernate ma sam stworzyć TABELE-->
        <property name="hbm2ddl.auto">update</property> <!--jesli nie chcę stracić danych to ustawiamy na update -->
        <property name="show_sql">true</property>

        <!--dopisnaie klas obsługiwanych przez hibernate-->

    </session-factory>
</hibernate-configuration>