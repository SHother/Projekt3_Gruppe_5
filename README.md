# Administrationssystem - Bilabonnement A/S (Gruppe 5)

Dette repository indeholder administrationssystemet til Bilabonnement A/S, udviklet som et 2. semesters eksamensprojekt på datamatikeruddannelsen ved Erhvervsakademi København (EK). 
Systemet er opbygget som en webapplikation.
---

## Deployment og Demo

Applikationen er deployet live i skyen på Microsoft Azure og kan tilgås via nedenstående link:

* **Live URL:** https://bilbasenwebapp.azurewebsites.net/

---

## Softwaremæssige forudsætninger

For at kunne downloade, opsætte og afvikle applikationen lokalt på en maskine, kræves følgende software:

1. **Java Development Kit (JDK 21):** Projektet er bygget og kompileret med Java 21.
2. **IDE (Udviklingsmiljø):** En Java-understøttet IDE, såsom IntelliJ IDEA eller Visual Studio Code (med Java Extension Pack).
3. **Database:** En lokal installation af MySQL Server (f.eks. via MySQL Workbench) eller adgang til en ekstern MySQL-database.
4. **Byggeværktøj:** Apache Maven (oftest indbygget i IDE'en).

---

## Installations- og opsætningsvejledning (Lokal kørsel)

Følg disse trin for at downloade projektet og køre det lokalt:

### Trin 1: Download og udpak projektet
1. Gå til projektets GitHub repository: https://github.com/SHother/Projekt3_Gruppe_5
2. Klik på den grønne "Code"-knap i højre hjørne og vælg "Download ZIP".
3. Find den downloadede ZIP-fil på din computer og udpak den i en valgfri mappe.

### Trin 2: Åbn projektet i din IDE
1. Åbn IntelliJ IDEA (eller tilsvarende IDE).
2. Vælg "Open" (eller Import Project) og naviger til den udpakkede mappe. Vælg rodmappen (hvor pom.xml filen ligger), og tryk "OK".
3. Vent mens din IDE indekserer koden og downloader de nødvendige dependencies.

### Trin 3: Opret databasen lokalt (DB-delen)
Før applikationen kan starte, skal databasestrukturen oprettes i din MySQL-server.
1. Åbn dit databaseværktøj (f.eks. MySQL Workbench) og log ind på din lokale MySQL-server.
2. Find SQL-scriptet `schema.sql` under stien: `src/main/resources/schema.sql` i projektmappen.
3. Kopier indholdet af scriptet og eksekver det i dit databaseværktøj. Dette opretter automatisk databasen og tabellerne. *(Bemærk: Har projektet en `data.sql` fil, køres denne umiddelbart efter for at populere testdata).*

### Trin 4: Konfigurer application.properties (Spring-delen)
Applikationen skal vide, hvordan den forbinder til din lokale MySQL-database.
1. Naviger til og åbn filen: `src/main/resources/application.properties`
2. Opdater følgende linjer, så de matcher dine egne lokale database-oplysninger:

```properties
# Database URL - Ret "localhost:3306/bilabonnement" hvis din port eller databasenavn afviger
spring.datasource.url=jdbc:mysql://localhost:3306/bilabonnement?serverTimezone=UTC

# Dit lokale MySQL brugernavn (ofte "root")
spring.datasource.username=DIT_LOCAL_MYSQL_BRUGERNAVN

# Dit lokale MySQL password
spring.datasource.password=DIT_LOCAL_MYSQL_KODEORD

# Spring driver konfiguration
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

```

### Trin 5:Start og afvikling af applikationen
Når databasen er oprettet, og filen application.properties er konfigureret korrekt, kan applikationen afvikles:

Find hovedklassen Projekt3Gruppe5Application.java under stien:
src/main/java/org/example/projekt3_gruppe_5/Projekt3Gruppe5Application.java

Kør klassen via din IDE

Hold øje med terminalen. Når systemet skriver "Hello World!", kører applikationen.

Åbn en browser og indtast følgende adresse for at tilgå systemet:
http://localhost:8080
