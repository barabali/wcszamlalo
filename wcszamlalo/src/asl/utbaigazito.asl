// Agent utbaigazito in project helloworld2

/* Initial beliefs and rules */
number(1).
/* Initial goals */

/* Plans */

+getWc(X, Y, Z): true <- ?number(A); B=A+1;-+number(B);.broadcast(askAll, m(X, Y, Z,B)); -getWc(X, Y, Z)[source(percept)].

+place_bid(N,_,_)     // receives bids and checks for new winner
   :  .findall(b(R,V),place_bid(N,R,V)[source(A)],L) & 
      .length(L,3)  // all 3 expected bids was received
   <- main.defaultinternalactions.winner(L,R); printResult(R).
