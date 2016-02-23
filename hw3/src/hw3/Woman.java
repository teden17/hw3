package hw3;

//Class that represents a woman that works and go to bathroom
//extends Thread
public class Woman extends Thread{
	int ID;
	
	public Woman(int ID){
		this.ID = ID;
	}
	@Override
	public void run() {
		while(true){
			doWorkstuff();
			int menInBathroom = 1;
			while(menInBathroom!=0){//While there are men in bathroom cant enter
				Main.lock.acquireUninterruptibly();
				menInBathroom = Main.menInBathroom;
				if(menInBathroom == 0){//when no men in bathroom do bathroom
					doBathroom();
				}
				
				Main.lock.release();
			}
			
		}
	}

	//Method that represents a bathroom visit
	private void doBathroom() {
		Main.womenInBathroom++;
		Main.lock.release();
		double bathroomtime = Math.random()*2000;
		System.out.println("woman "+ ID + " does some ladystuff in bathroom ");
		try {
			Thread.sleep((long) bathroomtime);
		} catch (InterruptedException e) {
			System.out.println("could not manage to work");
		}
		Main.lock.acquireUninterruptibly();
		Main.womenInBathroom--;
	}
	
	//method that represent a work session
	private void doWorkstuff() {
		double worktime = Math.random()*10000;
		System.out.println("woman "+ ID + " gonna work for a while");
		try {
			Thread.sleep((long) worktime);
		} catch (InterruptedException e) {
			System.out.println("could not manage to work");
		}
		
	}
}
