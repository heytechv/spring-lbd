### Pyt 5.
Zawartość skompilowanego pliku rozszerzona o `BOOT-INF`

### Pyt 9.
Nie zadziala. Wystepuje blad: <br/>
`Field employeeService in com.fisproject.springlbd.SpringLbdApplication required a single bean, but 2 were found:`<br/>
Inforumjacy o tym, ze zostalo znalezione wiele implementacji interfejsu.
Rozwiazac problem mozna poprzez dodanie @Primary do jednej z klas.
<br/>LUB/I<br/>
Nazywajac poszczegolne komponenty `@Service("nazwa")` oraz przy tworzeniu uzyc dyrektywy `@Qualifier("nazwa")`.

### Pyt 12.
Po wybraniu wlasciwego profilu w `application.properties` (`spring.profiles.active=dev`),
test konczy pomyslnie (wlasciwa implementacja klasy jest uruchamiana)

### Pyt 14.
Wyswietlanie logow w trybie DEBUG:<br/>
`logging.level.org.springframework=DEBUG`<br/>
Wyswietla dodatkowe informacje m.in. szczeogoly odnosnie uzytego autowire, stworzonych instancji,
jakie endpointy sa wystawiane jako REST API, raport auto konfiguratora

### Pyt 15.
Format daty dla logow: <br/>
`logging.pattern.dateformat=dd/MM HH:mm`

### Pyt 20.
Poziom logowania ***DEBUG*** dla autokonfiguracji: <br/>
`logging.level.org.springframework.boot.autoconfigure.logging=DEBUG`
<br/>
Raport auto-konfiguracji wyswietla informacje o klasach, ktore zostaly znalezione i skonfigurowane przez SpringBoot.
<b/>
np. ktore z endpointow Actuatora zostaly oznaczone w konfigu i beda udostepniane na zewnatrz.



### Pyt 22.
Odpowiedz w osobnym pliku, przeslanym mailem.

### Pyt 23.
Logi podczas odwiedzania endpointow (np. /actuator/health) pokazuja sie w trybie DEBUG.
