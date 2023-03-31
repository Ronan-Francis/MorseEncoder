package ie.atu.sw;

import javax.swing.SwingUtilities;

public class Runner {
	public static void main(String[] args) {
		/*
		 * A GUI should always be executed in a different thread
		 * to that which launches it. This way, if the GUI
		 * crashes, the calling thread will have a chance to handle
		 * the error and present the user with a reason for the
		 * problem and (hopefully) suggest a solution.
		 * 
		 * Don't mess with this unless you know exactly what
		 * you're doing. This is really a queue in action. The
		 * method invokeLater() places the runnable task on Swing's
		 * event dispatch queue and executes it when it gets a 
		 * chance. In practice this is instantaneous, but there
		 * are no guarantees. Invoke later means just that...
		 */
		SwingUtilities.invokeLater(new Runnable() {
		    public void run() {
		        new MorseWindow();
		    }
		});
	}
}