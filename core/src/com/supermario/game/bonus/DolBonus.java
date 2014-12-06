package com.supermario.game.bonus;

import com.supermario.game.model.Player;

/**
 * Created by Анна on 06.12.2014.
 */
public class DolBonus extends Bonus {

    @Override
    public void make(Player player) {
        player.count -= 30;
    }
}
