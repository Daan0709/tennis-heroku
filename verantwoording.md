# Verantwoording

## Features

De features die ik geprioriseerd heb zijn:
 - Een set is gewonnen door de eerste speler die 6 games wint (met een verschil van 2)
 - Een speler wint een match wanneer hij 2 sets heeft gewonnen (bo3)
 - Het scoresysteem werkt precies zoals bij een echt potje tennis, met 0, 15, 30, 40, duece en advantage
 - Bij het opstarten van de applicatie worden er alvast 4 spelers aangemaakt, zodat ik snel een game kan beginnen

## URLs
De URLs die ik heb gekozen beginnen in ieder geval met '/tennis'. Dit geeft duidelijk aan om wat voor applicatie het gaat.
Daarnaast heb ik 2 endpoints:
- /match
- /player

Deze zorgen respectievelijk voor het spelen en aanmaken van een wedstrijd, en voor het managen van de spelers.

## Database
De database heeft slechts 2 tabellen: player en match. Een match heeft een OneToMany relatie naar player, en er zitten dan ook 2 players in een match. 
Dit zorgt voor een makkelijk overzicht over welke spelers er precies in welke match spelen.

Ook wordt er per speler bijgehouden hoeveel matches deze in totaal heeft gewonnen. Dit leek mij zelf wel een interessante feature om te hebben.

## Code Netheid
De code die ik heb geschreven is over het algemeen vrij netjes. Functies hebben duidelijke namen, het packagesysteem is goed in orde, er worden duidelijke (custom) exceptions gegeven wanneer er iets fout gaat en de code zelf is niet verschikkelijk ingewikkeld.
Wel zou ik wat meer commentaar tussen de code hebben kunnen neerzetten, zodat alles goed opgepakt zou kunnen worden door andere developers en, met wat meer tijd, had ik mijn code wat kunnen optimaliseren. 
Een functie in MatchService (increaseScore()) is nu bijvoorbeeld een erg lange functie wat ik had kunnen opsplitsen in meerdere kleinere functies om duidelijkheid te vergroten. 

