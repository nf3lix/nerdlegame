# NerdleGame
## Spiel-Anleitung
- Diese Anwendung ist dem [NerdleGame](https://nerdlegame.com/) nachempfunden
- Das Original:  
  - man hat 6 Versuche, um einen mathematischen Term mit insgesamt 8 Ziffern zu erraten
  - gibt man einen Tipp ab, wird farblich hervorgehoben, welche getippten Ziffern an der richtigen Position sind (grün), welche zwar vorkommen aber an der falschen Position sind (lila), und welche Ziffern nicht vorkommen (schwarz)
- Diese Version:
  - 1 vs. 1 (bzw. durch Ändern einer Variable auch mit mehr als zwei Spielern)
  - wer den Term als erstes errät, gewinnt die Runde
  - Term wird per Zufallsprinzip durch den Server bestimmt; Nach Abgabe eines Tipps erhält der Client eine entsprechende Antwort, welche Ziffern richtig oder falsch sind

## Applikation    
  - Java Anwendung
  - Java Standard Bibliothek, keine Fremdbibliotheken
  - Client-Server Architektur, TCP Sockets

## Anleitung
- Klassen in folgender Reihenfolge ausführen:
  - ```de.dhbw.nerdlegame.NerdleGameServer``` im Modul ```00-nerdlegame-server``` (unter Angabe eines offenen Ports im in den Command-Line Arguments)
  - ```de.dhbw.nerdlegame.NerdleGameClient``` im Modul ```00-nerdlegame-client``` mindestens zwei Mal (unter Angabe der Server-Adresse & Port)
- Am Beispiel von Maven:
  1. Im Hauptverzeichnis ausführen:
  2. ```mvn clean install```
  3. ```mvn exec:java -pl 00-nerdlegame-server -Dexec.args="5000"``` (Server starten unter Port 5000)
  4. ```mvn exec:java -pl 00-nerdlegame-client -Dexec.args="127.0.0.1 5000"``` (Client starten unter Angabe der Serer Adresse und des Ports -> zwei Mal um Runde spielen zu können)
  5. In der UI der beiden Clients auf "Find game..." klicken
  6. Spaß haben (oder halt auch nicht, je nach Rechnung... wer cheaten möchte, kann sich aber auch einfach in der Konsolen-Ausgaben des Servers die Lösung anschauen)
- ![Einfache UI](https://i.imgur.com/gtakRyS.png)

## Offene Punkte zur Umsetzung
- Paar mehr Tests wären schön (aber es gibt welche!)
- Clean Architecture wurde anfangs angestrebt, dann aber leider nicht mehr so ganz durchgezogen
- Clients schließen sich automatisch nach einer Runde -> ginge auch schöner
