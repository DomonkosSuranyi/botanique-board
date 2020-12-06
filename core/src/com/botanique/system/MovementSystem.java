package com.botanique.system;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.botanique.component.MovementComponent;
import com.botanique.component.PositionComponent;

public class MovementSystem extends IteratingSystem {
    private ComponentMapper<PositionComponent> pm = ComponentMapper.getFor(PositionComponent.class);
    private ComponentMapper<MovementComponent> mm = ComponentMapper.getFor(MovementComponent.class);

    public MovementSystem () {
        super(Family.all(PositionComponent.class, MovementComponent.class).get());
    }

    @Override
    public void processEntity (Entity entity, float deltaTime) {
        PositionComponent position = pm.get(entity);
        MovementComponent movement = mm.get(entity);

        position.getPosition().x += movement.velocityX * deltaTime;
        position.getPosition().y += movement.velocityY * deltaTime;
    }
}
