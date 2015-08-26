package se.tek.rpg.hud.dialog;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.TemporalAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.utils.Align;
import com.partlight.sandcat.assets.SandCatAssets;

public class Dialog extends Group {

	private String					currentDialog	= "";
	private boolean					showing;
	private final Image				iBackground;
	private final Label				lDialogLabel;
	private final TemporalAction	faDialogLabelProgression;

	public Dialog() {
		this.iBackground = new Image(SandCatAssets.tPixel);

		final LabelStyle style = new LabelStyle();
		style.font = CommonAssets.bfDialog;
		style.fontColor = Color.WHITE;

		final Label label = this.lDialogLabel = new Label("", style);
		this.faDialogLabelProgression = new TemporalAction() {
			@Override
			protected void begin() {
				super.begin();
				Dialog.this.animateDialogLabel();
			}

			@Override
			protected void update(float percent) {
				Dialog.this.updateDialogLabel(percent);
			}
		};
		label.setAlignment(Align.bottomLeft);

		this.setTransform(true);
		this.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight() * 0.25f);
		this.setOrigin(Align.center);
		this.iBackground.setSize(this.getWidth(), this.getHeight());
	}

	public void dialog(String message) {
		
		final Image background = this.iBackground;
		final Label label = this.lDialogLabel;
		final float duration = 1f * (message.length() / 32f);
		final TemporalAction action = this.faDialogLabelProgression;

		this.getColor().a = 0;
		this.setScale(0.75f);

		if (!background.hasParent())
			this.addActor(background);

		this.addAction(Actions.parallel(Actions.scaleTo(1, 1, 0.25f, Interpolation.exp10Out), Actions.fadeIn(0.25f, Interpolation.exp10Out),
				Actions.delay(0.25f, action)));

		label.setText(message);
		label.setX(this.getWidth() * 0.05f);
		label.setY((this.getHeight() - label.getPrefHeight()) * 0.5f);
		label.setVisible(false);
		action.setDuration(duration);
		action.restart();

		this.currentDialog = message;
	}

	protected void animateDialogLabel() {
		final Label label = this.lDialogLabel;
		if (!label.hasParent())
			this.addActor(label);
		label.setVisible(true);
	}

	protected void updateDialogLabel(float percent) {
		final String message = this.currentDialog;
		int length = (int) Math.ceil(message.length() * percent);

		// Skip white space
		{
			if (message.charAt(length - 1) == ' ' && length < message.length())
				length++;
		}

		this.lDialogLabel.setText(message.substring(0, length));
	}
}
