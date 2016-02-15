package pl.kaqu.pg.engine.gamearea.behaviour.dispatcher;

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

import pl.kaqu.pg.engine.error.PGIncorrectUnitLocationException;
import pl.kaqu.pg.engine.gamearea.PGField;
import pl.kaqu.pg.engine.gamearea.PGUnitContainer;
import pl.kaqu.pg.engine.unit.PGUnit;

import java.util.ArrayList;
import java.util.List;

/**
 * Class responsible for dispatching player moves
 */
public class PGMoveDispatcher {

    public void pickUnitToHand(PGUnitContainer unitContainer, PGUnitContainer hand) throws PGIncorrectUnitLocationException {
        PGUnit unit = unitContainer.getContainedUnit();
        hand.setContainedUnit(unit);
        for(PGUnitContainer container : unit.getCurrentUnitContainers()) {
            container.setContainedUnit(null);
        }
        unit.setCurrentUnitContainers(hand);
    }

    public void dropUnitFromHand(PGUnitContainer hand, PGField primaryContainer) throws PGIncorrectUnitLocationException {
        PGUnit unit = hand.getContainedUnit();
        for(PGUnitContainer container : unit.getCurrentUnitContainers()) {
            container.setContainedUnit(null);
        }

        int x = primaryContainer.getCoordinate().x;
        int y = primaryContainer.getCoordinate().y;
        int width = unit.width;
        int height = unit.height;
        List<PGField> unitLocations = new ArrayList<>();

        PGField currentMostLeft = primaryContainer;

        for(int i=0; i<height; i++) {
            PGField currentField = currentMostLeft;
            for(int j=0; j<width; j++) {
                if(currentField == null || currentField.getContainedUnit() != null) {
                    throw new PGIncorrectUnitLocationException();
                }

                unitLocations.add(currentField);
                currentField = currentField.getRightNeighbor();
            }
            currentMostLeft = currentMostLeft.getRearNeighbor();
        }

        for(PGField field : unitLocations) {
            field.setContainedUnit(unit);
        }
        unit.setCurrentUnitContainers(primaryContainer);
    }
}
