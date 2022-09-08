# Back to School: Tennis
*Een opdracht die probeert zoveel mogelijk 2dejaars kennis weer op te frissen.*

## Inleiding

Hopelijk is iedereen een beetje bijgekomen van de zomervakantie, dus het is tijd om weer even de programmeervingers wakker te maken.

Als warming-up doen we een zogeheten [Code Kata](https://en.wikipedia.org/wiki/Kata#Outside_martial_arts), een programmeeropdracht
niet zozeer omdat we het eindresultaat nodig hebben, maar omdat het een boel onderdelen van onze kennis raakt.

In deze *Kata* gaan we via een RESTful webservice een potje [Tennis](https://en.wikipedia.org/wiki/Tennis_scoring_system) spelen. 
Tennis is waarschijnlijk redelijk bekend bij iedereen, maar heeft net genoeg rariteiten om 'een beetje irritant' te zijn om
uit te programmeren.

Het is de bedoeling dat je deze opdracht individueel maakt. Uiteraard mag je met medestudenten overleggen, maar we willen vooral dat 
je zelf even die programmeerskills weer wakker schudt. Je kunt iemand anders ook niet voor je laten warm-lopen!

*LET OP: Een helemaal prachtige, feature-complete Tennis-API bouwen is daaaagen werk. Dat is **niet** de bedoeling bij deze opdracht*

## Budget

Voor deze opdracht heb je 'de middagen van de eerste week' (dus 6-12 uur). Probeer slim te kiezen welke functionaliteit je in deze periode kan realiseren. Misschien heb je de eerste woensdag of vrijdag ook nog een paar uurtjes, dat verschilt een beetje per project.

Afhankelijk van je INNO project heb je in de rest van sprint 1 nog een aantal middagen om op deze code vervolgwerkzaamheden te verrichten.
Daar zijn namelijk oefeningen gepland die je idealiter op je INNO project kan uitvoeren, maar zo niet dus met deze codebase kan uitvoeren.

Let er op dat je zelf ook niet over je eigen budget heen gaat. Het INNO-semester is een marathon, en geen sprint.

## Opdracht

Deze startrepository gebruikt een Spring-Boot starter. Als je echt heel graag in een andere programmeertaal of met een ander
framework werkt mag dat natuurlijk ook. Gebruik idealiter deze opdracht alleen niet om een nieuw framework te leren, daar is in het budget
bij deze opdracht geen ruimte voor overgehouden.

Het enige dat er bovenop de starter is gedaan is het toevoegen van de H2 database. Dit is een zogeheten 'in-memory' database die elke keer dat je de applicatie start helemaal blanco begint. Je kunt 'm verder gewoon als normale relationele database gebruiken, en je kunt zodra de applicatie draait de database admin-console benaderen op http://localhost:8080/h2-console. Als het goed is kun je met de standaard ingevulde settings direct connecten, maar anders moet je even spieken in de application.properties.

#### Must-have:

* Spelers kunnen beheren via een REST-API
* Met die spelers een potje tennis kunnen starten via een REST-API
* Per deelnemer aan kunnen geven dat die speler een punt heeft gemaakt via een REST-API.
(vergeet hierbij de gekke Tennispunten van 0,15,30,40 en [Deuce](https://en.wikipedia.org/wiki/Tennis_scoring_system) niet)

En uiteraard op 'de redelijke plekken' ook de informatie kunnen opvragen.

#### Should-have:

* Persistentie in een database
* Nette errorhandling en status-codes
* Unit-tests voor je domein-logica
* Enkele integratie-tests voor je controller- en persistentie-logica

#### Could-have:

* Mooie HATEOAS
* Sets & Matches (incl. Tie-breaks) kunnen afhandelen
* Mooie dummydata bij de eerste keer opstarten
* Security op de POST requests
* Minimale frontend-code

#### Wont-have

* Externe database zoals Postgres. We gebruiken voor deze opdracht gewoon even H2, dat werkt 'lekker makkelijk' voor je docenten
* Overige externe services. Code uitchecken en dan moet ie het gewoon direct doen.

## Evaluatie

Waar letten we zoal op? (voor het 'waarom' is een klein commentje in de code voldoende)

* Welke features heb je geprioriseerd, en waarom?
* Wat voor urls heb je gekozen? en waarom? 
* Hoe ziet je H2 database schema er uit? en waarom?
* Is je code een beetje netjes? Waarom wel? Of juist niet?

## Inleveren

Lever op de inleverbox voor de 'jaar 2 vaardigheden' in je project een link naar je repository.