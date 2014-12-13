package com.supermario.game.bonus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.supermario.game.model.Player;

import java.util.ArrayList;

/**
 * Created by Анна on 06.12.2014.
 */

public class RubBonus extends Bonus {
    BitmapFont font = new BitmapFont(Gdx.files.internal("font/1.fnt"), new Sprite(new Texture("font/1.png")), false);

    @Override
    public void make(Player player) {
        player.count += 100;
        player.setCount();
        labels.add(new Label("+100",new Label.LabelStyle(font, Color.LIGHT_GRAY)));
    }
}
