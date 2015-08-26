package se.tek.rpg;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.partlight.sandcat.SandCatGame;
import com.partlight.sandcat.debug.DebugInputDialog;

import se.tek.rpg.hud.dialog.CommonAssets;
import se.tek.rpg.screen.BaseCampaign;

public class TeknikumRpg extends SandCatGame {
	public static final float	GRID_SIZE	= 48;
	public static final boolean	DEBUG		= true;

	private DebugInputDialog didDebugInput;

	@Override
	public void create() {
		super.create();
		this.bgR = 100f / 255f;
		this.bgG = 149f / 255f;
		this.bgB = 237f / 255f;
		
		CommonAssets.bfDialog = new BitmapFont(Gdx.files.internal("bin/font.fnt"));

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
