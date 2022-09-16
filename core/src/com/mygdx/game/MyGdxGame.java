package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.ScreenUtils;

public class MyGdxGame extends ApplicationAdapter {
	private SpriteBatch batch;
	private Texture coinSprite;
	private CharacterAtlasAnimation runRight, runLeft, characterStand, characterState;
	private Music music;
	private Sound sound;
	private MainInputProcessor mainInputProcessor;
	private float x, y;
	int dir = 0, step = 1;
	
	@Override
	public void create () {
		initProcessor();
		initAnimation();
		initMusic();
		initSound();
	}


	public void initAnimation() {
		batch = new SpriteBatch();
		coinSprite = new Texture(Gdx.files.internal("coinSprite.png"));
		runRight = new CharacterAtlasAnimation("characterAtlas/walkAtlas.atlas", "walk_right", 9, Animation.PlayMode.LOOP);
		characterStand = new CharacterAtlasAnimation("characterAtlas/walkAtlas.atlas", "stand", 5, Animation.PlayMode.LOOP);
		characterState = characterStand;
	}
	public void initMusic() {
		music = Gdx.audio.newMusic(Gdx.files.internal("mainTitle.mp3"));
		music.setPan(0, 0.2f);
		music.setLooping(true);
		music.play();
	}
	public void initSound() {
		sound = Gdx.audio.newSound(Gdx.files.internal("hitTheWeapon.wav"));
	}
	public void initProcessor() {
		mainInputProcessor = new MainInputProcessor();
		Gdx.input.setInputProcessor(mainInputProcessor);
	}



	@Override
	public void render () {
		ScreenUtils.clear(1, 1, 1, 1);
		characterState = characterStand;
		dir = 0;

		if (mainInputProcessor.getOutString().contains("A")) {
			dir = -1;
			characterState = runRight;
		}
		if (mainInputProcessor.getOutString().contains("D")) {
			dir = 1;
			characterState = runRight;
		}
		if (mainInputProcessor.getOutString().contains("W")) {
			y++;

		}
		if (mainInputProcessor.getOutString().contains("S")) {
			y--;
		}
		if (mainInputProcessor.getOutString().contains("Space")) {
			x = Gdx.graphics.getWidth()/2;
			y = Gdx.graphics.getHeight()/2;
		}

		if (dir == -1) x-=step;
		if (dir == 1) x+=step;

		TextureRegion tmp = characterState.draw();
		if (!characterState.draw().isFlipX() & dir == -1) characterState.draw().flip(true, false);
		if (characterState.draw().isFlipX() & dir == 1) characterState.draw().flip(true, false);

		characterState.setTime(Gdx.graphics.getDeltaTime());
		batch.begin();
		batch.draw(characterState.draw(), x, y);
		batch.end();


		if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) sound.play();


	}

	@Override
	public void dispose () {
		batch.dispose();
		coinSprite.dispose();
		runRight.dispose();
		characterState.dispose();
		characterStand.dispose();
		music.dispose();
		sound.dispose();
	}

}
