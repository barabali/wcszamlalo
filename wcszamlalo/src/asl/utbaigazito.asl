// Agent utbaigazito in project helloworld2

/* Initial beliefs and rules */

/* Initial goals */

/* Plans */

+getWc(X, Y, Z): true <- .broadcast(askAll, m(X, Y, Z)); -getWc(X, Y, Z)[source(percept)].