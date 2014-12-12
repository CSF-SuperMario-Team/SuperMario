package com.supermario.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Created by Анна on 30.11.2014.
 */

//закончились жизни или уровни пройдены
public class EndGameScreen extends SuperMarioScreen {

    Sprite sprite;

    public EndGameScreen() {

    }

    @Override
    public void render(float delta) {
        super.render(delta);
        batch.begin();
        sprite.draw(batch);
        batch.end();
    }

    @Override
    public void show() {
        super.show();
        sprite = new Sprite(new Texture(Gdx.files.internal("assets/end.png"))) {
            {
                setX(0);
                setY(0);
            }
        };
    }
}
