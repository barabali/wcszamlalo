// Agent person in project helloworld2

/* Initial beliefs and rules */
position("I E 2").
desire("wc").
/* Initial goals */

!find.

/* Plans */

+!find : true <- burn. 
+change <- Uj="I E 110"; -+position(Uj); .print(Uj); !getPos.
+!getPos : true <- -getPos; ?desire(O); ?position(N); .send(utbaigazito,tell,ask(N,O)).
