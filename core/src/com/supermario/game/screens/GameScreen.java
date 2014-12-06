package com.supermario.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.supermario.game.bonus.Bonus;
import com.supermario.game.model.Map;
import com.supermario.game.model.Player;
import com.supermario.game.renderers.MapRenderer;

/**
 * Created by Анна on 30.11.2014.
 */

//экран игры
public class GameScreen extends SuperMarioScreen {

    MapRenderer mapRenderer;
    Map map;

    @Override
    public void show() {
        map = new Map();
        Bonus.setTexture();

        super.show();

        mapRenderer = new MapRenderer(map);

        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean keyDown(int keycode) {
                switch (keycode) {
                    case Input.Keys.LEFT:
                        map.player.left();
                        break;
                    case Input.Keys.RIGHT:
                        map.player.right();
                        break;
                    case Input.Keys.SPACE:
                        map.player.up();
                        break;
                    default:
                        break;
                }

                return false;
            }
        });
    }

    @Override
    public void render(float delta) {
        super.render(delta);
    }
}
