// Agent foglaltszamlalo in project helloworld2

/* Initial beliefs and rules */

/* Initial goals */

/* Plans */

+!kqml_received(A,B,m(X, Y, Z,N),_) : A == utbaigazito & B == askAll <- +m(X,Y,Z,N); .print(X, Y, Z) ; !createBid.
+!createBid : true <- ?m(X,Y,Z,N); ?szarny(S) helloworld2.createBid(X,Y,Z,S,O); !preparesend(O,N).
@lbid
+!preparesend(O,N): true<- .send("utbaigazito",tell,place_bid(N,O)); ?m(X,Y,Z,N);-m(X,Y,Z,N).
