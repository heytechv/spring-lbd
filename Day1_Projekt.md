### KlientService
Map< ID_kienta:Long, Klient >
- 1,Klient
  - kraj
  - miasto
  - ArrayList< ID_projektu:Long >
    - {123}

### ProjektService
Map< ID_projektu:Long, Projekt >
- 123,Projekt
  - Faktura
    - termin_platnosci
    - status
  - Map< Rola, ArrayList< ID_pracownika:Long > >
    - PROGRAMISTA,{0, 2, 3}
    - ANALITYK, {3, 4, 5}
  - ArrayList < ID_sprint:Long >
    - {21}

### PracownikService
Map< ID_Pracownika:Long, Pracownik >
- 3,Pracownik
  - imie
    - nazwisko
    - Oddzial
      - Gliwice
    - Team
      - JAVA

### SprintService
Map< ID_sprint:Long, Sprint >
- 21,Sprint
  - okres
  - ArrayList < User_stories:String >

