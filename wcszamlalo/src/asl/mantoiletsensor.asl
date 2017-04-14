// Agent mantoiletsensor in project helloworld2

/* Initial beliefs and rules */
available(2,3).
/* Initial goals */

/* Plans */

+takenWc: available(A,B) & A>0 <- ?available(N,M); UjN=N-1; -+available(UjN,M);?available(I,J);.print("Maradt:",I,",",J); -takenWc[source(percept)]. 
+takenUrin: available(A,B) & B>0 <- ?available(N,M); UjM=M-1; -+available(N,UjM);?available(I,J);.print("Maradt:",I,",",J);-takenUrin[source(percept)].