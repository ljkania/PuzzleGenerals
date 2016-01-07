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

import pl.kaqu.pg.engine.unit.activation.PGActivatedUnit;
import pl.kaqu.pg.engine.unit.activation.PGActivationType;

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

    /*package*/ PGActivationType checkUnitActivation() {
        if (this.containedUnit instanceof PGActivatedUnit){
            return ((PGActivatedUnit) this.containedUnit).checkActivation(this);
        } else {
            return PGActivationType.NONE;
        }
    }

    public PGCoordinate getCoordinate() {
        return coordinate;
    }

    public PGField getLeftNeighbor() {
        return leftNeighbor;
    }

    /*package*/ void setLeftNeighbor(PGField leftNeighbor) {
        this.leftNeighbor = leftNeighbor;
    }

    public PGField getFrontNeighbor() {
        return frontNeighbor;
    }

    /*package*/ void setFrontNeighbor(PGField frontNeighbor) {
        this.frontNeighbor = frontNeighbor;
    }

    public PGField getRightNeighbor() {
        return rightNeighbor;
    }

    /*package*/ void setRightNeighbor(PGField rightNeighbor) {
        this.rightNeighbor = rightNeighbor;
    }

    public PGField getRearNeighbor() {
        return rearNeighbor;
    }

    /*package*/ void setRearNeighbor(PGField rearNeighbor) {
        this.rearNeighbor = rearNeighbor;
    }

    public PGField getSecondRearNeighbor() {
        if (this.getRearNeighbor() != null) {
            return this.getRearNeighbor().getRearNeighbor();
        }
        return null;
    }

}
