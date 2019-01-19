
/**
 * @(#)gacircle.java
 *
 *
 *
 * @version 1.00 2008/10/25
 */
 
import java.io.*;
import java.util.*;
import java.awt.*;
import java.lang.*;


public class gacircle {
	
	//intial parameter setup    
	double X_location;
	double Y_location;
	double radius;
    double selection;//(optional) used for roullete wheel selection
	
    //this is used to create a new instance of gacircle******************************************************************************************
    public gacircle( double x,double y, double r, double s) {
    	X_location = x;
    	Y_location = y;
    	radius = r;
    	selection = s;
    }
    //end of gacircle creator******************************************************
   

  //an example code of how GA is run
  //Feel free to change this to do what you want.
    
  static gacircle crossover(gacircle a, gacircle b) {
	  //Returns a child from parent a's X and parent b's Y
	  gacircle child = new gacircle (a.X_location, b.Y_location, 0, 0);
	  return child;
  }
  
  static void mutate(gacircle a) {
	  //If we're on 9 or 10, mutate the x/y values by a double value between 0 and 1
	  Random prob = new Random();
	  int MutVal = prob.nextInt(10);
	  if(MutVal == 9) {
		  a.X_location += prob.nextDouble();
		  a.Y_location += prob.nextDouble();
	  }
	  else if (MutVal == 10)
	  {
		  a.X_location -= prob.nextDouble();
		  a.Y_location -= prob.nextDouble();
	  }
	  //Otherwise, just add/subtract 0.1 based on the random int
	  else if (MutVal < 5){
		  a.X_location += 0.1;
		  a.Y_location += 0.1;
	  }
	  else {
		  a.X_location -= 0.1;
		  a.Y_location -= 0.1;
	  }
  }
  
  static int answersofar (ArrayList<gacircle> S) {
	  //Checks through the population and returns the circle with the largest radius (fitness value)
	  int pos = 0;
	  double best = 0;
	  for(int i = 0; i < S.size(); i++) {
		  if((S.get(i)).radius > best){
			  best = (S.get(i)).radius;
			  pos = i;
		  }
	  }
	  return pos;
  }
  
  static void roulette(ArrayList<gacircle> S) {
	  double fitSum = 0;
	  
	  //Sums up the fitness values for the roulette formula	  
	  for(int i = 0; i < S.size(); i++){
		  fitSum += (S.get(i)).radius;
	  }	  
	  for(int i = 0; i < S.size(); i++){
		  gacircle curr = S.get(i);
		  //Set the selection value by dividing the current radius (fitness) by the sum of all the radii(fitnesses)
		  curr.selection = (curr.radius/fitSum);
	  }
  }
  
  static int select(ArrayList<gacircle> S) {
	  Random prob = new Random();
	  //Generate a value between 0 and 1 to use to select a parent
  	  double selector = prob.nextDouble();

	  for(int i = 0; i < S.size(); i++) {
		  //Check whether we're on the current selected value
		  if(selector < 0) {
			  return i;
		  }
		  //If we're not on the selected value yet, eliminate the current gacircle and keep going
		  else {
			  selector -= (S.get(i)).selection;
		  }
	  }
	  //If somehow we don't return a parent from the loop, return the final gacircle in the population
	  return S.size()-1;
  }
  
