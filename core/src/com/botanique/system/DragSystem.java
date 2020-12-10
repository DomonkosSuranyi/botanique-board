package com.botanique.system;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.botanique.component.*;
import com.botanique.TileMap;
import sun.awt.X11.XSystemTrayPeer;

import java.util.Optional;

public class DragSystem extends IteratingSystem {
    private final ComponentMapper<PositionComponent> positionCM = ComponentMapper.getFor(PositionComponent.class);
    private final ComponentMapper<RectangleAreaComponent> areaCM = ComponentMapper.getFor(RectangleAreaComponent.class);
    private final Cursor cursor;

    private final TileMap tilemap;

    private final Array<Entity> draggables = new Array<>();

    public DragSystem(final TileMap tilemap, final Cursor cursor) {
        super(Family.all(DraggableComponent.class, RectangleAreaComponent.class, PositionComponent.class).get());
        this.cursor = cursor;
        this.tilemap = tilemap;
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);

        if (cursor.leftButtonJustPressed) {
            cursor.leftButtonJustPressed = false;
            // start drag
            getDraggableUnderCursor().ifPresent(this::dragEntity);
            System.out.println(cursor.getDraggedEntity());
        } else if (cursor.leftButtonPressed) {
            // continue drag: update draggable position
            final Vector2 dragEntityPosition = tilemap.roundToTilePosition(cursor.getPosition());
            cursor.getDraggedEntity()
                    .map(positionCM::get)
                    .ifPresent(component -> component.setPosition(dragEntityPosition));
        } else if (cursor.getDraggedEntity().isPresent()) {
            // finish drag
            cursor.dropEntity();
        }

        draggables.clear();
    }

    private void dragEntity(final Entity draggedEntity) {
        System.out.println("cursorPos: " + cursor.getPosition());
        final Vector2 offset = cursor.getPosition().sub(positionCM.get(draggedEntity).getPosition());
        System.out.println("offset: " + offset);
        cursor.dragEntity(draggedEntity, offset);
    }

    @Override
    protected void processEntity(final Entity entity, float deltaTime) {
        draggables.add(entity);
    }

    private Optional<Entity> getDraggableUnderCursor() {
        for(final Entity entity : draggables) {
            final RectangleAreaComponent area = areaCM.get(entity);
            final PositionComponent position = positionCM.get(entity);

            if(area.isHovering(position.getPosition(), cursor.getPosition())) {
                return Optional.of(entity);
            }
        }
        return Optional.empty();
    }
}
