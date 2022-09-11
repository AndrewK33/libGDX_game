package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class CoinAnimation {
    Texture imgTexture;
    Animation<TextureRegion> animationTextureRegion;
    private float time;

    public CoinAnimation(String name, int row, int col, float fps, Animation.PlayMode playMode){
        time = 0;
        imgTexture = new Texture(name);
        TextureRegion reg1 = new TextureRegion(imgTexture);
        TextureRegion[][] regions = reg1.split(imgTexture.getWidth()/col, imgTexture.getHeight()/row);
        TextureRegion[] tmp = new TextureRegion[regions.length*regions[0].length];
        int cnt = 0;
        for (int i = 0; i < regions.length; i++) {
            for (int j = 0; j < regions[0].length; j++) {
                tmp[cnt++] = regions[i][j];
            }
        }
        animationTextureRegion = new Animation<>(1/fps, tmp);
        animationTextureRegion.setPlayMode(playMode);
    }

    public TextureRegion draw() {
        return animationTextureRegion.getKeyFrame(time);
    }

    public void setTime(float dT){
        time += dT;
    }

    public void dispose(){
        this.imgTexture.dispose();
    }

}
