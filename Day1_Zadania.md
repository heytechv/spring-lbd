### Pyt. 5.
Zawartość skompilowanego pliku rozszerzona o `BOOT-INF`

### Pyt. 9.
Nie zadziala. Wystepuje blad: <br/>
`Field employeeService in com.fisproject.springlbd.SpringLbdApplication required a single bean, but 2 were found:`<br/>
Inforumjacy o tym, ze zostalo znalezione wiele implementacji interfejsu.
Rozwiazac problem mozna poprzez dodanie @Primary do jednej z klas.
<br/>LUB/I<br/>
Nazywajac poszczegolne komponenty `@Service("nazwa")` oraz przy tworzeniu uzyc dyrektywy `@Qualifier("nazwa")`.


