package se.tek.rpg;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.Viewport;

import se.tek.rpg.character.BaseCharacter;
import se.tek.rpg.character.player.Player;
import se.tek.rpg.character.pool.NpcCharacterPool;
import se.tek.rpg.hud.dialog.DebugDialog;
import se.tek.rpg.hud.dialog.Dialog;

public class CampaignStage extends Stage {

	private Array<BaseCharacter>	bcCharacters;
	private NpcCharacterPool		ncpNpcPool;
	private Player					pPlayer;
	private Dialog					dDialog;

	public CampaignStage() {
		super();
		this.postConstructor();
	}

	public CampaignStage(Viewport viewport) {
		super(viewport);
		this.postConstructor();
	}

	public CampaignStage(Viewport viewport, Batch batch) {
		super(viewport, batch);
		this.postConstructor();
	}

	public void createDialog(boolean debug) throws IllegalStateException {
		if (this.dDialog != null)
			throw new IllegalStateException();

		if (debug)
			this.dDialog = new DebugDialog();
		else
			this.dDialog = new Dialog();
	}

	public void createPlayer() throws IllegalStateException {
		if (this.pPlayer != null)
			throw new IllegalStateException();

		this.pPlayer = new Player();
	}
	
	@Override
	public boolean keyDown(int keyCode) {
		if (keyCode == Keys.Q)
			if (this.dDialog instanceof DebugDialog)
				((DebugDialog)this.dDialog).getInput();
		return super.keyDown(keyCode);
	}
	
	@Override
	public boolean keyTyped(char character) {
		return super.keyTyped(character);
	}

	public Player getPlayer() {
		return this.pPlayer;
	}
	
	public Dialog getDialog(){
		return this.dDialog;
	}

	public void addCharacter(BaseCharacter character) {
		this.bcCharacters.add(character);
		this.addActor(character);
	}

	public void addNpc() {
		this.addCharacter(this.ncpNpcPool.obtain());
	}

	public void clearCharacters() {
		for (BaseCharacter character : this.bcCharacters)
			character.remove();
	}

	public NpcCharacterPool getNpcPool() {
		return this.ncpNpcPool;
	}

	private void postConstructor() {
		this.bcCharacters = new Array<BaseCharacter>();
		this.ncpNpcPool = new NpcCharacterPool();
	}

	public void removeCharacter(BaseCharacter character) {
		this.bcCharacters.removeValue(character, true);
		character.remove();
	}
}
