package com.botanique;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;

public enum EntityFactory {
    ;

    public void createTileMap(final Engine engine) {
        final Entity entity = engine.createEntity();



        engine.addEntity(entity);
    }
}
