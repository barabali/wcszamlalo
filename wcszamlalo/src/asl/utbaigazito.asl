// Agent utbaigazito in project helloworld2

/* Initial beliefs and rules */

/* Initial goals */

/* Plans */

+getWc(X, Y, Z): true <- .broadcast(askAll, m(X, Y, Z)); -getWc(X, Y, Z)[source(percept)].
+!kqml_received(A,B,C,_) :(A==foglaltszamlalo1 | A==foglaltszamlalo2 | A==foglaltszamlalo3)& C>7 & B == tell <- printResult(C).