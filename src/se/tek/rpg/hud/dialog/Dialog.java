package se.tek.rpg.hud.dialog;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;

public class Dialog extends Actor {

	private Label	lDialogLabel;
	private String	dialog;

	public Dialog() {
		final LabelStyle style = new LabelStyle();
		style.font = new BitmapFont();
		style.fontColor = Color.WHITE;

		this.lDialogLabel = new Label("", style);
	}

	public void dialog(String message) {
		this.dialog = message;
		System.out.println(message);
	}
}