  static void eval_fitness(ArrayList<gacircle> S, gacircle[] G) {
	  for(int i = 0; i< S.size(); i++) {
		  gacircle curr = S.get(i);
		  double distance = 0;
		  for(int j = 0; j<5; j++) {
			  //Take the distance between the centers of the circles
			  distance = Math.sqrt(Math.pow((G[j].X_location-curr.X_location),2)+Math.pow((G[j].Y_location-curr.Y_location),2));
			  //If there isn't a radius, maximize the radius
			  if(curr.radius == 0) {
				  curr.radius = distance-(G[j].radius);
			  }
			  //Otherwise, if the circles overlap, reduce the child radius to be on the border of the other circle
			  else if (distance <= (curr.radius+G[j].radius)) {
				  curr.radius = distance-(G[j].radius);
			  }
		  }
		  //If the current radius would take us outside the boundaries, reduce it to be right on the respective boundary
		  if(curr.X_location+curr.radius > 10){			
			  curr.radius -= ((curr.X_location+curr.radius)-10);
		  }
		  if(curr.X_location-curr.radius < 0) {
			  curr.radius -= -(curr.X_location-curr.radius);
		  }
		  if(curr.Y_location+curr.radius > 10){
			  curr.radius -= ((curr.Y_location+curr.radius)-10);
		  }
		  if(curr.Y_location-curr.radius < 0) {
			  curr.radius -= -(curr.Y_location-curr.radius);
		  }
	  }
  }
  
  
  static int produce(ArrayList<gacircle>S, gacircle[] G, Splat pl){
  	int count = 0;
  	int current_generation = 0;
  	int generation = 0;
  	int current = -1;
  	
  	while(count < 1000){ //maximum iteration is set to 1000
    
    //evaluate the fitness of the population in S
  	eval_fitness(S,G);
    //create the roulette wheel for the population in S
  	roulette(S);	
    //select the father and the mother (parents)
  	int f = select(S);
  	int m = select(S);
  	
  	Random prob = new Random();
  	int luck = prob.nextInt(100);
  	
  	if(luck <80){ //probability of crossover is set to 0.8
	  	gacircle child1 = crossover(S.get(f),S.get(m)); //offspring 1
	  	gacircle child2 = crossover(S.get(m),S.get(f)); //offsring 2
	  	mutate(child1); //mutate the offspring 1
	  	mutate(child2); //mutate the offspring 2
	  	//Add the offspring to the population
	  	S.add(child1);
	  	S.add(child2);
	    
	  	current_generation++;
	        
	    //re-evaluate the fitness of the population again
	    eval_fitness(S,G);
	    //re-set the roulette wheel again
	    roulette(S);
  	}
    
  	
    //get the best answer so far
  	int k = answersofar(S);
  	gacircle A = S.get(k);
        
	//update the screen
	pl.update(A);
	try {
       Thread.sleep(20);                 //1000 milliseconds is one second.
    } catch(InterruptedException ex) {
       Thread.currentThread().interrupt();
    }
    
  	if(k != -1 && k != current){
  	 	current = k;
  	 	generation = current_generation;
  	 }
    System.out.println("answer's generation: "+generation+ ", total generation: "+current_generation);
  	count++;
  	}
  	//return the index of the best solution at the end
  	return current;
  	
  	
  }
  public static void main(String[] args) throws FileNotFoundException{
  	File inFile = new File(args[0]);
  	Scanner scan = new Scanner(inFile);
  	
  	double x=0,y=0,r = 0;
  	
  	gacircle [] G = new gacircle[5];
  	
  	Random  Xgen = new Random();
  	Random  Xdec = new Random();
  	Random  Ygen = new Random();
  	Random  Ydec = new Random();
  	Random  Rgen = new Random();
  	Random  Rdec = new Random();
  	
    //create 5 random circle to within the rectangle (0,0),(0,10),(10,0), and (10,10)
  	for(int i = 0; i < 5; i++){
  		G[i] = new gacircle(Xgen.nextInt(10)+ Xdec.nextFloat(),Ygen.nextInt(10)+Ydec.nextFloat(),Rgen.nextInt(3)+Rdec.nextFloat(),0);
  	}
    //Array List of population
  	ArrayList<gacircle> pop = new ArrayList<gacircle>();
      
    //reading the initial population from the input file
  	while(scan.hasNext()){	
  		gacircle C = new gacircle(scan.nextDouble(),scan.nextDouble(),0,0);
  	    pop.add(C);
  	}
    //evaluating the fitness of the population
  	eval_fitness(pop,G);
    //create the slots for roulette wheel selection
  	roulette(pop);
    //splat object for drawing circle
  	Splat sp1 = new Splat();
    //create dummy gacircle
  	gacircle B = new gacircle(0,0,0,0);
    //draw the the 5 initial circle with the dummy circle
	sp1.run(G,B);
  	
    //get the answer
    int k = produce(pop,G,sp1);
    
    if(k != -1){ //an answer was found
    	System.out.println("Disks set");
    	for(int i = 0; i < G.length; i++)
    		System.out.println("Disk #"+i+" : x-location:"+G[i].X_location+", y-location:"+G[i].Y_location+", radius:"+G[i].radius);
    	
		gacircle A = pop.get(k);
		System.out.println("Solution:");
		System.out.println("x-location: "+A.X_location+",y-location: "+A.Y_location+", radius: "+A.radius);
		//Splat sp1 = new Splat();
		sp1.update(A);
		
	}
    else{//no answer was found
	
		System.out.println("no answer");
		for(int i = 0; i < pop.size(); i++)
			System.out.println(pop.get(i).X_location+" , " + pop.get(i).Y_location+" , " +pop.get(i).radius);
	}
  }
   
   
}
