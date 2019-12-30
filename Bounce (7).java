import acm.graphics.*;
import acm.program.*;
import java.awt.Color;

//importing packages necessary to assignment.

    public class Bounce extends GraphicsProgram{
	
    private static final int WIDTH=600;
	//defines the width of the screen in pixels
	private static final int HEIGHT=600;
	//distance from top of the screen to ground plane
	private static final int OFFSET=200;
	//distance from bottom of screen to ground plane
	private static final double g=9.8;
	//MKS gravitational constant 9.8m/s^2
	private static final double Pi=3.141592654;
	//To convert degrees to radians
	private static final double mass = 1.0;
	//Mass of the ball(kg)
	private static final double Xinit=5.0;
	//Initial ball location (X)
	private static final double TICK=0.1;
	//Clock tic duration (sec)
	private static final double ETHR=0.01;
	//If either Vx or Vy<ETHR STOP
	private static final double XMAX=100.0;
	//Maximum value of X
	private static final double YMAX=100.0;
	//Maximum value of Y
	private static final double PD=1;
	//Trace point diameter
	private static final double k=0.0016;
	//Parameter k
	private static final boolean TEST = true;      
	// print if test true
	private static final double SCALE=HEIGHT/XMAX;
	//Pixels/meter	
	
	           //source: from Assignment 1 pdf.
	
	public void run() {
		//size display window
		// TODO Auto-generated method stub	
		
		println("Initial velocity of the ball in m/s [0,100]");
		double Vo= readDouble("Enter Vo: ");
		println("Launch angle in degrees [0,90]");
		double theta= readDouble("Enter theta: ");
		println("Radius of the ball in meters [0.1,5]");
		double bSize= readDouble("Enter bSize: ");
		println("Energy loss parameter [0,1]");
		double Elossparameter= readDouble("Enter Elossparameter: ");
		
		//setting up user input
		
		this.resize(WIDTH,HEIGHT+OFFSET);
		
		//resetting pixel measurements for display canvas
		
		
		final double Yo=bSize;
		//Initial ball location (Y)
		
		
		GOval myBall = new GOval 
				(Xinit*SCALE,(HEIGHT-bSize)*SCALE,2*bSize*SCALE,2*bSize*SCALE); 
		
		//initial position of ball and its dimensions
		
		myBall.setFilled(true);
		myBall.setFillColor(Color.RED); //filling the ball red
		add(myBall);                        //creates the ball
		
		
		GRect myRect= new GRect(0,HEIGHT,600,3); 
		
		//initial position of plank and its dimensions
		
		add(myRect);                     //creates plank
		
		
		//Initialize Variables
		double Vt=(mass*g)/(4*Pi*bSize*bSize*k);
		//terminal velocity
		
		double Vox=(Vo*Math.cos(theta*Pi/180));
		//x component of initial velocity
		
		double Voy=(Vo*Math.sin(theta*Pi/180));
		//y component of initial velocity
		
		double Xlast=Xinit;
		double Ylast= Yo;
		double time=0;
                double Xo = Xinit;
                
                //source: Assignment 1 pdf.
		
                
        //boolean TEST=true;
		//Simulation loop
		
                
        while(true) {
			double X= Xo+Vox*Vt/g*(1-Math.exp(-g*time/Vt)); 
			//X coordinate of ball
			
			double Y=bSize + Vt/g*(Voy+Vt)*(1-Math.exp(-g*time/Vt))-Vt*time;
			//Y coordinate of ball
			
			double Vx=(X-Xlast)/TICK; 
			//X component of ball's velocity
			
			double Vy=(Y-Ylast)/TICK; 
			//Y component of ball's velocity
			
			
			//Initializing X,Y,Vx,Vy in loop
			//Source: Assignment 1 pdf.
			
			Xlast=X;
			Ylast=Y;
			
			//X and Y values to be used in subsequent iterations
			
			
			 
			if (TEST)  System.out.printf //print following values on console if test true
			("t: %.2f  X: %.2f  Y: %.2f  Vx: %.2f  Vy:%.2f\n", time,Xlast+X,Y,Vx,Vy); 
			
			//source: Assignment 1 pdf.

			int ScrX= (int)  ((X-bSize)*SCALE);
			int ScrY= (int) (HEIGHT-(Y+bSize)*SCALE);
			
			//setting up simulation coordinates
			
			myBall.setLocation(ScrX,ScrY);
			//Screen units
			
			GOval trace= new GOval(ScrX,ScrY,SCALE*PD,SCALE*PD); 
			
			//loops creation of Oval with these dimensions at each iteration for trace
			
			trace.setFilled(true); //Fills the path's trace
			add (trace);
			
			//adds the path's trace
			
			time+=TICK;
			pause(TICK*1000);
			
			//Large TICK, could lead to error/ball going slightly below the plank
			
			//sets time as a sum of clock tick
			//A second pause between each tick
			
			if(Y<=bSize&&Vy<0) { 
				double KEx=((double) 0.5* mass* (Math.pow(Vx, 2)))*(1-Elossparameter);	
				
				//Formula for x component of kinetic energy
				
				
				 double KEy=((double) 0.5* mass* (Math.pow(Vy, 2)))*(1-Elossparameter);	
				 
				//Formula for Y component of kinetic energy
				
				 Vox = Math.sqrt(2*KEx);  // Resulting horizontal velocity 
				Voy = Math.sqrt(2*KEy);  // Resulting vertical velocity )
				
				Xo=Xlast;
				Y=bSize;
				time=0;
				X=0;
				
				//parameters for bouncing of the ball to occur
				
                                if ((KEx <= ETHR) | (KEy <= ETHR)) break;
                               
                                //if statement source: Assignment 1 pdf.
                                
                         
                                
                                if(X>XMAX||Y>YMAX)
                            {   println("ERROR!");
			// ERROR if XMAX or YMAX surpassed by ball
			
                            }
				
			
			}
			
		}
	}
	
}












