public class Hashing {

	public static void main(String args[]) {
		ParallelRunner R1 = new ParallelRunner("Thread-1");
		R1.start();

		ParallelRunner R2 = new ParallelRunner("Thread-2");
		R2.start();
		
		ParallelRunner R3 = new ParallelRunner("Thread-3");
		R3.start();
		
		ParallelRunner R4 = new ParallelRunner("Thread-4");
		R4.start();
		
		ParallelRunner R5 = new ParallelRunner("Thread-5");
		R5.start();
		
		ParallelRunner R6 = new ParallelRunner("Thread-6");
		R6.start();
		
		ParallelRunner R7 = new ParallelRunner("Thread-7");
		R7.start();
		
		ParallelRunner R8 = new ParallelRunner("Thread-8");
		R8.start();
	}
}

