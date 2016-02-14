package pl.kaqu.pg.engine.gamearea;

import java.util.Collection;
import java.util.Map;

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

import com.sun.istack.internal.NotNull;

import pl.kaqu.pg.engine.error.PGError;
import pl.kaqu.pg.engine.error.PGWrongSumOfUnitsException;
import pl.kaqu.pg.engine.error.PGOutOfAreaException;
import pl.kaqu.pg.engine.player.PGPlayer;
import pl.kaqu.pg.engine.unit.PGUnit;

public class PGPlayerArea {
	
	public static final int TOTAL_NUMBER_OF_UNITS = 37;
	
    private final int width;
    private final int height;
    private final PGField[][] fields;
    private PGPlayer connectedPlayer;
    
    private UnitsReserve reserve;
    
    public PGPlayerArea(int width, int height, @NotNull PGPlayer connectedPlayer) {
        this.width = width;
        this.height = height;
        this.connectedPlayer = connectedPlayer;
        this.fields = new PGField[this.width][];
        initializeFields();
    }

    private void initializeFields() {
        for (int i = 0; i < this.width; i++) {
            this.fields[i] = new PGField[this.height];
            for (int j = 0; j < this.height; j++) {
                this.fields[i][j] = new PGField(i, j);
            }
        }
        for (int i = 0; i < this.width; i++) {
            for (int j = 0; j < this.height; j++) {
                if (j > 1) {
                    this.fields[i][j].setFrontNeighbor(this.fields[i][j - 1]);
                }
                if (i < this.width - 1) {
                    this.fields[i][j].setRightNeighbor(this.fields[i + 1][j]);
                }
                if (j < this.height - 1) {
                    this.fields[i][j].setRearNeighbor(this.fields[i][j + 1]);
                }
                if (i > 1) {
                    this.fields[i][j].setLeftNeighbor(this.fields[i - 1][j]);
                }
            }
        }

    }

    @NotNull public PGField getField(int x, int y) throws PGOutOfAreaException {
        if (x < 0 && x >= this.width && y < 0 && y >= this.height) {
            throw new PGOutOfAreaException();
        }
        return this.fields[x][y];
    }
    
    public void initializeUnitsDistribution(Map<PGCoordinate, PGUnit> unitsDistribution, Collection<PGUnit> reserve) throws PGError {
    	if(unitsDistribution.size() + reserve.size() != TOTAL_NUMBER_OF_UNITS) {
    		throw new PGWrongSumOfUnitsException();
    	}
    	
    	if (unitsDistribution.keySet().stream()
    			.anyMatch(coord -> coord.x < 0 && coord.y >= this.width && coord.y < 0 && coord.y >= this.height)) {
    		throw new PGOutOfAreaException();
    	}
    	
    	unitsDistribution.forEach((coords, unit) -> {
    		fields[coords.x][coords.y].setContainedUnit(unit);
    	});
    	
    	this.reserve = new UnitsReserve(reserve);
    }
    
    public void moveUnitsToField() throws PGError {
    	Map<PGCoordinate, PGUnit> unitsDistribution = reserve.provideUnits();
    	
    	if(unitsDistribution.size() + reserve.sizeOfReserve() != TOTAL_NUMBER_OF_UNITS) {
    		throw new PGWrongSumOfUnitsException();
    	}
    	
    	if (unitsDistribution.keySet().stream()
    			.anyMatch(coord -> coord.x < 0 && coord.y >= this.width && coord.y < 0 && coord.y >= this.height)) {
    		throw new PGOutOfAreaException();
    	}
    	
    	unitsDistribution.forEach((coords, unit) -> {
    		fields[coords.x][coords.y].setContainedUnit(unit);
    	});
    }
    
    public void moveUnitToReserve(int x, int y) throws PGOutOfAreaException {
        if (x < 0 && x >= this.width && y < 0 && y >= this.height) {
            throw new PGOutOfAreaException();
        }

        this.reserve.addUnitToReserve(fields[x][y].getContainedUnit());
        fields[x][y].setContainedUnit(null);
    }

}
