import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;

public class ParallelRunner implements Runnable {

	private Thread t;
	private String threadName;

	ParallelRunner(String name) {
		threadName = name;
		System.out.println("Creating " + threadName);
	}

	public void run() {
		System.out.println("Running " + threadName);
		SecureRandom random = new SecureRandom();
		try {
			for (int i = 0; i < 10; i++) {
				System.out.println("Thread: " + threadName + ", " + i);
				int result = random.nextInt(1000000);
				String resultStr = result + "";
				if (resultStr.length() != 6) {
					for (int x = resultStr.length(); x < 6; x++) {
						resultStr = "0" + resultStr;
					}
				}
				try {
					MessageDigest digest = MessageDigest.getInstance("SHA-256");
					byte[] hash = digest.digest(resultStr.getBytes(StandardCharsets.UTF_8));
					for (int j = 0; j < hash.length; j++) {
						System.out.print(hash[j]);
					}
					System.out.println();
				} catch (Exception e) {
		
				}
				// Let the thread sleep for a while.
				Thread.sleep(50);
			}
		} catch (InterruptedException e) {
			System.out.println("Thread " + threadName + " interrupted.");
		}
		System.out.println("Thread " + threadName + " exiting.");
	}

	public void start() {
		System.out.println("Starting " + threadName);
		if (t == null) {
			t = new Thread(this, threadName);
			t.start();
		}
	}
}