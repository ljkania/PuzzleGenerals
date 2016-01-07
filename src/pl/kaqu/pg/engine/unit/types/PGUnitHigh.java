package pl.kaqu.pg.engine.unit.types;

import pl.kaqu.pg.engine.gamearea.PGField;
import pl.kaqu.pg.engine.player.PGPlayer;
import pl.kaqu.pg.engine.unit.PGUnit;
import pl.kaqu.pg.engine.unit.PGUnitGroup;
import pl.kaqu.pg.engine.unit.activation.PGActivatedUnit;
import pl.kaqu.pg.engine.unit.activation.PGActivationType;
import pl.kaqu.pg.engine.unit.effect.PGUnitState;

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

public abstract class PGUnitHigh extends PGUnit implements PGActivatedUnit {

    protected PGUnitHigh(long unitID, PGPlayer owner, PGUnitGroup group, PGUnitState state) {
        super(unitID, owner, group, state);
    }

    @Override
    public PGActivationType checkActivation(PGField currentField) {
        if (currentField.getRearNeighbor() != null && currentField.getRearNeighbor().isContainingUnitAbleToActivate(this)) {
            if (currentField.getSecondRearNeighbor() != null && currentField.getSecondRearNeighbor().isContainingUnitAbleToActivate(this)) {
                return PGActivationType.SINGLE;
            }
        }
        return PGActivationType.NONE;
    }

}
