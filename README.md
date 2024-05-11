# **SWE4-Übungen - SS2024 - Übungszettel 5**

## **EURO-Bet – Die SWE4-Wettplattform zur Fußball-EM**

### Kurzbeschreibung

Die FH möchte die bevorstehende Fußball-EM für seine Studierenden und
Mitarbeiter besonders spannend machen und eine interne Wettplattform ins Leben
rufen. Teilnehmer sollen vor, aber auch während der Spiele Tipps über den
Gewinner abgeben können und bekommen (bei richtigen Tipps) nach einem
ausgeklügelten System Punkte gutgeschrieben. Dafür wird die Erstellung einer
Software in Auftrag gegeben, die im Wesentlichen folgende Anforderungen erfüllen
soll:

#### Verwaltungstool

* Eintragen von Mannschaften
* Eintragen von Spielpaarungen
* Aktualisieren von Spielergebnissen
* Benutzerverwaltung (eintragen, ändern, sperren)

#### Wettanwendung

* Login mit Benutzername und Passwort
* Anzeige der vergangenen und zukünftigen Spiele
* Abgabe von Tipps

### Verwaltungstool

Das Verwaltungstool steht dem Betreiber der Wettplattform zur Verfügung, um die
Mannschaften, Spiele und Ergebnisse zu warten bzw. die abgegebenen Tipps zu
analysieren.

Ein Spiel besteht aus den beiden Mannschaften, dem Spielort, dem Datum und der
Uhrzeit des Spielbeginns. Es soll möglich sein, Spiele bereits vorab einzutragen
(noch bevor das Ergebnis bekannt ist). Nach Ablauf des Spiels wird auch das
Spielergebnis festgehalten.

Alle Wett-Teilnehmer müssen sich beim Plattformbetreiber persönlich melden.
Dieser kann dann im Verwaltungstool neue Benutzer anlegen (Vorname, Nachname,
Benutzername, Passwort). Wenn ein Benutzer des Wettbetrugs verdächtigt wird, so
möchte der Betreiber den Benutzer auch wieder sperren können.

### Wettanwendung

Nach der Anmeldung mit den erhaltenen Zugangsdaten erscheint eine Übersicht
aller Spiele, bei bereits abgelaufenen Spielen ist auch der Endstand, der ev.
abgegebene Tipp des Benutzers und die dadurch erreichte Punktezahl sichtbar. Bei
noch nicht abgelaufenen Spielen kann der Benutzer einen Tipp abgeben:

*	Mannschaft 1 gewinnt 
*	Mannschaft 2 gewinnt 
*	Unentschieden

Der Tipp kann beliebig oft verändert werden, allerdings nur bis zur 80.
Spielminute (Sie können davon ausgehen, dass Spiele aufgrund der
Fernsehübertragungen pünktlich angepfiffen werden – und es keine Nachspielzeiten
gibt).

Aus dem Fußball-Lexikon: Ein Fußballspiel dauert 2 x 45 Minuten, unterbrochen
durch eine 15-minütige Spielpause.

Wichtig ist, dass die abgegebenen Tipps unterschiedlich gewichtet werden müssen.
Ein Tipp, der vor dem Spiel abgegeben wurde, führt natürlich zu mehr Punkten als
ein Tipp kurz vor Ende des Spiels. Überlegen Sie sich ein faires System, das
auch für Spannung sorgt (belasse ich meinen Tipp, weil er mir im Falle eines
korrekten Ergebnisses sehr viele Punkte bringen würde – oder ändere ich aufgrund
des Spielverlaufs ständig meinen Tipp und hamstere „sichere“ Punkte ein).

Eine Highscore-Tabelle gibt Auskunft über den aktuellen Gesamtpunktestand aller
Benutzer.

Diese Anwendung ist in den kommenden drei Übungen schrittweise umzusetzen. Die
genauen Anforderungen an die einzelnen Ausbaustufen (S1, S2 und S3) werden in
folgenden Dokumenten spezifiziert:

* [Ausbaustufe 1](README-S1.md)
* [Ausbaustufe 2](README-S2.md)
* [Ausbaustufe 3](README-S3.md)

### Technische Hinweise

Die einzelnen Ausbaustufen sind in drei Repositories abzugeben. Übernehmen Sie
auf folgende Weise die Versionshistorie der vorausgehenden Ausbaustufe $n-1$ in
die aktuelle Ausbaustufe $n$ ($n \in \{6, 7\}$).

* Repository für Übung $n$ klonen
  ```sh
  git clone https://github.com/swe4-[vz|bb]-2024ss/ue<n>-<user>.git
  ```

* In das Arbeitsverzeichnis dieses Repositories wechseln.
  
* Remote-Repository-Verweis auf das Repository der vorausgehenden Ausbaustufe
  erstellen
  ```sh
  git remote add ue<n-1> https://github.com/swe4-[vz|bb]-2024ss/ue<n-1>-<user>.git
  ```

* Herunterladen der Änderungsdaten vom Vorgänger-Repository
  ```sh
  git fetch ue<n-1>
  ```
  
* Einfügen des `main`-Branches des Vorgänger-Repositories in das aktuelle
  Repository.
  ```sh
  git merge ue<n-1>/main --allow-unrelated-histories
  ```

* Mergen von Dateien, die in beiden Repositories verkommen. Das sollte nur
  `ASSESSMENT.md` betreffen.
  ```sh
  git checkout --ours ./ASSESSMENT.md
  git add ./ASSESSMENT.md
  git commit -m "Merge files from previous assignment"
  ```

* Änderungen auf GitHub hochladen
  ```sh
  git push origin main
  ```
