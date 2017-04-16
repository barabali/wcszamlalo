// Agent foglaltszamlalo in project helloworld2

/* Initial beliefs and rules */

/* Initial goals */

/* Plans */

+!kqml_received(A,B,m(X, Y, Z),_) : A == utbaigazito & B == askAll <- +m(X,Y,Z); .print(X, Y, Z) ; !createBid.
+!createBid : true <- ?m(X,Y,Z); ?szarny(S) helloworld2.createBid(X,Y,Z,S,O); !preparesend(O).
+!preparesend(O): true<- .number(O);.send("utbaigazito",tell,O). 
