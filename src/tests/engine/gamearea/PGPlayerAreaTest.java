package tests.engine.gamearea;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import pl.kaqu.pg.engine.error.PGOutOfAreaException;
import pl.kaqu.pg.engine.gamearea.PGField;
import pl.kaqu.pg.engine.gamearea.PGPlayerArea;
import pl.kaqu.pg.engine.player.PGPlayer;

import static org.junit.Assert.*;


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

public class PGPlayerAreaTest {
    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void constructor_FirstArgumentIncorrect_IllegalArgumentExceptionThrown() {
        exception.expect(IllegalArgumentException.class);
        new PGPlayerArea(0, 6, new PGPlayer());
    }

    @Test
    public void constructor_SecondArgumentIncorrect_IllegalArgumentExceptionThrown() {
        exception.expect(IllegalArgumentException.class);
        new PGPlayerArea(8, -1, new PGPlayer());
    }

    @Test
    public void getField_AnyCorrectArguments_ArgumentsEqualsReturnedPGFieldCoordinate() throws PGOutOfAreaException {
        PGPlayerArea playerArea = new PGPlayerArea(8, 6, new PGPlayer());
        int x = 2;
        int y = 5;
        PGField field = playerArea.getField(x, y);
        assertEquals(field.getCoordinate().x, x);
        assertEquals(field.getCoordinate().y, y);
    }

    @Test
    public void getField_GetTwoFieldsNextToEachOther_FieldsShouldBeHorizontalNeighbours() throws PGOutOfAreaException {
        PGPlayerArea playerArea = new PGPlayerArea(8, 6, new PGPlayer());
        PGField field1 = playerArea.getField(3, 4);
        PGField field2 = playerArea.getField(4, 4);
        assertEquals(field1.getRightNeighbor(), field2);
        assertEquals(field2.getLeftNeighbor(), field1);
    }

    @Test
    public void getField_GetOneFieldInFrontOfAnother_FieldsShouldBeVerticalNeighbours() throws PGOutOfAreaException {
        PGPlayerArea playerArea = new PGPlayerArea(8, 6, new PGPlayer());
        PGField field1 = playerArea.getField(1, 4);
        PGField field2 = playerArea.getField(1, 5);
        assertEquals(field1.getRearNeighbor(), field2);
        assertEquals(field2.getFrontNeighbor(), field1);
    }

    @Test
    public void getField_FirstArgumentNegative_PGOutOfAreaExceptionThrown() throws PGOutOfAreaException {
        PGPlayerArea playerArea = new PGPlayerArea(8, 6, new PGPlayer());
        exception.expect(PGOutOfAreaException.class);
        playerArea.getField(-1, 4);
    }

    @Test
    public void getField_FirstArgumentTooBig_PGOutOfAreaExceptionThrown() throws PGOutOfAreaException {
        PGPlayerArea playerArea = new PGPlayerArea(8, 6, new PGPlayer());
        exception.expect(PGOutOfAreaException.class);
        playerArea.getField(8, 4);
    }

    @Test
    public void getField_SecondArgumentNegative_PGOutOfAreaExceptionThrown() throws PGOutOfAreaException {
        PGPlayerArea playerArea = new PGPlayerArea(8, 6, new PGPlayer());
        exception.expect(PGOutOfAreaException.class);
        playerArea.getField(0, -2);
    }

    @Test
    public void getField_SecondArgumentTooBig_PGOutOfAreaExceptionThrown() throws PGOutOfAreaException {
        PGPlayerArea playerArea = new PGPlayerArea(8, 6, new PGPlayer());
        exception.expect(PGOutOfAreaException.class);
        playerArea.getField(4, 7);
    }
}