import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;

public class ParallelRunner implements Runnable {

	private Thread t;
	private String threadName;
	
	private static int amt = 11;
	private static int num_tries = 1000;

	ParallelRunner(String name) {
		threadName = name;
		//System.out.println("Creating " + threadName);
	}

	public void run() {
		//System.out.println("Running " + threadName);
		SecureRandom random = new SecureRandom();
		int result = random.nextInt(1000000);
		String resultStr;
		try {
			for (int i = 0; i < num_tries; i++) {
				//System.out.println("Thread: " + threadName + ", " + i);
				try {
					result += 1;
					resultStr = result + "";
					MessageDigest digest = MessageDigest.getInstance("SHA-256");
					byte[] bytehash = digest.digest(resultStr.getBytes(StandardCharsets.UTF_8));
					String hash = bytesToHex(bytehash);
					String bithash = new BigInteger(hash, 16).toString(2);
					boolean check = true;
					for (int j = bithash.length() - 1; j > bithash.length() - 1 - amt; j--) {
						if (bithash.charAt(j) != '0') {
							check = false;
							break;
						}
					}
					if (check) {
						System.out.print("Got a Hash with " + amt + " zero bytes: ");
						System.out.print(hash);
						System.out.println();
					}
				} catch (Exception e) {
		
				}
				// Let the thread sleep for a while.
				Thread.sleep(50);
			}
		} catch (InterruptedException e) {
			//System.out.println("Thread " + threadName + " interrupted.");
		}
		//System.out.println("Thread " + threadName + " exiting.");
	}
	private static String bytesToHex(byte[] hash) {
	    StringBuffer hexString = new StringBuffer();
	    for (int i = 0; i < hash.length; i++) {
	    String hex = Integer.toHexString(0xff & hash[i]);
	    if(hex.length() == 1) hexString.append('0');
	        hexString.append(hex);
	    }
	    return hexString.toString();
	}

	public void start() {
		//System.out.println("Starting " + threadName);
		if (t == null) {
			t = new Thread(this, threadName);
			t.start();
		}
	}
}