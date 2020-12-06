package com.botanique.entity;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.botanique.component.*;

public class Tile {
    public static final int TILE_WIDTH = 100;
    public static final int TILE_HEIGHT = 100;

    private Tile() {

    }

    public static Entity create(final Entity entity, final float positionX, final float positionY) {
        entity.add(new PositionComponent(positionX, positionY));
        entity.add(new VisualComponent(new TextureRegion(new Texture("core/assets/tile.png")), TILE_WIDTH, TILE_HEIGHT));
        entity.add(new RectangleAreaComponent(TILE_WIDTH, TILE_HEIGHT));
        entity.add(new TileComponent());
        entity.add(new DraggableComponent());
        return entity;
    }
}
