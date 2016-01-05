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

import pl.kaqu.pg.engine.unit.PGUnit;
import pl.kaqu.pg.engine.unit.PGUnitGroup;

public class PGUnitContainer {

    protected PGUnit containedUnit;

    public PGUnitContainer(PGUnit containedUnit) {
        this.containedUnit = containedUnit;
    }

    public PGUnit getContainedUnit() {
        return containedUnit;
    }

    public void setContainedUnit(PGUnit containedUnit) {
        this.containedUnit = containedUnit;
    }

    public PGUnit pickContainedUnit() {
        PGUnit tmp = this.containedUnit;
        this.containedUnit = null;
        return tmp;
    }

    public PGUnitGroup getContainedUnitGroup() {
        if (this.containedUnit != null){
            return this.containedUnit.getGroup();
        }
        return PGUnitGroup.NONE;
    }
}
