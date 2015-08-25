package se.tek.rpg.hud.dialog;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.Thread.State;

import com.badlogic.gdx.Gdx;

public class DebugDialog extends Dialog implements Runnable {

	private String userInput;
	private Thread tThread;

	public void getInput() {
		if (this.tThread == null)
			this.tThread = new Thread(this);
		
		final State state = this.tThread.getState();
		if (state != State.NEW && state != State.TERMINATED)
			try {
				this.tThread.join();
			} catch (InterruptedException e) {
			}
		
		
		this.tThread.run();
	}

	@Override
	public void run() {
//		final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//		
//		try {
//			
////			System.out.println(reader.readLine());
////			
//			if ((userInput = reader.readLine()) != null) {
//				this.dialog(userInput);
//			}
//		} catch (IOException e) {
//			Gdx.app.error("Teknikum RPG", "Debug dialog IO error.");
//			Gdx.app.exit();
//		}
	}
}
