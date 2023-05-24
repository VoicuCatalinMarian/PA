Instructiuni de compilare:
	-> pentru compilare este folosita comanda "make".
	-> pentru rulare este folosita comanda din regulamentul proiectului:
		xboard -fcp "make run" -debug.
	-> pentru stergerea fisierelor cu extensia .class este folosita comanda:
		make clean.
Botul nu merge in urma schimbarii de pe alb pe negru, dar 1v1 sau cu mutari custom merge
pana in momentul in care se ajunge fie la remiza fie la sah mat(acest lucru se datoreaza
faptului ca am lucrat pe scheletul vechi al proiectului, adica cel initial).
Am incercat si cu "Makefile"-ul pus la dispozitie de echipa de "PA", dar nu ne-a mers,
asa ca am facut repede unul al nostru(timpul si alte activitati ne-au cam complesit aici).

Detalii despre structura proiectului:
	-> pentru implementarea propriu-zisa a proiectului am folosit atat structuri de date
	   deja existente in Java, precum "ArrayList", "LinkedHashMap", cat si structuri
 	   de date primitive, precum vectorii si matricile.
	-> pentru tabla de sah am ales sa folosim o matrice char[][] de 8x8.
	-> am folosit un "ArrayList" pentru stocarea mutarilor aleatoare(legale)
	   la fiecare pas.
	-> am folosit un "LinkedHashMap" pentru a tine cont de piesele care se afla in
	   "Crazy House".
	-> am folosit un "ArrayList" pentru mutarile specifice din "Crazy House", pe care
	   la final il adaugam la "ArrayList"-ul de mutari finale.
	-> in "recordMove" este inregistrata fiecare miscare si actualizata tabla de sah
	   cu noua piesa ce urmeaza a fi pusa.

Abordarea algoritmica a etapei:
	-> nu am folosit niciun algoritm specific, mutarile pe care bot-ul alege sa le faca
	   fiind aleatoare(extrase din "ArrayList"-ul cu mutari pentru fiecare culoare,
	   folosind clasa "Random" din Java).

Surse de inspiratie:
	-> nu ne-am inspirat de nicaieri din punct de vedere al implementarii(totul a fost
	   facut manual, de la 0), iar pentru intelegerea modului de joc "Crazy House" am
	   folosit Chess.com.

Responsabilitatea fiecarui membru al echipei:
	-> Sprincenatu Bogdan-Andrei, Manolache George-Vlad si Osnaga Robert:
		-> responsabili pentru modul de joc "Crazy House" si implementarea calului,
		   nebunului si al reginei.(atat "recordMove", cat si "calculateNextMove")
	-> Osnaga Robert si Voicu-Catalin:
		-> responsabili pentru piese precum tura, pion si rege.(atat "recordMove",
		   cat si "calculateNextMove").

P.S. Toti suntem de la grupa 322CC.