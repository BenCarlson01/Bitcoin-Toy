public class Hashing {

	public static void main(String args[]) {
		ParallelRunner R1 = new ParallelRunner("Thread-1");
		R1.start();

		ParallelRunner R2 = new ParallelRunner("Thread-2");
		R2.start();
	}
}

