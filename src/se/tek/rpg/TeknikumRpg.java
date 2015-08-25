package se.tek.rpg;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.partlight.sandcat.SandCatGame;

import se.tek.rpg.screen.BaseCampaign;

public class TeknikumRpg extends SandCatGame{
	public static final float GRID_SIZE = 48;
	
	@Override
	public void create() {
		super.create();
		this.bgR = 1;
		this.bgG = 1;
		this.bgB = 1;
		this.setScreen(new BaseCampaign());
	}
	
	@Override
	public void render() {
		super.render();
		if (Gdx.input.isKeyPressed(Keys.ESCAPE))
			Gdx.app.exit();
	}
}
