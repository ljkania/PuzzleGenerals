package pl.kaqu.pg.engine.unit.types;

import com.sun.istack.internal.NotNull;
import pl.kaqu.pg.engine.gamearea.PGField;
import pl.kaqu.pg.engine.gamearea.PGUnitContainer;
import pl.kaqu.pg.engine.player.PGPlayer;
import pl.kaqu.pg.engine.unit.PGUnit;
import pl.kaqu.pg.engine.unit.PGUnitGroup;
import pl.kaqu.pg.engine.unit.activation.PGActivatedUnit;
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

public abstract class PGUnitWall extends PGUnit implements PGActivatedUnit {
    PGUnitContainer currentUnitContainer;
    private final int width = 1;
    private final int height = 1;

    protected PGUnitWall(long unitID, PGPlayer owner, PGUnitGroup group, PGUnitState state, @NotNull PGUnitContainer currentUnitContainer) {
        super(unitID, owner, group, state);
        this.setCurrentUnitContainers(currentUnitContainer);
    }

    public int getFrontRowIndex() {
        if(currentUnitContainer instanceof PGField) {
            return ((PGField) currentUnitContainer).getCoordinate().getY();
        }
        return Integer.MAX_VALUE;
    }

    public void setCurrentUnitContainers(@NotNull PGUnitContainer currentUnitContainer) {
        this.currentUnitContainer = currentUnitContainer;
        if(this.currentUnitContainer instanceof PGField) {
            List<PGField> toObserve = new ArrayList<>();
            toObserve.add(((PGField) this.currentUnitContainer).getRearNeighbor());
            toObserve.add(((PGField) this.currentUnitContainer).getSecondRearNeighbor());

            if(!toObserve.contains(null)) {
                for(PGField field : toObserve) {
                    field.addObserver(this);
                }
            }
        }
    }

    @NotNull public PGUnitContainer getCurrentFrontLeftContainer() {
        return currentUnitContainer;
    }

    @NotNull public List<PGUnitContainer> getCurrentUnitContainers() {
        List<PGUnitContainer> result = new ArrayList<>();
        result.add(this.currentUnitContainer);
        return result;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }


    @Override
    public void update(Observable obj, Object arg) {
        //TODO: Register yourself for checking of activation
    }
}
