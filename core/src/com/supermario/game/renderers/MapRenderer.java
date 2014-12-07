package com.supermario.game.renderers;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.supermario.game.model.Map;

/**
 * Created by Анна on 30.11.2014.
 */
public class MapRenderer {

    Map map;
    OrthographicCamera cam;


    public MapRenderer (Map map) {
        this.map = map;
        this.cam = new OrthographicCamera(24, 16);


    }

    public void render(SpriteBatch batch) {
        map.drawMap(batch);
        batch.begin();
        map.player.playerSprite.draw(batch);
        batch.end();
    }

}
