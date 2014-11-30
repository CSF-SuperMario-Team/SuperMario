package com.supermario.game.renderers;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.supermario.game.model.Bonus;
import com.supermario.game.model.Map;

/**
 * Created by Анна on 30.11.2014.
 */
public class MapRenderer {

    Map map;
    Bonus bonus;
    OrthographicCamera cam;


    public MapRenderer (Map map) {
        this.map = map;
        this.cam = new OrthographicCamera(24, 16);
        //this.cam.position.set(map.player.pos.x, map.player.pos.y, 0);
        //this.cache = new SpriteCache(this.map.tiles.length * this.map.tiles[0].length, false);
        //this.blocks = new int[(int)Math.ceil(this.map.tiles.length / 24.0f)][(int)Math.ceil(this.map.tiles[0].length / 16.0f)];

        //createAnimations();
        //createBlocks();
    }

}
