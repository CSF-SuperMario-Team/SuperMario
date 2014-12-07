package com.supermario.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.supermario.game.bonus.Bonus;
import com.supermario.game.model.Map;
import com.supermario.game.model.Player;
import com.supermario.game.renderers.MapRenderer;

import java.awt.*;

/**
 * Created by Анна on 30.11.2014.
 */

//экран игры
public class GameScreen extends SuperMarioScreen {

    MapRenderer mapRenderer;
    Map map;
    OrthographicCamera camera;

    @Override
    public void render(float delta) {
        super.render(delta);
        batch.setProjectionMatrix(camera.combined);
        mapRenderer = new MapRenderer(map);
        mapRenderer.render(batch);
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            map.player.dx = 300;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            map.player.dx = -300;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            if (map.player.grounded) {
                map.player.grounded = false;
                map.player.dy = 500;
            }
        }
        map.player.playerMove();
        if ((map.player.point.x > 300) && (map.player.point.x < 200 * 30 - 300)) {//не даем камере выйти за пределы карты
            camera.position.x = map.player.point.x;
        }
        if ((map.player.point.y > 209) && (map.player.point.y < 18 * 30 - 209)) {
            camera.position.y = map.player.point.y;
        }
        camera.update();
    }

    @Override
    public void show() {
        map = new Map();
        Bonus.setTexture();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 600, 418);
        super.show();
    }


}
