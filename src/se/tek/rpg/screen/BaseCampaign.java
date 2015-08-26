package se.tek.rpg.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.partlight.sandcat.debug.DebugInputDialog;

import se.tek.rpg.CampaignStage;

public class BaseCampaign extends ScreenAdapter{
	
	private CampaignStage sMainStage;
	
	public BaseCampaign() {
		this.sMainStage = new CampaignStage();
		this.sMainStage.createPlayer();
		this.sMainStage.addActor(this.sMainStage.getPlayer());
		this.sMainStage.createDialog(true);
		this.sMainStage.addActor(this.sMainStage.getDialog());
		
		Gdx.input.setInputProcessor(this.sMainStage);
	}
	
	@Override
	public void show() {
	}
	
	@Override
	public void render(float delta) {
		super.render(delta);
		if (!DebugInputDialog.input.contentEquals("")){
			this.sMainStage.getDialog().dialog(DebugInputDialog.input);
		}
		this.sMainStage.act();
		this.sMainStage.draw();
	}
	
}
