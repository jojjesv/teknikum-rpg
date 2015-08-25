package se.tek.rpg.character;

import java.util.ArrayList;

import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Pool.Poolable;
import com.partlight.sandcat.tools.EightDirection;

import se.tek.rpg.TeknikumRpg;

public class BaseCharacter extends Image implements Poolable {

	private boolean						isMoving;
	private ArrayList<EightDirection>	alMoveQueue;
	private MoveByAction				mbaMoveAction;

	public BaseCharacter() {
		this.setSize(TeknikumRpg.GRID_SIZE, TeknikumRpg.GRID_SIZE);
		this.alMoveQueue = new ArrayList<>();
		this.mbaMoveAction = new MoveByAction() {
			@Override
			protected void end() {
				super.end();
				isMoving = false;
				if (alMoveQueue.size() > 0)
					moveFromQueue();
			}
		};
		this.mbaMoveAction.setDuration(this.getMoveDuration());
	}

	public float getMoveDuration() {
		return 0.1f;
	}

	@Override
	public void act(float delta) {
		super.act(delta);
		if (this.alMoveQueue.size() > 0)
			this.moveFromQueue();
	}

	public boolean isMoving() {
		return this.isMoving;
	}

	public void moveDown() {
		if (!this.isMoving)
			this.queueMoveDown();
	}

	@SuppressWarnings("incomplete-switch")
	protected void moveFromQueue() {
		if (this.isMoving)
			return;

		this.mbaMoveAction.reset();

		final EightDirection dir = this.alMoveQueue.get(0);
		switch (dir) {
		case NORTH:
			this.mbaMoveAction.setAmount(0, TeknikumRpg.GRID_SIZE);
			break;
		case SOUTH:
			this.mbaMoveAction.setAmount(0, -TeknikumRpg.GRID_SIZE);
			break;
		case WEST:
			this.mbaMoveAction.setAmount(-TeknikumRpg.GRID_SIZE, 0);
			break;
		case EAST:
			this.mbaMoveAction.setAmount(TeknikumRpg.GRID_SIZE, 0);
			break;
		}
		this.alMoveQueue.remove(0);

		this.isMoving = true;
		this.removeAction(this.mbaMoveAction);
		this.addAction(this.mbaMoveAction);
	}

	public void moveLeft() {
		if (!this.isMoving)
			this.queueMoveLeft();
	}

	public void moveRight() {
		if (!this.isMoving)
			this.queueMoveRight();
	}

	public void moveUp() {
		if (!this.isMoving)
			this.queueMoveUp();
	}

	public void queueMoveDown() {
		this.alMoveQueue.add(EightDirection.SOUTH);
	}

	public void queueMoveLeft() {
		this.alMoveQueue.add(EightDirection.WEST);
	}

	public void queueMoveRight() {
		this.alMoveQueue.add(EightDirection.EAST);
	}

	public void queueMoveUp() {
		this.alMoveQueue.add(EightDirection.NORTH);
	}

	@Override
	public void reset() {
		this.setDrawable(null);
	}
}
