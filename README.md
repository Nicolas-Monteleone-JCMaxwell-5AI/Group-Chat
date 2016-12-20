# Group-Chat
Chat di gruppo creata in java.
Tramite l'avvio del programma dal cmd del server e del client si ha la possibilità di chattare con i propi amici scegliendo il propio nickname

### Prerequisiti
Java SDK (Software Development Kit) - programma per la compilazione (javac) da codice in Java a ByteCode. L'installazione comprende anche Java JRE (Java Runtime Envirorment) che fornisce la Virtual Machine (VM) su cui far eseguire il ByteCode.
```
E' suggerito anche l'utilizzo di netBeans IDE (Integrated Development Envirorment)
```

##Installazione
Scarica i files del progetto cliccando "Clone/Download"
* in NetBeans crea due nuovi progetti "Java-Sockets-Client" e "Java-Sockets-Server"

* estrai i files e spostali nella sub-directory "src" dei rispettivi progetti (es. Documents\NetBeansProjects\Java-Sockets-Server\src)

##Utilizzo
Lo scopo di questo progetto è di collegare 2 o più computer ad un server avviato su una macchina,
tramite ip e porta, ci si collega a questo server tramite il client.
C'è anche la possibilità di poter aggiungere uno username :D

## Esempio usando uno stesso computer sia per eseguire Server che multipli Clients
Da finestra di comando esguire il Server:
```
cd Documents\NetBeansProjects\Java-Sockets-Server\src
java ServerTestoMultiThreaded 1234
```
Da nuova finestra di comando esguire il ```primo``` Client
```
cd Documents\NetBeansProjects\Java-Sockets-Client\src
java client-Testo localhost 1234
```
Da nuova finestra di comando esguire il ```secondo``` Client
```
cd Documents\NetBeansProjects\Java-Sockets-Client\src
java client-Testo localhost 1234
```

##Versione Programma
versione 1.0


## Licenza
opensource nel modo piu' completo del termine :) senza alcuna restrizione!
##Autori 
Rinaldi Brescia Monteleone
