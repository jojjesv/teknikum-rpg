package se.tek.rpg.hud.dialog;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.TemporalAction;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;

public class Dialog extends Group {

	private final Label				lDialogLabel;
	private String					currentDialog;
	private final TemporalAction	faDialogLabelProgression;

	public Dialog() {
		final LabelStyle style = new LabelStyle();
		style.font = new BitmapFont();
		style.fontColor = Color.GREEN;

		this.lDialogLabel = new Label("Test", style);
		this.faDialogLabelProgression = new TemporalAction() {
			@Override
			protected void update(float percent) {
				Dialog.this.updateDialogLabel(percent);
			}
		};
	}

	public void dialog(String message) {
		this.currentDialog = message;
		this.faDialogLabelProgression.setDuration(1);
		this.addActor(this.lDialogLabel);
		this.addAction(this.faDialogLabelProgression);
	}

	protected void updateDialogLabel(float percent) {
		final int length = (int)Math.floor(this.currentDialog.length() * percent);
		this.lDialogLabel.setText(this.currentDialog.substring(length));
	}
}
