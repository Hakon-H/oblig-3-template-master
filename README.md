# Obligatorisk oppgave 3 i Algoritmer og Datastrukturer

Denne oppgaven er en innlevering i Algoritmer og Datastrukturer. 
Oppgaven er levert av følgende student:
* Håkon Havn, S344194, s344194@oslomet.no


# Oppgavebeskrivelse

I oppgave 1 så gikk jeg frem ved å sjekke koden i kompendiet som ble nevnt i oppgaveteksten. Koden virker ved å sjekke for hver node om verdien du skal legge inn er større eller mindre enn verdien i noden du sjekker. Men fordi i oppgaven så har nodene også en "forelder" måtte jeg modifisere koden litt. Heldigvis var node klassen sin konstruktør allerede laget med en forelder, så det eneste jeg trengte å gjøre var å endre p = new Node<>(verdi) til p = new Node<>(verdi, q) der q er forelderen til den nye p noden.

I oppgave 2 så brukte jeg rekursjon for å finne antall forekomster av en verdi. For å gjøre dette bruker jeg en hjelpevariabel som er definert utenfor metodene (slik at den kan brukes i begge). Metoden med rekursjon tar inn to parametere, som er noden og verdien den skal sjekkes for. Først sjekker jeg basistilfellet som vil være om noden er null. Dersom den ikek er det sjekker jeg om verdien i noden er lik verdien jeg skal finne, og hvis den er det øker jeg hjelpevariabelen jeg definerte tidligere med en. Deretter kaller jeg på metoden igjen (rekursjon) men endrer node parameteren til å ta inn venstre og høyre barn til noden. Når denne blir ferdig vil hjelpevariabelen kunne returneres med antall forekomster av verdien i treet. I metoden som kalles først (den som brukes i testen, og til å starte rekursjonen) setter jeg først hjelpevariabelen til 0, slik at variabelen vil bli satt til 0 hver gang jeg skal sjekke ett nytt tall.

I oppgave 3 skulle jeg finne den første og andre noden som blir funnet i post-orden. Det betyr at jeg skal returnere nodene som først er "ferdig" (at alle barnene dens er sjekket). Fordi alle barnene må være sjekket før en node blir ansett som "ferdig" i post-orden, kan jeg lett finne den første noden ved å finne den første noden uten barn. Vi begynner med å se til venstre, og om det finnes ett venstre barn går vi til det barnet. Hvis det ikke finnes et venstre barn sjekker vi høyre (og går til det dersom det finnes). Etterhvert vil vi komme til en node uten barn, og denne noden vil være den først i post-orden. For å finne den andre noden må vi først gjøre et par sjekker, men etter de sjekkene kaller jeg på førstePostorden metoden igjen. Jeg må først sjekke om noden sin forelder er null (hvis den er null betyr det at denne noden er roten, og ikke har noen neste node i post-orden. Deretter sjekker jeg om forelder noden sitt høyre barn enter er noden jeg er på, eller om den er null. Hvis en av disse to er sanne betyr det at den neste noden i post orden vil være forelder noden. Men når disse sjekkene er ferdig kan jeg kalle på førstePostorden metoden igjen, men siden jeg nå ikke begynner på rot noden, vil jeg finne den første noden i post orden etter den første, altså den andre noden.

Oppgave 4 har jeg ikke klart å løse. Jeg forstår generelt sett hvordan jeg ville ha kodet det, men skjønner ikke hvordan jeg skal bruke "oppgave" eller returnere stringen.

