package se.tek.rpg;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.partlight.sandcat.SandCatGame;

import se.tek.rpg.debug.DebugInputDialog;
import se.tek.rpg.screen.BaseCampaign;

public class TeknikumRpg extends SandCatGame {
	public static final float	GRID_SIZE	= 48;
	public static final boolean	DEBUG		= true;

	private DebugInputDialog didDebugInput;

	@Override
	public void create() {
		super.create();
		this.bgR = 1;
		this.bgG = 1;
		this.bgB = 1;

		this.setScreen(new BaseCampaign());
	}

	public void getDebugInput() {
		if (!TeknikumRpg.DEBUG)
			return;

		if (this.didDebugInput == null)
			this.didDebugInput = new DebugInputDialog();
		this.didDebugInput.setVisible(true);
	}

	@Override
	public void render() {
		super.render();
		if (Gdx.input.isKeyPressed(Keys.ESCAPE))
			Gdx.app.exit();

		DebugInputDialog.input = DebugInputDialog.lastInput.toString();
		DebugInputDialog.lastInput = "";
	}
}
