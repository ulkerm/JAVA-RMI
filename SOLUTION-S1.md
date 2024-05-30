# **SWE4-Übungen - SS2024 - Übungszettel 5 - Ausarbeitung**

## **EURO-Bet - Ausbaustufe 1**

### **Lösungsidee**
In erste Teil(Verwaltungstools) kann man neue Team hinzufügen, neue Spielpaarung und Spielergebnissen eintragen, neue User erstellen oder existriende user ändern, sperren(ich mache delete :) ). Um diese testen - eine Liste von User oder Team oder Spielen, habe ich Observable List und singletons benutzt. 
in zweite Teil muss User login machen. In diesem Teil habe ich kein User erstellt, weil in der Angabe ist geschrieben: hartcodierten Daten arbeiten. Es wird geprüft ob solche user existiert. Dann kann user eine Liste von Spielen, eigene Punkte und nächste Spiel sehen. Dort kann user betten. Nachdem wird diese bet in der mitte sichtbar.
Ich habe Bet klasse implementriert, aber die funktionalität habe ich nicht benutzt wegen hartcodierte teil in code.

### **Testfälle**
![Screen Recording 2024-05-30 at 20.24.19.gif](Screen%20Recording%202024-05-30%20at%2020.24.19.gif)
![Screen Recording 2024-05-30 at 21.01.15.gif](Screen%20Recording%202024-05-30%20at%2021.01.15.gif)
