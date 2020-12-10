package com.botanique;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;
import com.botanique.entity.Tile;

public class TileMap {
    private final Entity[][] tiles;

    public TileMap(final int tilePerRow, final int tilePerColumn) {
        tiles = new Entity[tilePerRow][tilePerColumn];
    }

    public boolean pushTile(final int column, final int row, final Entity tile) {
        if(tiles[column][row] == null) {
            tiles[column][row] = tile;
            return true;
        }
        return false;
    }

    public Utils.DiscreteCoord getCoordAtPosition(final Vector2 position) {
        final int x = (int) (position.x / Tile.TILE_WIDTH);
        final int y = (int) (position.y / Tile.TILE_HEIGHT);
        return new Utils.DiscreteCoord(x, y);
    }

    public Vector2 roundToTilePosition(final Vector2 position) {
        final Utils.DiscreteCoord coord = getCoordAtPosition(position);
        return new Vector2(coord.x * Tile.TILE_WIDTH, coord.y * Tile.TILE_HEIGHT);
    }
}
