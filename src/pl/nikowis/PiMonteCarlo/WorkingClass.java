package pl.nikowis.PiMonteCarlo;

import java.util.concurrent.ThreadLocalRandom;

public class WorkingClass implements Runnable {
	private Counters c;
	private int timesToRepeat;
	private double x,y;
	private ThreadLocalRandom rand;
	public WorkingClass(Counters c, int timesToRepeat){
		this.c=c;
		this.timesToRepeat = timesToRepeat;
		rand = ThreadLocalRandom.current();
		}
	
	@Override
	public void run() {
		for(int i =0;i<timesToRepeat;i++){
			x=rand.nextDouble(0,1+Double.MIN_VALUE);
			y=rand.nextDouble(0, 1+Double.MIN_VALUE);
			synchronized(c){
				if(Math.sqrt(x*x+y*y)<=1.0)
					c.incrementInside();
				c.incrementOverall();
			}
		}
	}
}
