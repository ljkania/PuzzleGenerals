package pl.kaqu.pg.engine.unit.types;

import com.sun.istack.internal.NotNull;
import pl.kaqu.pg.engine.error.PGError;
import pl.kaqu.pg.engine.error.PGIncorrectUnitLocationException;
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

public abstract class PGUnitAttackingFormation extends PGUnit implements PGActivatedUnit {
    Map<Integer, PGUnitContainer> currentUnitContainers;
    public static final int FRONT = 0;
    public static final int CENTER = 1;
    public static final int BACK = 2;
    private final int width = 1;
    private final int height = 3;

    protected PGUnitAttackingFormation(long unitID, PGPlayer owner, PGUnitGroup group, PGUnitState state, @NotNull PGUnitContainer frontOfUnit) throws PGError {
        super(unitID, owner, group, state);
        this.currentUnitContainers = new HashMap<>();
        this.setCurrentUnitContainers(frontOfUnit);
    }

    public int getFrontRowIndex() {
        if(currentUnitContainers.get(FRONT) instanceof PGField) {
            return ((PGField) currentUnitContainers.get(FRONT)).getCoordinate().getY();
        }
        return Integer.MAX_VALUE;
    }

    public void setCurrentUnitContainers(@NotNull PGUnitContainer frontOfUnit) throws PGIncorrectUnitLocationException {
        this.currentUnitContainers.clear();

        if(frontOfUnit instanceof PGField) {
            PGField centerOfUnit = ((PGField) frontOfUnit).getRearNeighbor();
            PGField backOfUnit = ((PGField) frontOfUnit).getSecondRearNeighbor();

            if(centerOfUnit == null || backOfUnit == null) {
                throw new PGIncorrectUnitLocationException();
            }

            this.currentUnitContainers.put(FRONT, frontOfUnit);
            this.currentUnitContainers.put(CENTER, centerOfUnit);
            this.currentUnitContainers.put(BACK, backOfUnit);

            List<PGField> toObserve = new ArrayList<>();
            toObserve.add(backOfUnit.getRearNeighbor());
            toObserve.add(backOfUnit.getSecondRearNeighbor());

            if(!toObserve.contains(null)) {
                for(PGField field : toObserve) {
                    field.addObserver(this);
                }
            }
        }
    }

    @NotNull public PGUnitContainer getCurrentFrontLeftContainer() {
        return currentUnitContainers.get(FRONT);
    }

    @NotNull public List<PGUnitContainer> getCurrentUnitContainers() {
        return new ArrayList<>(this.currentUnitContainers.values());
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
