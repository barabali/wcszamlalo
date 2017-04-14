// Agent mantoiletsensor in project helloworld2

/* Initial beliefs and rules */

/* Initial goals */

/* Plans */

+available(A, B) : true <- +available(A, B).

+takenWc: available(A,B) & A>0 <- ?available(N,M); UjN=N-1; -+available(UjN,M);?available(I,J);.print("Allapot:",I,",",J); -takenWc[source(percept)]. 
+takenUrin: available(A,B) & B>0 <- ?available(N,M); UjM=M-1; -+available(N,UjM);?available(I,J);.print("Allapot:",I,",",J);-takenUrin[source(percept)].

+freeWc: true <- ?available(N,M); UjN=N+1; -+available(UjN,M);?available(I,J);.print("Allapot:",I,",",J); -freeWc[source(percept)]. 
+freeUrin: true <- ?available(N,M); UjM=M+1; -+available(N,UjM);?available(I,J);.print("Allapot:",I,",",J);-freeUrin[source(percept)].
