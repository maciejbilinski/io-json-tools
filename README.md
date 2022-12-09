develop: [![Build](https://github.com/maciejbilinski/io-json-tools/actions/workflows/maven_build.yml/badge.svg?branch=develop)](https://github.com/maciejbilinski/io-json-tools/actions/workflows/maven_build.yml) [![Tests](https://github.com/maciejbilinski/io-json-tools/actions/workflows/maven_feature.yml/badge.svg?branch=develop)](https://github.com/maciejbilinski/io-json-tools/actions/workflows/maven_feature.yml)
main: [![Build](https://github.com/maciejbilinski/io-json-tools/actions/workflows/maven_build.yml/badge.svg?branch=main)](https://github.com/maciejbilinski/io-json-tools/actions/workflows/maven_build.yml) [![Tests](https://github.com/maciejbilinski/io-json-tools/actions/workflows/maven_feature.yml/badge.svg?branch=main)](https://github.com/maciejbilinski/io-json-tools/actions/workflows/maven_feature.yml)

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
5. Powinien nam się stworzyć folder **.idea** i w dolnym panelu **Build** powinien się wykonać proces **Sync** (czekamy aż się zakończy)
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
16. Otwieramy plik **test.html**
17. Klikamy przycisk "**Send**"

## Testy nieautomatyczne
Jeżeli napiszemy jakąś funkcjonalność logiczną możemy ją testować za pomocą pliku **test.html**.

1. Włączamy aplikację
2. Czekamy aż się załaduje (pojawia się log "Started JSONToolsApplication (...)")
3. Otwieramy plik **test.html**
4. Wpisujemy [poprawny input](#input)
5. Klikamy przycisk **Send**

Po każdej zmianie trzeba zresetować aplikację i poczekać, aż się załaduje. 
_Polecam [Hot reload](https://stackoverflow.com/questions/23155244/spring-boot-hotswap-with-intellij-ide)_.

<a id="input"></a>
### Poprawny input
#### Minify/Pretty
JSON, na którym ma być wykonana operacja powinien się znajdować w kluczu **json** pola wejściowego.
#### Subset/Skip
JSON, na którym ma być wykonana operacja powinien się znajdować w kluczu **json** pola wejściowego. Podzbiór kluczy powinien być tablicą napisów i znajdować się w kluczu **keys** pola wejściowego.

_Przykład:_

    {
        "json": {
            "example": 3,
            "test": "test"
        },
        "keys": [
            "example"
        ]
    }
#### Compare
Teksty do porównania powinny być **napisami** i znajdować się pod kluczami **text1** i **text2** pola wejściowego.

_Przykład:_

    {
        "text1": "Linia 1\nLinia 2",
        "text2": "Linia 1\nRóżna linia\nDodatkowa linia"
    }

## Autorzy
- Maciej Biliński
- Mikołaj Bartkowiak
- Kinga Cołta [**Proxy Product Owner**]
- Piotr Więtczak [**Scrum Master**]
