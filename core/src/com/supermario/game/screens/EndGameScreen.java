package com.supermario.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.supermario.game.SuperMario;

import java.awt.*;

/**
 * Created by Анна on 30.11.2014.
 */

//закончились жизни или уровни пройдены
public class EndGameScreen extends SuperMarioScreen {

    Sprite sprite;

    public EndGameScreen(Game g) {
        this.game = g;
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        batch.begin();
//        batch.draw(sprite.getTexture(),0,0,Window.WIDTH,Window.HEIGHT);
        sprite.draw(batch);
//        batch.draw(sprite.getTexture(),0,0);
        batch.end();
    }

    @Override
    public void show() {
        super.show();
        sprite = new Sprite(new Texture(Gdx.files.internal("assets/loose.png"))) {
            {
                setX(0);
                setY(0);
            }
        };

        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                screenY = SuperMario.HEIGHT - screenY; //инверсия координаты у
                if(SuperMario.currentLevel<4) {
                    if (new Rectangle(0, 0, 600, 420).contains(screenX, screenY)) //нажали на начало игры
                        game.setScreen(new GameScreen(game));
                }

                return true;
            }
        });
    }
}
