// Agent mantoiletsensor in project helloworld2

/* Initial beliefs and rules */

/* Initial goals */

/* Plans */

+takenWc: available(A,B) & A>0 <- ?available(N,M); UjN=N-1; -+available(UjN,M);?available(I,J);.print("Maradt:",I,",",J); -takenWc[source(percept)].