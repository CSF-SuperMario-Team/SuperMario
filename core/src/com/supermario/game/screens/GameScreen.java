package com.supermario.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.supermario.game.SuperMario;
import com.supermario.game.bonus.Bonus;
import com.supermario.game.model.Map;
import com.supermario.game.renderers.MapRenderer;

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
            map.player.dx = map.player.SpeedX;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            map.player.dx = -map.player.SpeedX;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            if (map.player.grounded) {
                map.player.grounded = false;
                map.player.dy = map.player.SpeedY;
            }
        }
        map.player.playerMove();
        if ((map.player.point.x > SuperMario.WIDTH/2) && (map.player.point.x < map.getWidth() * map.cellSize - SuperMario.WIDTH/2)) {//не даем камере выйти за пределы карты
            camera.position.x = map.player.point.x;
        }
        if ((map.player.point.y > SuperMario.HEIGHT/2) && (map.player.point.y < (map.getHeight()-1) * map.cellSize - SuperMario.HEIGHT/2)) {
            camera.position.y = map.player.point.y;
        }
        camera.update();
    }

    @Override
    public void show() {
        map = new Map();
        Bonus.setTexture();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, SuperMario.WIDTH, SuperMario.HEIGHT);
        super.show();
    }


}
