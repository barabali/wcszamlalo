// Agent foglaltszamlalo in project helloworld2

/* Initial beliefs and rules */

/* Initial goals */

/* Plans */

+!kqml_received(A,B,m(X, Y, Z,N),_) : A == utbaigazito & B == askAll <- +m(X,Y,Z,N) ; !startCreatingBid.
+!startCreatingBid : true <- ?m(X,Y,Z,N); ?szarny(S) main.defaultinternalactions.createBid(X,Y,Z,S,R,O); !preparesend(N,R,O).

+!preparesend(N,R,O): true<- .send("utbaigazito",tell,place_bid(N,R,O)); ?m(X,Y,Z,N);-m(X,Y,Z,N).