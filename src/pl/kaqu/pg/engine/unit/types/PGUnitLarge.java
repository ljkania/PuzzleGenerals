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

public abstract class PGUnitLarge extends PGUnit implements PGActivatedUnit {
    Map<Integer, PGUnitContainer> currentUnitContainers;
    public static final int LEFT_FRONT = 0;
    public static final int LEFT_BACK = 1;
    public static final int RIGHT_FRONT = 2;
    public static final int RIGHT_BACK = 3;

    protected PGUnitLarge(long unitID, PGPlayer owner, PGUnitGroup group, PGUnitState state, @NotNull PGUnitContainer leftFrontOfUnit) throws PGError {
        super(unitID, owner, group, state);
        this.currentUnitContainers = new HashMap<>();

        if(leftFrontOfUnit instanceof PGField) {
            PGField leftBackOfUnit;
            PGField rightFrontOfUnit;
            PGField rightBackOfUnit;

            try {
                leftBackOfUnit = ((PGField) leftFrontOfUnit).getRearNeighbor();
                rightFrontOfUnit = ((PGField) leftFrontOfUnit).getRightNeighbor();
                rightBackOfUnit = rightFrontOfUnit.getRearNeighbor();
            } catch(NullPointerException e) {
                throw new PGIncorrectUnitLocationException();
            }

            currentUnitContainers.put(LEFT_FRONT, leftFrontOfUnit);
            currentUnitContainers.put(LEFT_BACK, leftBackOfUnit);
            currentUnitContainers.put(RIGHT_FRONT, rightFrontOfUnit);
            currentUnitContainers.put(RIGHT_BACK, rightBackOfUnit);

            if(currentUnitContainers.containsValue(null)) {
                throw new PGIncorrectUnitLocationException();
            }

            List<PGField> toObserve = new ArrayList<>();
            toObserve.add(leftBackOfUnit.getRearNeighbor());
            toObserve.add(leftBackOfUnit.getSecondRearNeighbor());
            toObserve.add(rightBackOfUnit.getRearNeighbor());
            toObserve.add(rightBackOfUnit.getSecondRearNeighbor());


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
