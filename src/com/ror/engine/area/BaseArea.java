package com.ror.engine.area;

import com.ror.engine.battle.MobBattleMechanic;
import com.ror.engine.design.Stats;
import com.ror.models.*;
import java.util.*;

public abstract class BaseArea {

    // shared by all areas
    protected static Random rand = new Random();
    protected static Scanner scan = new Scanner(System.in);
    protected MobBattleMechanic mobBattle = new MobBattleMechanic();
    protected Stats rewardHandler = new Stats();

    // default state
    protected int currentAreaIndex = 0;
    protected boolean isExiting = false;

    public abstract void enter(Hero hero);

    public abstract void exploreEntry(Hero hero);

    public abstract void exploreOutsideArea(Hero hero);

    public abstract void exploreMiddleArea(Hero hero);

    public abstract void exploreInnerArea(Hero hero);

    public abstract void playSection(String[] section);

    public Entity randomMob() {
        return null;
    }

    public abstract void exit();
}
