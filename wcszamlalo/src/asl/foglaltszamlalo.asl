// Agent foglaltszamlalo in project helloworld2

/* Initial beliefs and rules */

/* Initial goals */

/* Plans */

+!kqml_received(A,B,m(X, Y, Z,N),_) : A == utbaigazito & B == askAll <- +m(X,Y,Z,N); .print(X, Y, Z) ; !createBid.
+!createBid : true <- ?m(X,Y,Z,N); ?szarny(S) helloworld2.createBid(X,Y,Z,S,R,O); !preparesend(O,R,N); .print("Valasztott: ",R).

+!preparesend(O,R,N): true<- .send("utbaigazito",tell,place_bid(N,R,O)); ?m(X,Y,Z,N);-m(X,Y,Z,N).