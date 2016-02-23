package hw3;

import java.util.concurrent.Semaphore;

public class Main {
	//Semaphore used as locks for shared variables
	public static Semaphore lock = new Semaphore(1,true);
	//shared variables
	public static int menInBathroom = 0;
	public static int womenInBathroom = 0;

	public static void main(String[] args) {
		for(int i = 1; i <= 20; i++){
			Man man = new Man(i);
			Woman woman = new Woman(i);
			man.start();
			woman.start();
		}
	}

}
