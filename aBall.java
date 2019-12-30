	import acm.graphics.*;
    import java.awt.Color;
    //importing packages necessary to assignment.
    
    /** The aBall class creates an oval ball and simulates its trajectory defined by formulas from Assignment 1.
     * @author George
     * An object of the aBall class is created in the bSIm class, and the Thread extension 
     * makes it possible for multiple aBalls to run simultaneously.
     */
	
    
    public class aBall extends Thread {

   
	private static final int HEIGHT=600;
	//distance from top of the screen to ground plane
	private static final double g=9.8;
	//MKS gravitational constant 9.8m/s^2
	private static final double Pi=3.141592654;
	//To convert degrees to radians
	private static final double mass = 1.0;
	//Mass of the ball(kg)
	private static final double TICK=0.1;
	//Clock tic duration (sec)
	private static final double ETHR=0.01;
	//If either Vx or Vy<ETHR STOP
	private static final double XMAX=100.0;
	//Maximum value of X
	private static final double k=0.0001;
	//Parameter k
	private static final boolean TEST = true;      
	// print if test true
	private static final double SCALE=HEIGHT/XMAX;
	//Pixels/meter	
	
	
	
	//source: from Assignment 1 pdf.
	
	
	
	private double Xinit;
	//Initial ball location (X)
	private double bSize;
	//Instance variable of radius (part of constructor)
	private double Yo;
	//Instance variable of radius (part of constructor)
	private double Vo;
	//Instance variable of velocity (part of constructor)
	private double theta;
	//Instance variable of angle (part of constructor)
	private Color bColor;
	//Instance variable of ball color (part of constructor)
	private double bLoss;
	//Instance variable of loss coefficient (part of constructor)
	private GOval myBall;
	//Initial ball location (Y)	
	
		
		
		
		/** * The constructor specifies the parameters for simulation.  They are *  
		 * 
		 * * @param Xinit  double The initial X position of the center of the ball 
		 * * @param Yo  double The initial Y position of the center of the ball 
		 * * @param Vo  double The initial velocity of the ball at launch 
		 * * @param theta double Launch angle (with the horizontal plane) 
		 * * @param bSize  double The radius of the ball in simulation units 
		 * * @param bColor Color  The initial color of the ball 
		 * * @param bLoss double Fraction [0,1] of the energy lost on each bounce 
		 */ 
		
	
	    //javadoc documentation of the constructor is sourced from Assignment 2.
		
	    public aBall(double Xinit, double Yo, double Vo, double theta, double bSize, Color bColor, double bLoss) 
		
		//setting up the aBall constructor
		
		{
		
		
		this.Xinit = Xinit;   
		this.Yo = Yo;   
		this.Vo = Vo;                        
		this.theta = theta;   
		this.bSize = bSize;   
		this.bColor = bColor;   
		this.bLoss = bLoss;
		
		//instance variables for each component of the constructor.
		
		
		
        this.myBall = new GOval(this.Xinit*SCALE,(HEIGHT-this.bSize)*SCALE,2*this.bSize*SCALE,2*this.bSize*SCALE); 
		this.myBall.setFilled(true); 
		this.myBall.setFillColor(bColor);
		
		}
		
		//initial position of ball and its dimensions
		//filling the ball with color.
		
		
		
		
		
		public GOval getBall() {   return myBall; } ;
		
		/**
		 * The get method gets the myBall object and adds it to the bSim class
		 * where the GraphicsProgram may be used to add the GOvals to the canvas and simulate them.
		 * @return myBall 
		 */
		
		//adding the image to the display using the get method.
		
		
		/** 
		 * The run method implements the simulation loop from Assignment 1.   
		 * Once the start method is called on the aBall instance, the  
		 * code in the run method is executed concurrently with the main * program. 
		 * @param void 
		 * * @return void 
		 */ 
		
		
		//javadoc documentation for run method sourced from Assignment 2.
	
		
		/**
		 * The run method runs a simulation loop, the same as the one
		 * used to make the single ball bounce in Assignment 1.
		 */
		
		
		
		public void run() {  
			
			
			
			double Yo=this.bSize;	
			
			
			//size display window
					// TODO Auto-generated method stub	
		
			
							
					//Initialize Variables
					double Vt=(mass*g)/(4*Pi*this.bSize*this.bSize*k);
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
						
						double Y=this.bSize + Vt/g*(Voy+Vt)*(1-Math.exp(-g*time/Vt))-Vt*time;
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
	
						int ScrX= (int)  ((X-this.bSize)*SCALE);
						int ScrY= (int) (HEIGHT-(Y+this.bSize)*SCALE);
						
						//setting up simulation coordinates
						
						myBall.setLocation(ScrX,ScrY);
						//Screen units
						
						
						time+=TICK;
					
						
						//Large TICK, could lead to error/ball going slightly below the plank
						
						//sets time as a sum of clock tick
					
						
						if(Y<=this.bSize&&Vy<0) { 
							
							double KEx=((double) 0.5* mass* (Math.pow(Vx, 2)))*(1-this.bLoss);	
							
							//Formula for x component of kinetic energy
							
							
							 double KEy=((double) 0.5* mass* (Math.pow(Vy, 2)))*(1-this.bLoss);
							 
							//Formula for Y component of kinetic energy
							
							 
							 int sign= theta <= 90 ? 1 : -1; //takes direction into account
							 
							 
							 
							 Vox = sign*Math.sqrt(2*KEx);  // Resulting horizontal velocity 
							Voy = Math.sqrt(2*KEy);  // Resulting vertical velocity )
							
							Xo=Xlast;
							Y=this.bSize;
							time=0;
							X=0;
							
							
							//parameters for bouncing of the ball to occur
							
							
							double KElast=KEx+KEy;
							
						
							if (KEx+KEy<ETHR || (KElast>KEx+KEy)) break;
							
							//total energy must be less than it was during the previous bounce, but greater than some energy ETHR.
			                               
			                                
			                                
			                                
			                            try {      
			                    			Thread. sleep (100);  
			                    			} catch (InterruptedException e) {   e.printStackTrace();  
			                    			} 
			                            }
			                               
						     // pause for 50 milliseconds 
						}
						
			        }
			        
		}
		
		
		
		
						
			        
			        
		
		
		
		
			                    			 
			                    	
			                    			
	
			                    
	
						
			                            
							
						
						
						
					
				
				
			
	
	
	
	
	
	
	
	
	
	
	
	
	
			
	               
	        
	        
					
			
			
			
			
	        
	    
	       
			
			
			
			
			
			
				
	


