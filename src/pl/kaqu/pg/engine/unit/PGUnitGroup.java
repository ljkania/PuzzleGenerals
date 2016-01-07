package pl.kaqu.pg.engine.unit;

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

import java.util.BitSet;

public class PGUnitGroup {

    public static final PGUnitGroup ONE = new PGUnitGroup().setBits(1);
    public static final PGUnitGroup TWO = new PGUnitGroup().setBits(2);
    public static final PGUnitGroup THREE = new PGUnitGroup().setBits(3);
    public static final PGUnitGroup ONE_TWO = new PGUnitGroup().setBits(1,2);
    public static final PGUnitGroup ONE_THREE = new PGUnitGroup().setBits(1,3);
    public static final PGUnitGroup TWO_THREE = new PGUnitGroup().setBits(2,3);
    public static final PGUnitGroup ALL = new PGUnitGroup().setBits(1,2,3);
    public static final PGUnitGroup NONE = new PGUnitGroup().setBits();

    private static final int bitSetLength = 3;

    private final BitSet groupMembership;

    private PGUnitGroup() {
        this.groupMembership = new BitSet(bitSetLength);
    }

    private PGUnitGroup setBits(int... bits){
        for(int i : bits){
            this.groupMembership.set(i);
        }
        return this;
    }

    public boolean isMatchingGroup(PGUnitGroup obj) {
        if (this == obj)
            return true;
        for(int i = 0; i < bitSetLength; i++){
            if (this.groupMembership.get(i) == obj.groupMembership.get(i)){
                return true;
            }
        }

        return false;
    }

}
