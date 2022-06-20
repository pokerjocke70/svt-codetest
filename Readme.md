# Swapi kodprov

## KODTEST 

Din uppgift är att ta fram en körbar applikation som exponerar en REST endpoint som returnerar de 10 dyraste rymdskeppen baserat på data från detta API: (https://swapi.dev/) 
Svaret skall vara sorterat utifrån det dyraste skeppet först. 
Applikationen ska kunna köras lokalt och innehålla information om hur den körs. 

Uppgiften löses med fördel med Kotlin (men Java är ok också) i enlighet med “best practices” men i övrigt med valfri teknik och arkitektur. 
Kodtestet behöver inte vara perfekt och förväntas ta några timmar att utföra. 

Du förväntas kunna motivera dina lösningar.


## Running the application


```bash
java -jar build/libs/codetest-0.0.1-SNAPSHOT.jar
```

In ide

Run class org.sundqvist.svt.codetest.SwapiApplication


or with gradle

```bash
./gradlew bootRun
```

## Api 

```bash
curl -s localhost:8081/swapi/starships |jq . 
```

## TODO 

- Document api with OAS
- Some logging
- Exception handling
- cache
