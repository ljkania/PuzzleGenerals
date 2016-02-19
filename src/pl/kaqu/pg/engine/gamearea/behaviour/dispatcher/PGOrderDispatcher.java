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
import pl.kaqu.pg.engine.error.PGOutOfAreaException;
import pl.kaqu.pg.engine.gamearea.PGField;
import pl.kaqu.pg.engine.gamearea.PGPlayerArea;
import pl.kaqu.pg.engine.gamearea.PGUnitContainer;
import pl.kaqu.pg.engine.unit.PGUnit;

import java.util.*;

import static java.lang.Integer.max;

/**
 * Class responsible for setting units in correct order on board after changes
 */
public class PGOrderDispatcher {

    public static final Comparator<PGUnit> UNIT_COMPARATOR = (PGUnit u1, PGUnit u2) -> {
        if (u1.getPriority() != u2.getPriority()) {
            return u2.getPriority() - u1.getPriority();
        }
        if(u1.getPrimaryUnitContainer() instanceof PGField && u2.getPrimaryUnitContainer() instanceof PGField) {
            return ((PGField) u1.getPrimaryUnitContainer()).getCoordinate().y - ((PGField) u2.getPrimaryUnitContainer()).getCoordinate().y;
        }
        return 0;
    };

    public static void reorderUnits(PGPlayerArea playerArea) throws PGIncorrectUnitLocationException {
        int width = playerArea.width;
        int height = playerArea.height;

        Map<PGUnit, PGUnitContainer> previousUnitLocation = new HashMap<>();

        for(int i=0; i<width; i++) {
            for(int j=0; j<height; j++) {
                try {
                    PGUnit unit = playerArea.getField(i,j).getContainedUnit();
                    if(unit != null) {
                        previousUnitLocation.put(unit, unit.getPrimaryUnitContainer());
                    }
                } catch (PGOutOfAreaException e) {
                    e.printStackTrace();
                }
            }
        }

        List<PGUnit> sortedUnits = new ArrayList<>(previousUnitLocation.keySet());
        sortedUnits.sort(UNIT_COMPARATOR);
        sortedUnits.forEach(pl.kaqu.pg.engine.unit.PGUnit::clearCurrentUnitContainers);

        int[] firstPossible = new int[width];

        for(PGUnit unit : sortedUnits) {
            int widthOfUnit = unit.width;
            int heightOfUnit = unit.height;
            PGUnitContainer leftFrontOfUnit = previousUnitLocation.get(unit);

            if(leftFrontOfUnit instanceof PGField) {
                int x = ((PGField) leftFrontOfUnit).getCoordinate().x;

                int k = 0;
                for(int i=x; i<x+widthOfUnit; i++) {
                    k = max(k, firstPossible[i]);
                }

                try {
                    unit.setCurrentUnitContainers(playerArea.getField(x, k));
                } catch (PGOutOfAreaException e) {
                    e.printStackTrace();
                }

                for(int i=x; i<x+widthOfUnit; i++) {
                    firstPossible[i] = k+heightOfUnit;
                }
            }
        }
    }
}
