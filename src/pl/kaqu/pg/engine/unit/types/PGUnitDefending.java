package pl.kaqu.pg.engine.unit.types;

import com.sun.istack.internal.NotNull;
import pl.kaqu.pg.engine.gamearea.PGField;
import pl.kaqu.pg.engine.gamearea.PGUnitContainer;
import pl.kaqu.pg.engine.player.PGPlayer;
import pl.kaqu.pg.engine.unit.PGUnit;
import pl.kaqu.pg.engine.unit.PGUnitGroup;
import pl.kaqu.pg.engine.unit.effect.PGUnitState;

import java.util.*;

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

public abstract class PGUnitDefending extends PGUnit {
    protected PGUnitDefending(long unitID, PGPlayer owner, PGUnitGroup group, PGUnitState state, @NotNull PGUnitContainer currentUnitContainer) {
        super(unitID, owner, group, state, 1, 1);
        this.setCurrentUnitContainers(currentUnitContainer);
    }

    public void setCurrentUnitContainers(@NotNull PGUnitContainer currentUnitContainer) {
        this.currentUnitContainers.clear();
        this.currentUnitContainers.put(PGUnit.PRIMARY_CONTAINER, currentUnitContainer);

        if(currentUnitContainer instanceof PGField) {
            List<PGField> toObserve = new ArrayList<>();
            toObserve.add(((PGField) currentUnitContainer).getRearNeighbor());
            toObserve.add(((PGField) currentUnitContainer).getSecondRearNeighbor());

            if(!toObserve.contains(null)) {
                for(PGField field : toObserve) {
                    field.addObserver(this);
                }
            }
        }
    }

    @Override
    public void update(Observable obj, Object arg) {
        //TODO: Register yourself for checking of activation
    }
}
