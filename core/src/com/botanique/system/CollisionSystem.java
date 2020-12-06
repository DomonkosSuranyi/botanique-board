package com.botanique.system;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.botanique.component.*;

import java.util.Optional;

public class CollisionSystem extends IteratingSystem {
    private final Array<Entity> areaArray = new Array<>();
    private Optional<Entity> cursor;

    final ComponentMapper<RectangleAreaComponent> areaCM = ComponentMapper.getFor(RectangleAreaComponent.class);
    final ComponentMapper<CursorComponent> cursorCM = ComponentMapper.getFor(CursorComponent.class);
    final ComponentMapper<PositionComponent> positionCM = ComponentMapper.getFor(PositionComponent.class);

    public CollisionSystem() {
        super(Family.one(RectangleAreaComponent.class, CursorComponent.class).get());
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);

        if(!cursor.isPresent()) {
            return;
        }

        for(final Entity areaEntity : areaArray) {
            final RectangleAreaComponent area = areaCM.get(areaEntity);
            final PositionComponent pos = positionCM.get(areaEntity);
        }

        areaArray.clear();
    }

    @Override
    protected void processEntity(final Entity entity, final float deltaTime) {
        if(cursorCM.has(entity))
            cursor = Optional.of(entity);
        else
            areaArray.add(entity);
    }
}
