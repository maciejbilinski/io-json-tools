# io-json-tools
## Opis
Projekt z przedmiotu Inżynieria Oprogramowania. Celem projektu jest stworzenie narzędzi do plików JSON.

## Backlog
[GDrive](https://docs.google.com/spreadsheets/d/1BYmtHcCASf_tXP3-bAaPyoInwj0TD9TU/edit?usp=sharing&ouid=117444929924556355691&rtpof=true&sd=true)

## Jak nazywać Branch?
feature/nrzadania_nazwa_zadania

Branchujemy z developa zawsze.

Po zatwierdzeniu pull requesta squashujemy brancha (żeby na developie pojawiała się tylko jedna kropka)

## Getting started (dla [InteliJ IDEA](https://www.jetbrains.com/idea/))
0. _Klonujemy repo (branch develop) na swój komputer_
1. Otwieramy [InteliJ IDEA](https://www.jetbrains.com/idea/)
2. Klikamy **Open**
3. Wybieramy folder ze sklonowanym projektem
4. Klikamy **Open**
5. Powinien nam się stworzyć folder **.idea** i w dolnym panelu **Build** powinien się wykonać proces **Sync**
6. Następnie przechodzimy do konfiguracji budowania - w górnym panelu obok ikony **młotka** jest pokazana aktualna konfiguracja (*prawdopodobnie **Current File***).
7. Klikamy w nią i wybieramy **Edit configurations…**
8. Klikamy „**Add new run configuration…**”
9. Z listy wybieramy „**Application**”
10. Wpisujemy dowolną nazwę np. "run"
11. W zakładce „**Build and run**” wybieramy **java 11** ([Pobierz Java 11](https://www.oracle.com/pl/java/technologies/javase/jdk11-archive-downloads.html))
12. W polu **Main class** wpisujemy „*pl.put.poznan.transformer.app.JSONToolsApplication*”, **nie przejmując się czerwonym kolorem**
13. Klikamy **Apply** i klikamy **OK**
14. Klikamy **zielony trójkąt** obok **nazwy konfiguracji** (*w moim przypadku **run***)
15. Ostatnim logiem (logi są w dolnym panelu w zakładce **Build**) powinno być coś w stylu „_Started JSONToolsApplication in 7.189 seconds (JVM running for 8.082)_”
16. Otwieramy przeglądarkę
17. Wpisujemy w polu adresu URL [http://localhost:8080/test](http://localhost:8080/test) i powinna nam się wyświetlić strona z napisem „**TEST**”

## Autorzy
- Maciej Biliński
- Mikołaj Bartkowiak
- Kinga Cołta [**Proxy Product Owner**]
- Piotr Więtczak [**Scrum Master**]
