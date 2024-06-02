# **SWE4-Übungen - SS2024 - Übungszettel 6**

## **Ausbaustufe 2: EURO-Bet-Server**

In der Übung 5 haben Sie bereits eine Java-Anwendung mit grafischer
Benutzeroberfläche (auf Java-FX-Basis) für unsere Wettplattform erstellt. Im
bisherigen Ausbaustadium sollte es das Verwaltungstool im Wesentlichen
ermöglichen, Benutzer, Mannschaften, Fußballspiele und Spielergebnisse zu
verwalten. In der Wettanwendung sollten sich eingeloggte Benutzer über das
Wettangebot informieren und Tipps abgeben können.

In dieser Ausbaustufe soll Ihr Programm zu einer Client-Server-Anwendung
erweitert werden. Die Clients und der Server stellen eigenständige Programme
dar, die über RMI miteinander kommunizieren.

Im Detail sollten Sie folgende Anforderungen umsetzen:

1.	Implementieren Sie die Serverkomponente, welche die Daten der Anwendung
    verwaltet. Stellen Sie die Funktionalität der Komponente über eine
    RMI-Schnittstelle zur Verfügung. Zur Serverkomponente sollen sich parallel
    beliebig viele Clients verbinden können. Die Datenhaltung kann in dieser
    Ausbaustufe noch vollständig im Hauptspeicher der Serverkomponente erfolgen.
    In der nächsten Ausbaustufe ist die Anwendung um die dauerhafte Speicherung
    der Daten in einer relationalen Datenbank zu erweitern.

2.	Integrieren Sie das Verwaltungstool und die Wettanwendung in die
    Client-Server-Architektur. Beide Anwendungen sind über RMI mit der
    Serverkomponente verbunden und tauschen so mit dieser Daten aus. Beachten
    Sie, dass die beiden Clients ausschließlich für die Darstellung der Daten
    und die Benutzerinteraktion zuständig sind. Alle darüber hinausgehenden
    Aufgaben sind an die Serverkomponente zu delegieren. Stellen Sie sicher,
    dass die GUI bedienbar bleibt, auch wenn z. B. gerade auf eine Antwort vom
    Server gewartet wird.

Achten Sie darauf, dass Sie Ihre Anwendung nach den Grundprinzipien des
objektorientierten Designs entwerfen, jede Klasse für einen Aspekt der Anwendung
verantwortlich ist und die Methoden nicht zu umfangreich sind. Erstellen Sie für
Ihre Anwendung sowohl Unit- als auch Integrations-Tests.
