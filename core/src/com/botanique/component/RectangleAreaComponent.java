package com.botanique.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;

public class RectangleAreaComponent implements Component {
    private final float width;
    private final float height;

    public RectangleAreaComponent(final float width, final float height) {
        this.width = width;
        this.height = height;
    }

//    @Override
    public boolean isHovering(final Vector2 referencePosition, final Vector2 hoveringObjectPosition) {
        final Vector2 offset = hoveringObjectPosition.cpy();
        offset.sub(referencePosition);

        return offset.x > 0 && offset.y > 0 && offset.x < width && offset.y < height;
    }
}
