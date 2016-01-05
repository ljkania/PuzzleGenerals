package pl.kaqu.pg.engine.gamearea;

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

import pl.kaqu.pg.engine.unit.effect.PGUnitEffect;
import pl.kaqu.pg.engine.unit.effect.PGUnitState;

import java.util.List;

public class PGPlayerArea {

    private final int width;
    private final int height;
    private final PGField[][] fields;

    public PGPlayerArea(int width, int height) {
        this.width = width;
        this.height = height;
        this.fields = new PGField[this.width][];
        for(int i = 0; i < this.width; i++) {
            this.fields[i] = new PGField[this.height];
        }
    }

    public PGField getField(int x, int y) {
        return this.fields[x][y];
    }

    public void applyEffectForColumn(int column, PGUnitEffect effect){

    }

    /*package*/ void refreshArea(){
        //TODO - check area, fall and activate if needed
    }

    /*package*/ void normalizeUnitsPositions(){
//        for(int j = 0; j < this.height; j++){
//            for(int i = 0; i < this.width; i++){
//                if(this.fields[i][j].content != null && this.fields[i][j].content.getState() != PGUnitState.DISABLED) {
//
//                }
//            }
//        }

    }
}
