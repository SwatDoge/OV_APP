# Opbouw applicatie

De applicatie is opgezet op basis van het MVC model. Dit betekend dat iedere pagina in de applicatie een model, view en controller heeft.
De `view` is het uiterlijk van de applicatie waarin stijl, layout en text in opgeslagen word. Dit word verwerkt als de initieele "staat" van de UI.


De `controllers` zijn verantwoordelijk voor de huidige staat van een pagina. Je kan met een controller programatisch de UI aanpassen en inlezen. Je gebruikt de controller om de UI te updaten tijdens het draaien van de applicatie.


In de `model` package staan models die de structuur van onze data beslissen. Voor onze database gebruiken we bijvoorbeeld een model om ons JSON databestand te kunnen lezen en schrijven in Java.


`Main` is het begin van de applicatie die de `Home` view en controller in de scene laad. De home controller heeft navigatie elementen die via de `Page` middleware naar andere scenes wisselt. `Users` is een singleton waaruit je `User` classes kunt ophalen om te gebruiken in je code. Als je bijvoorbeeld wilt weten of iemand is ingelogd kan je de volgende code gebruiken:
```java
import com.example.ovapp.Users;

Users.getInstance().isSomeUserLoggedIn(); //boolean
```

Er is hier voor een singleton gekozen omdat deze data door de hele applicatie beschikbaar moet zijn. De vulkuilen die bij singletons horen zijn nog niet van toepassing bij dit project. (Bijv. Onveiligheid bij meerdere threads)

`Request` wordt gebruikt om de NS Api te fetchen en om te zetten naar een model. Deze kan vervolgens in de SearchResultController worden weergeven.

# Resources
Icoontjes, stylesheets en databestanden kunnen gevonden worden in de `resource` map. Dit zijn statische bestanden die niet veranderen binnen onze applicatie.

# Instalatie

Vanwegen een deprecated JavaFX library die niet bij Java 21 past kan deze applicatie niet tot een Jar gebuild worden. Voor nu word dit project kan ingeladen worden in `Intellij`, waarna deze software vanzelf meeste dependencies inleest.