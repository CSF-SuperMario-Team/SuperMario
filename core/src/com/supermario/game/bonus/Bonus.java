package com.supermario.game.bonus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.supermario.game.model.Player;

import java.util.ArrayList;

/**
 * Created by Анна on 06.12.2014.
 */
public abstract class Bonus {

    public abstract void make (Player player);

    static ArrayList <TextureRegion> texture;

    public static void setTexture (){

        texture = new ArrayList<TextureRegion>();
        texture.add(new TextureRegion(new Texture(Gdx.files.internal("assets/rub.png"))));
        texture.add(new TextureRegion(new Texture(Gdx.files.internal("assets/dol.png"))));
    }
}
