package pl.kaqu.pg.engine.gamearea;

/*
    PuzzleGenerals
    Copyright (C) 2016 kaqu kaqukal@gmail.com

    This program is free software; you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation; either version 2 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License along
    with this program; if not, write to the Free Software Foundation, Inc.,
    51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 */

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class PGField extends PGUnitContainer {

    /*package*/ final PGCoordinate coordinate;

    private PGField leftNeighbor;
    private PGField frontNeighbor;
    private PGField rightNeighbor;
    private PGField rearNeighbor;


    /*package*/ PGField(int x, int y) {
        super(null);
        this.coordinate = new PGCoordinate(x, y);
    }

    @NotNull public PGCoordinate getCoordinate() {
        return coordinate;
    }

    @Nullable public PGField getLeftNeighbor() {
        return leftNeighbor;
    }

    /*package*/ void setLeftNeighbor(@Nullable PGField leftNeighbor) {
        this.leftNeighbor = leftNeighbor;
    }

    @Nullable public PGField getFrontNeighbor() {
        return frontNeighbor;
    }

    /*package*/ void setFrontNeighbor(@Nullable PGField frontNeighbor) {
        this.frontNeighbor = frontNeighbor;
    }

    @Nullable public PGField getRightNeighbor() {
        return rightNeighbor;
    }

    /*package*/ void setRightNeighbor(@Nullable PGField rightNeighbor) {
        this.rightNeighbor = rightNeighbor;
    }

    @Nullable public PGField getRearNeighbor() {
        return rearNeighbor;
    }

    /*package*/ void setRearNeighbor(@Nullable PGField rearNeighbor) {
        this.rearNeighbor = rearNeighbor;
    }

    @Nullable public PGField getSecondRearNeighbor() {
        if (this.getRearNeighbor() != null) {
            return this.getRearNeighbor().getRearNeighbor();
        }
        return null;
    }

}
