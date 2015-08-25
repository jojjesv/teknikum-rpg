package se.tek.rpg.character.pool;

import com.badlogic.gdx.utils.Pool;

import se.tek.rpg.character.BaseCharacter;

public class NpcCharacterPool extends Pool<BaseCharacter>{
	@Override
	protected BaseCharacter newObject() {
		return new BaseCharacter();
	}
}
