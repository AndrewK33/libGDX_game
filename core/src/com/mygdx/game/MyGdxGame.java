package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class MyGdxGame extends ApplicationAdapter {
	private SpriteBatch batch;
	private Texture img;
	private Texture coinSprite;
	private CoinAnimation coinAnimation;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		coinSprite = new Texture("coinSprite.png");
		coinAnimation = new CoinAnimation("coinSprite.png", 1, 6, 15, Animation.PlayMode.LOOP);
	}

	@Override
	public void render () {
		ScreenUtils.clear(1, 1, 1, 1);
		coinAnimation.setTime(Gdx.graphics.getDeltaTime());
		float x = Gdx.input.getX() - coinAnimation.draw().getRegionWidth()/2;
		float y = Gdx.graphics.getHeight() - (Gdx.input.getY() + coinAnimation.draw().getRegionHeight()/2);

		batch.begin();
		batch.draw(coinAnimation.draw(), x, y);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		coinSprite.dispose();
		coinAnimation.dispose();
	}
}
