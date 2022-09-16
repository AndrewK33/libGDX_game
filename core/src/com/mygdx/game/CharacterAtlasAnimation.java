package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class CharacterAtlasAnimation {
    TextureAtlas textureAtlas;
    Animation<TextureAtlas.AtlasRegion> textureAtlasAnimation;
    private float time;

    public CharacterAtlasAnimation(String atlas, String name, float fps, Animation.PlayMode playMode){
        time = 0;
        this.textureAtlas = new TextureAtlas(atlas);
        textureAtlasAnimation = new Animation<>(1/fps, this.textureAtlas.findRegions(name));
        textureAtlasAnimation.setPlayMode(playMode);
    }

    public TextureRegion draw() {
        return textureAtlasAnimation.getKeyFrame(time);
    }

    public void setTime(float dT){
        time += dT;
    }

    public void dispose(){
        this.textureAtlas.dispose();
    }



}
