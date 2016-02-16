package pl.nikowis.PiMonteCarlo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MyMain {
	public static int getNumOfThreads(){
		String numberOfThreads="";
		int numOfThreads=0;
		
		System.out.println("Input the number of threads to run : ");
		try {
			numberOfThreads= new BufferedReader(new InputStreamReader(System.in)).readLine();
		} catch (IOException e1) {
			System.out.println(e1.getMessage());
		}
		try{
		numOfThreads = Integer.parseInt(numberOfThreads);
		}catch(Exception e){
			System.out.println("Wrong input format, setting default value 10.");
			numOfThreads=10;
		}
		
		return numOfThreads;
	}
	public static int getNumOfDraws(){
		String numberOfDraws="";
		int numOfDraws=0;
		System.out.println("Input the number of draws to do : ");
		try {
			numberOfDraws= new BufferedReader(new InputStreamReader(System.in)).readLine();
		} catch (IOException e1) {
			System.out.println(e1.getMessage());
		}
		try{
		numOfDraws = Integer.parseInt(numberOfDraws);
		}catch(Exception e){
			System.out.println("Wrong input format, setting default value 1000000.");
			numOfDraws=1000000;
		}
		
		return numOfDraws;
	}
	
	public static void main(String[] args) {
		int numOfThreads;
		int numOfDraws;
		Counters counters = new Counters();
		Thread [] threads;

		numOfThreads =getNumOfThreads();
		numOfDraws = getNumOfDraws();
		
		threads = new Thread[numOfThreads];
		for(int i=0;i<numOfThreads-1;i++){
			threads[i] = new Thread(new WorkingClass(counters, numOfDraws/numOfThreads));
			threads[i].start();
		}
		threads[numOfThreads-1] = new Thread(new WorkingClass(counters, numOfDraws-(numOfThreads-1)*numOfDraws/numOfThreads));
		threads[numOfThreads-1].start();

		for(int i=0;i<numOfThreads;i++){
			try {
				threads[i].join();
			} catch (InterruptedException e) {
				System.out.println(e.getMessage());
			}
		}
		
		System.out.println(4*(double)counters.getInside()/counters.getOverall());
		System.out.println("The end...");
	}
}
