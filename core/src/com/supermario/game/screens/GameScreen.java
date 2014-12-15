package com.supermario.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.supermario.game.SuperMario;
import com.supermario.game.bonus.Bonus;
import com.supermario.game.enemy.EnemyWalker;
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


    public GameScreen(Game g) {
        this.game = g;
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        batch.setProjectionMatrix(camera.combined);
        mapRenderer.render(batch);
        if (!map.player.stunned) {
            if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
                map.player.dx = map.player.SpeedX;
                map.player.playerAnimation(-1);
            }
            if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
                map.player.dx = -map.player.SpeedX;
                map.player.playerAnimation(1);
            }
            if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
                if (map.player.grounded) {
                    map.player.grounded = false;
                    map.player.dy = map.player.SpeedY;
                }
            }
        }
        map.player.playerMove();
        for (EnemyWalker e : map.enemies) {
            e.moving();
        }
        if (map.player.countLife == 0 || map.player.isFinished) {
            game.setScreen(new EndGameScreen());
        }
        if ((map.player.point.x > SuperMario.WIDTH / 2) && (map.player.point.x < map.getWidth() * map.cellSize - SuperMario.WIDTH / 2)) {//не даем камере выйти за пределы карты
            camera.position.x = map.player.point.x;
            map.player.liveSprite.setX(map.player.point.x - SuperMario.WIDTH / 2 + 10);
            map.player.labelPoints.setX(map.player.point.x + SuperMario.WIDTH / 2 - 70);
        }
        if ((map.player.point.y > SuperMario.HEIGHT / 2) && (map.player.point.y < (map.getHeight() - 1) * map.cellSize - SuperMario.HEIGHT / 2)) {
            camera.position.y = map.player.point.y;
            map.player.liveSprite.setY(map.player.point.y + 6 * map.cellSize);
            map.player.labelPoints.setY(map.player.point.y + 6 * map.cellSize);
        }
        camera.update();
    }

    @Override
    public void show() {
        super.show();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, SuperMario.WIDTH, SuperMario.HEIGHT);
        camera.position.x = SuperMario.WIDTH / 2;
        camera.position.y = SuperMario.HEIGHT / 2;
        map = new Map(batch);
        mapRenderer = new MapRenderer(map);
        Bonus.setTexture();
    }
}