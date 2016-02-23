package hw3;

//Class that represents a man that works and go to bathroom
//extends Thread
public class Man extends Thread{
	int ID;

	public Man(int ID){
		this.ID = ID;
	}
	@Override
	public void run() {
		while(true){
			doWorkstuff();
			int womenInBathroom = 1;
			while(womenInBathroom!=0){//While there are women in bathroom cant enter
				Main.lock.acquireUninterruptibly();
				womenInBathroom = Main.womenInBathroom;
				if(womenInBathroom == 0){//when no women in bathroom do bathroom
					doBathroom();
				}
				
				Main.lock.release();
			}
			
		}
	}

	//Method that represents a bathroom visit
	private void doBathroom() {
		Main.menInBathroom++;
		Main.lock.release();
		double bathroomtime = Math.random()*2000;
		System.out.println("man "+ ID + " going to bathroom to sink some "
				+ "battleships");
		try {
			Thread.sleep((long) bathroomtime);
		} catch (InterruptedException e) {
			System.out.println("could not manage to work");
		}
		Main.lock.acquireUninterruptibly();
		Main.menInBathroom--;
	}
	
	//method that represent a work session
	private void doWorkstuff() {
		double worktime = Math.random()*10000;
		System.out.println("man "+ ID + " gonna work for a while");
		try {
			Thread.sleep((long) worktime);
		} catch (InterruptedException e) {
			System.out.println("could not manage to work");
		}	
	}
}
