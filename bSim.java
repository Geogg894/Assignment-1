import acm.graphics.*;
import acm.program.*;
import acm.util.RandomGenerator;
import java.awt.Color;

//importing packages necessary to this Assignment.

/**
 * The bSim class takes aBall objects created in this class and generates several 
 * instances of aBalls with randomized parameters(using Graphics Program).
 * @author George
 *
 */

public class bSim extends GraphicsProgram {

		
		private static final int WIDTH = 1200;   
		// n.b. screen coordinates 
		private static final int HEIGHT = 600; 
		private static final int OFFSET = 200; 
		private static final int NUMBALLS = 100;   
		// # balls to simulate 
		private static final double MINSIZE = 1.0;   
		// Minumum ball radius (meters) 
		private static final double MAXSIZE = 10.0;   
		// Maximum ball radius (meters) 
		private static final double EMIN = 0.1;   
		// Minimum loss coefficient 
		private static final double EMAX = 0.6;   
		// Maximum loss coefficient 
		private static final double VoMIN = 40.0;   
		// Minimum velocity (meters/sec) 
		private static final double VoMAX = 50.0;   
		// Maximum velocity (meters/sec) 
		private static final double ThetaMIN = 80.0;   
		// Minimum launch angle (degrees) 
		private static final double ThetaMAX = 100.0; 
		// Maximum launch angle (degrees) 
		private static final double XMAX=100.0;
		//Maximum value of X
		private static final double SCALE=HEIGHT/XMAX;
		//Pixels/meter	
		
		
	
	//sourced from Assignment 2 pdf.
		

		/** The run method generates a plank, resizes the screen and sets up
		 * the seed value.
		 */
				
		public void run() {
			
            GRect myRect= new GRect(0,HEIGHT,WIDTH,3); 
			
			//initial position of plank and its dimensions
			
			add(myRect);                         //creates plank
			
			
			
			this.resize(WIDTH,HEIGHT+OFFSET);
			
			//resetting pixel measurements for display canvas
			
			rgen.setSeed((long) 0.12345);  
			
			//setting up Seed
			
			
			
			
			//setting up loop that will randomly simulate 100 balls that will bounce concurrentlyy
			
			/** Balls are created with randomized parameters in this loop and 
			 * are concurrently generated and added to the simulation.
			 */
			
			for(int i=0;i<NUMBALLS;i++) {


			double bSize=rgen.nextDouble(MINSIZE,MAXSIZE);
			//randomizing bSize of each of the balls.
			Color bColor=rgen.nextColor();
			//randomizing bSize of each of the balls.
			double bLoss=rgen.nextDouble( EMIN , EMAX );
			//randomizing bSize of each of the balls.
			double Vo=rgen.nextDouble(VoMIN,VoMAX);
			//randomizing bSize of each of the balls.
			double theta=rgen.nextDouble(ThetaMIN,ThetaMAX);
			//randomizing bSize of each of the balls.
			
			
			//Creates ball and adds it to screen.
			
			
			aBall myBall=new aBall(WIDTH/2/SCALE,bSize,Vo,theta,bSize,bColor,bLoss);   
			add(myBall.getBall());   
			myBall.start(); 
					
			
			
			}
					 
			
			
			
		}				
		
		//Random generator.
				
		private RandomGenerator rgen=RandomGenerator.getInstance(); 
		
		
 }


