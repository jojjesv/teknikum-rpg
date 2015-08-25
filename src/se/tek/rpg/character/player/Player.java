package se.tek.rpg.character.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.partlight.sandcat.assets.SandCatAssets;

import se.tek.rpg.character.BaseCharacter;

public class Player extends BaseCharacter {

	private int	keyLeft		= Keys.LEFT;
	private int	keyRight	= Keys.RIGHT;
	private int	keyUp		= Keys.UP;
	private int	keyDown		= Keys.DOWN;
	private int	currentMoveKey;

	public Player() {
		super();
		this.setDrawable(new TextureRegionDrawable(new TextureRegion(SandCatAssets.tPixel)));
		this.setColor(Color.BLACK);
	}

	@Override
	public void act(float delta) {
		super.act(delta);

		final Input input = Gdx.input;

		if (this.currentMoveKey == -1)
			if (input.isKeyPressed(keyLeft)) {
				this.currentMoveKey = keyLeft;
			} else if (input.isKeyPressed(keyRight)) {
				this.currentMoveKey = keyRight;
			} else if (input.isKeyPressed(keyUp)) {
				this.currentMoveKey = keyUp;
			} else if (input.isKeyPressed(keyDown)) {
				this.currentMoveKey = keyDown;
			}

		if (!input.isKeyPressed(this.currentMoveKey))
			this.currentMoveKey = -1;
		
		if (this.currentMoveKey == this.keyLeft)
			this.moveLeft();
		else if (this.currentMoveKey == this.keyRight)
			this.moveRight();
		else if (this.currentMoveKey == this.keyUp)
			this.moveUp();
		else if (this.currentMoveKey == this.keyDown)
			this.moveDown();
	}
}
