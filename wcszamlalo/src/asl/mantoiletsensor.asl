// Agent mantoiletsensor in project helloworld2

/* Initial beliefs and rules */

/* Initial goals */

/* Plans */

+available(A, B) : true <- +available(A, B).

+takenWc: available(A,B) & A>0 <- ?available(N,M); UjN=N-1; -+available(UjN,M);?available(I,J);.print("Allapot:",I,",",J); -takenWc[source(percept)]. 
+takenUrin: available(A,B) & B>0 <- ?available(N,M); UjM=M-1; -+available(N,UjM);?available(I,J);.print("Allapot:",I,",",J);-takenUrin[source(percept)].

+freeWc: available(A,B) & A>=0 & A<2 <- ?available(N,M); UjN=N+1; -+available(UjN,M);?available(I,J);.print("Allapot:",I,",",J); -takenWc[source(percept)]. 
+freeUrin: available(A,B) & B>=0 & B<3 <- ?available(N,M); UjM=M+1; -+available(N,UjM);?available(I,J);.print("Allapot:",I,",",J);-takenUrin[source(percept)].
