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

import java.util.*;
import java.util.stream.Collectors;

import static java.lang.Integer.max;

/**
 * Class responsible for setting units in correct order on board after changes
 */
public class PGOrderDispatcher {
    public void reorderUnits(PGField[][] grid) throws PGIncorrectUnitLocationException {
        int width = grid.length;
        int height = grid[0].length;

        Comparator<PGUnit> unitComparator = (PGUnit u1, PGUnit u2) -> {
            if(u1.getPriority() != u2.getPriority()) {
                return u1.getPriority() - u2.getPriority();
            }
            return u2.getFrontRowIndex() - u1.getFrontRowIndex();
        };

        PriorityQueue<PGUnit> queue = new PriorityQueue<>(height * width, unitComparator);
        Set<PGUnit> uniqueUnits = new HashSet<>();

        for(PGField[] column : grid) {
            for(PGField field : column) {
                uniqueUnits.add(field.getContainedUnit());
                field.setContainedUnit(null);
            }
        }
        queue.addAll(uniqueUnits.stream().collect(Collectors.toList()));
        int[] firstPossible = new int[width];

        while(!queue.isEmpty()) {
            PGUnit unit = queue.remove();
            int widthOfUnit = unit.getWidth();
            int heightOfUnit = unit.getHeight();
            PGUnitContainer leftFrontOfUnit = unit.getCurrentFrontLeftContainer();

            if(leftFrontOfUnit instanceof PGField) {
                int x = ((PGField) leftFrontOfUnit).getCoordinate().getX();
                int y = ((PGField) leftFrontOfUnit).getCoordinate().getY();

                int k = 0;
                for(int i=x; i<x+widthOfUnit; i++) {
                    k = max(k, firstPossible[i]);
                }

                for(; k + heightOfUnit <= height; k++) {
                    boolean occupied = false;
                    for(int i = x; i < x + widthOfUnit; i++) {
                        for(int j = y; j < y + heightOfUnit; j++) {
                            if(grid[i][j].getContainedUnit() != null) {
                                occupied = true;
                                break;
                            }
                        }

                        if(occupied) {
                            break;
                        }
                    }

                    if(!occupied) {
                        unit.setCurrentUnitContainers(grid[x][k]);
                        List<PGUnitContainer> containersToChange = unit.getCurrentUnitContainers();

                        for(PGUnitContainer container : containersToChange) {
                            container.setContainedUnit(unit);
                        }

                        for(int i=x; i<x+widthOfUnit; i++) {
                            firstPossible[i] = k+heightOfUnit;
                        }

                        break;
                    }
                }
            }
        }
    }
}
