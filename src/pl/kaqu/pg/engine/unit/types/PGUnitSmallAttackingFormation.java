package pl.kaqu.pg.engine.unit.types;

import org.jetbrains.annotations.NotNull;
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

public abstract class PGUnitSmallAttackingFormation extends PGUnit {
    public static final int FRONT = PGUnit.PRIMARY_CONTAINER;
    public static final int CENTER = 1;
    public static final int BACK = 2;

    protected PGUnitSmallAttackingFormation(long unitID, PGPlayer owner, PGUnitGroup group, PGUnitState state, @NotNull PGUnitContainer primaryContainer) throws PGError {
        super(unitID, owner, group, state, 1, 3);
        this.currentUnitContainers = new HashMap<>();
        this.setCurrentUnitContainers(primaryContainer);
    }

    public void setCurrentUnitContainers(@NotNull PGUnitContainer primaryContainer) throws PGIncorrectUnitLocationException {
        clearCurrentUnitContainers();

        if(primaryContainer instanceof PGField) {
            PGField centerOfUnit = ((PGField) primaryContainer).getRearNeighbor();
            PGField backOfUnit = ((PGField) primaryContainer).getSecondRearNeighbor();

            if(centerOfUnit == null || backOfUnit == null) {
                throw new PGIncorrectUnitLocationException();
            }

            this.currentUnitContainers.put(FRONT, primaryContainer);
            this.currentUnitContainers.put(CENTER, centerOfUnit);
            this.currentUnitContainers.put(BACK, backOfUnit);

            observedObjects.add(backOfUnit.getRearNeighbor());
            observedObjects.add(backOfUnit.getSecondRearNeighbor());

            if(!observedObjects.contains(null)) {
                for(Observable observable : observedObjects) {
                    observable.addObserver(this);
                }
            } else {
                observedObjects.clear();
            }
        } else {
            this.currentUnitContainers.put(PGUnit.PRIMARY_CONTAINER, primaryContainer);
        }

        for(PGUnitContainer unitContainer : this.currentUnitContainers.values()) {
            unitContainer.setContainedUnit(this);
        }
    }

    @Override
    public void update(Observable obj, Object arg) {
        //TODO: Register yourself for checking of activation
    }
}
