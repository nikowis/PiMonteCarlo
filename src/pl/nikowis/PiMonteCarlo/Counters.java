package pl.nikowis.PiMonteCarlo;

public class Counters {
	private int inside;
	private int overall;
	
	public Counters(){
		inside = overall = 0;
	}
	
	public void incrementInside(){inside++;}
	public void incrementOverall(){overall++;}
	public int getInside(){return inside;}
	public int getOverall(){return overall;}
}
