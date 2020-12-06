package com.botanique.system;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.botanique.component.*;

import java.util.Optional;

public class DragSystem extends IteratingSystem {
    private final ComponentMapper<PositionComponent> positionCM = ComponentMapper.getFor(PositionComponent.class);
    private final ComponentMapper<RectangleAreaComponent> areaCM = ComponentMapper.getFor(RectangleAreaComponent.class);
    private final Entity cursorEntity;
    private final CursorComponent cursorComponent;

    private final Array<Entity> draggables = new Array<>();

    public DragSystem(final Entity cursor) {
        super(Family.all(DraggableComponent.class, RectangleAreaComponent.class, PositionComponent.class).get());
        cursorEntity = cursor;
        cursorComponent = cursor.getComponent(CursorComponent.class);
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);

        final PositionComponent cursorPos = positionCM.get(cursorEntity);
        if (cursorComponent.leftButtonJustPressed) {
            // start drag
            getDraggableUnderCursor().ifPresent(dragged -> dragEntity(cursorComponent, cursorPos, dragged));
        } else if (cursorComponent.leftButtonPressed) {
            // continue drag: update draggable position
            final Vector2 dragEntityPosition = cursorPos.getPosition().sub(cursorComponent.getDragOffset());
            cursorComponent.getDraggedEntity()
                    .map(positionCM::get)
                    .ifPresent(component -> component.setPosition(dragEntityPosition));
        } else if (cursorComponent.getDraggedEntity().isPresent()) {
            // finish drag
            cursorComponent.dropEntity();
        }

        draggables.clear();
    }

    private void dragEntity(final CursorComponent cursorComponent, final PositionComponent cursorPos, final Entity draggedEntity) {
        final Vector2 offset = cursorPos.getPosition().sub(positionCM.get(draggedEntity).getPosition());
        cursorComponent.dragEntity(draggedEntity, offset);
    }

    @Override
    protected void processEntity(final Entity entity, float deltaTime) {
        draggables.add(entity);
    }

    private Optional<Entity> getDraggableUnderCursor() {
        final PositionComponent cursorPos = positionCM.get(cursorEntity);
        for(final Entity entity : draggables) {
            final RectangleAreaComponent area = areaCM.get(entity);
            final PositionComponent position = positionCM.get(entity);

            if(area.isHovering(position.getPosition(), cursorPos.getPosition())) {
                return Optional.of(entity);
            }
        }
        return Optional.empty();
    }
}
