//// Agent sample_agent in project helloworld2
//
///* Initial beliefs and rules */
//
///* Initial goals */
//
//!start.
//
///* Plans */
//
//!start.
//
//+!start : true <- burn.
//+masodik<- !makeFalse; !message1.
//+!makeFalse: true <- -masodik.
//+!message1 : true & masodik <- message2.
//+fire <- run.

// Agent john: Initial beliefs and rules */
	
// A 'book' belief has three arguments: the title, its price, the quantity in stock
book("Harry", 32, 20).
book("Jason", 50, 10).

/* Initial goals */
	
	
/* Plans */
	
