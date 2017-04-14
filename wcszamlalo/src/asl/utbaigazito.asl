// Agent utbaigazito in project helloworld2

/* Initial beliefs and rules */

/* Initial goals */

/* Plans */

+!kqml_received(A,B,C,_) : A == person & B == tell <- helloworld2.getClosest(C); .print(A," ",C,"-t mondott").
