package com.botanique.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class VisualComponent implements Component {
    public TextureRegion region;
    public float width;
    public float height;
    public Vector2 textureOffset;

    public VisualComponent (final TextureRegion region, final float width, final float height) {
        this.region = region;
        this.width = width;
        this.height = height;
    }
}
