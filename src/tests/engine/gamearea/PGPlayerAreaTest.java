package tests.engine.gamearea;

import org.jetbrains.annotations.NotNull;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import pl.kaqu.pg.engine.error.PGIncorrectUnitLocationException;
import pl.kaqu.pg.engine.error.PGOutOfAreaException;
import pl.kaqu.pg.engine.gamearea.PGField;
import pl.kaqu.pg.engine.gamearea.PGPlayerArea;
import pl.kaqu.pg.engine.gamearea.PGUnitContainer;
import pl.kaqu.pg.engine.player.PGPlayer;
import pl.kaqu.pg.engine.unit.PGUnit;
import pl.kaqu.pg.engine.unit.PGUnitGroup;
import pl.kaqu.pg.engine.unit.PGUnitRank;
import pl.kaqu.pg.engine.unit.action.PGUnitAction;
import pl.kaqu.pg.engine.unit.activation.PGActivationType;
import pl.kaqu.pg.engine.unit.activation.PGUnitActivationCheckerCallable;
import pl.kaqu.pg.engine.unit.effect.PGUnitEffect;
import pl.kaqu.pg.engine.unit.effect.PGUnitState;
import pl.kaqu.pg.engine.unit.types.PGUnitLarge;
import pl.kaqu.pg.engine.unit.types.PGUnitSmall;

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
    public void getField_GetFieldNextToLeftEdge_LeftNeighbourShouldBeNull() throws PGOutOfAreaException {
        PGPlayerArea playerArea = new PGPlayerArea(8, 6, new PGPlayer());
        PGField field = playerArea.getField(0, 4);
        assertNull(field.getLeftNeighbor());
    }

    @Test
    public void getField_GetFieldNextToRightEdge_RightNeighbourShouldBeNull() throws PGOutOfAreaException {
        PGPlayerArea playerArea = new PGPlayerArea(8, 6, new PGPlayer());
        PGField field = playerArea.getField(7, 4);
        assertNull(field.getRightNeighbor());
    }

    @Test
     public void getField_GetFieldAtFirstRow_FrontNeighbourShouldBeNull() throws PGOutOfAreaException {
        PGPlayerArea playerArea = new PGPlayerArea(8, 6, new PGPlayer());
        PGField field = playerArea.getField(3, 0);
        assertNull(field.getFrontNeighbor());
    }

    @Test
     public void getField_GetFieldAtLastRow_RearNeighbourShouldBeNull() throws PGOutOfAreaException {
        PGPlayerArea playerArea = new PGPlayerArea(8, 6, new PGPlayer());
        PGField field = playerArea.getField(4, 5);
        assertNull(field.getRearNeighbor());
    }

    @Test
    public void getField_GetFieldAtLastRow_SecondRearNeighbourShouldBeNull() throws PGOutOfAreaException {
        PGPlayerArea playerArea = new PGPlayerArea(8, 6, new PGPlayer());
        PGField field = playerArea.getField(3, 5);
        assertNull(field.getSecondRearNeighbor());
    }

    @Test
    public void getField_GetFieldAtOneBeforeLastRow_SecondRearNeighbourShouldBeNull() throws PGOutOfAreaException {
        PGPlayerArea playerArea = new PGPlayerArea(8, 6, new PGPlayer());
        PGField field = playerArea.getField(2, 4);
        assertNull(field.getSecondRearNeighbor());
    }

    @Test
    public void getField_GetAnyFieldAndFieldTwoSpaceBehindIt_SecondOneEqualsFirstSecondNeighbour() throws PGOutOfAreaException {
        PGPlayerArea playerArea = new PGPlayerArea(8, 6, new PGPlayer());
        PGField field1 = playerArea.getField(5, 1);
        PGField field2 = playerArea.getField(5, 3);
        assertEquals(field1.getSecondRearNeighbor(), field2);
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

    @Test
    public void moveUnitToReserve_OnePGUnitSmallOnTheGridMovedToReserve_ReserveCounterIncremented() throws PGOutOfAreaException, PGIncorrectUnitLocationException {
        int width = 8;
        int height = 6;
        PGPlayer player = new PGPlayer();
        PGPlayerArea playerArea = new PGPlayerArea(width, height, player);
        int sizeOfReserve = playerArea.getReserve().sizeOfReserve();
        long unitId = 0;
        PGUnitGroup group = null;
        PGUnitState state = PGUnitState.IDLE;
        PGUnitContainer primaryContainer = playerArea.getField(3,4);

        PGUnit unit = new PGUnitSmall(unitId, player, group, state, primaryContainer) {
            @Override
            public void activate(@NotNull PGActivationType activationType) {

            }

            @NotNull
            @Override
            public PGUnitActivationCheckerCallable getActivationCheckerThread() {
                return null;
            }

            @Override
            public int getPriority() {
                return 0;
            }

            @NotNull
            @Override
            public String getName() {
                return null;
            }

            @NotNull
            @Override
            public String getDescription() {
                return null;
            }

            @Override
            public PGUnitRank getRank() {
                return null;
            }

            @Override
            public void applyEffect(PGUnitEffect effect) {

            }

            @Override
            public void removeEffect(PGUnitEffect effect) {

            }

            @NotNull
            @Override
            public PGUnitAction getUnitAction() {
                return null;
            }
        };

        playerArea.moveUnitToReserve(unit);
        assertEquals(sizeOfReserve + 1, playerArea.getReserve().sizeOfReserve());
    }

    @Test
    public void moveUnitToReserve_OnePGUnitSmallOnTheGridMovedToReserve_UnitHasClearedUnitContainers() throws PGOutOfAreaException, PGIncorrectUnitLocationException {
        int width = 8;
        int height = 6;
        PGPlayer player = new PGPlayer();
        PGPlayerArea playerArea = new PGPlayerArea(width, height, player);
        long unitId = 0;
        PGUnitGroup group = null;
        PGUnitState state = PGUnitState.IDLE;
        PGUnitContainer primaryContainer = playerArea.getField(3,4);

        PGUnit unit = new PGUnitSmall(unitId, player, group, state, primaryContainer) {
            @Override
            public void activate(@NotNull PGActivationType activationType) {

            }

            @NotNull
            @Override
            public PGUnitActivationCheckerCallable getActivationCheckerThread() {
                return null;
            }

            @Override
            public int getPriority() {
                return 0;
            }

            @NotNull
            @Override
            public String getName() {
                return null;
            }

            @NotNull
            @Override
            public String getDescription() {
                return null;
            }

            @Override
            public PGUnitRank getRank() {
                return null;
            }

            @Override
            public void applyEffect(PGUnitEffect effect) {

            }

            @Override
            public void removeEffect(PGUnitEffect effect) {

            }

            @NotNull
            @Override
            public PGUnitAction getUnitAction() {
                return null;
            }
        };

        playerArea.moveUnitToReserve(unit);
        assertTrue(unit.getCurrentUnitContainers().isEmpty());
    }

    @Test
    public void moveUnitToReserve_OnePGUnitSmallOnTheGridMovedToReserve_GridDoesNotContainUnitAnymore() throws PGOutOfAreaException, PGIncorrectUnitLocationException {
        int width = 8;
        int height = 6;
        PGPlayer player = new PGPlayer();
        PGPlayerArea playerArea = new PGPlayerArea(width, height, player);
        long unitId = 0;
        PGUnitGroup group = null;
        PGUnitState state = PGUnitState.IDLE;
        PGUnitContainer primaryContainer = playerArea.getField(3,4);

        PGUnit unit = new PGUnitSmall(unitId, player, group, state, primaryContainer) {
            @Override
            public void activate(@NotNull PGActivationType activationType) {

            }

            @NotNull
            @Override
            public PGUnitActivationCheckerCallable getActivationCheckerThread() {
                return null;
            }

            @Override
            public int getPriority() {
                return 0;
            }

            @NotNull
            @Override
            public String getName() {
                return null;
            }

            @NotNull
            @Override
            public String getDescription() {
                return null;
            }

            @Override
            public PGUnitRank getRank() {
                return null;
            }

            @Override
            public void applyEffect(PGUnitEffect effect) {

            }

            @Override
            public void removeEffect(PGUnitEffect effect) {

            }

            @NotNull
            @Override
            public PGUnitAction getUnitAction() {
                return null;
            }
        };

        playerArea.moveUnitToReserve(unit);
        assertNull(primaryContainer.getContainedUnit());
    }

    @Test
    public void moveUnitToReserve_BiggerUnitOnTheGridMovedToReserve_ReserveCounterIncremented() throws PGOutOfAreaException, PGIncorrectUnitLocationException {
        int width = 8;
        int height = 6;
        PGPlayer player = new PGPlayer();
        PGPlayerArea playerArea = new PGPlayerArea(width, height, player);
        int sizeOfReserve = playerArea.getReserve().sizeOfReserve();
        long unitId = 0;
        PGUnitGroup group = null;
        PGUnitState state = PGUnitState.IDLE;
        PGUnitContainer primaryContainer = playerArea.getField(1,3);

        PGUnit unit = new PGUnitLarge(unitId, player, group, state, primaryContainer) {
            @Override
            public void activate(@NotNull PGActivationType activationType) {

            }

            @NotNull
            @Override
            public PGUnitActivationCheckerCallable getActivationCheckerThread() {
                return null;
            }

            @Override
            public int getPriority() {
                return 0;
            }

            @NotNull
            @Override
            public String getName() {
                return null;
            }

            @NotNull
            @Override
            public String getDescription() {
                return null;
            }

            @Override
            public PGUnitRank getRank() {
                return null;
            }

            @Override
            public void applyEffect(PGUnitEffect effect) {

            }

            @Override
            public void removeEffect(PGUnitEffect effect) {

            }

            @NotNull
            @Override
            public PGUnitAction getUnitAction() {
                return null;
            }
        };

        playerArea.moveUnitToReserve(unit);
        assertEquals(sizeOfReserve + 1, playerArea.getReserve().sizeOfReserve());
    }

    @Test
    public void moveUnitToReserve_BiggerUnitOnTheGridMovedToReserve_UnitHasClearedUnitContainers() throws PGOutOfAreaException, PGIncorrectUnitLocationException {
        int width = 8;
        int height = 6;
        PGPlayer player = new PGPlayer();
        PGPlayerArea playerArea = new PGPlayerArea(width, height, player);
        long unitId = 0;
        PGUnitGroup group = null;
        PGUnitState state = PGUnitState.IDLE;
        PGUnitContainer primaryContainer = playerArea.getField(1,3);

        PGUnit unit = new PGUnitLarge(unitId, player, group, state, primaryContainer) {
            @Override
            public void activate(@NotNull PGActivationType activationType) {

            }

            @NotNull
            @Override
            public PGUnitActivationCheckerCallable getActivationCheckerThread() {
                return null;
            }

            @Override
            public int getPriority() {
                return 0;
            }

            @NotNull
            @Override
            public String getName() {
                return null;
            }

            @NotNull
            @Override
            public String getDescription() {
                return null;
            }

            @Override
            public PGUnitRank getRank() {
                return null;
            }

            @Override
            public void applyEffect(PGUnitEffect effect) {

            }

            @Override
            public void removeEffect(PGUnitEffect effect) {

            }

            @NotNull
            @Override
            public PGUnitAction getUnitAction() {
                return null;
            }
        };

        playerArea.moveUnitToReserve(unit);
        assertTrue(unit.getCurrentUnitContainers().isEmpty());
    }

    @Test
    public void moveUnitToReserve_BiggerUnitOnTheGridMovedToReserve_GridDoesNotContainUnitAnymore() throws PGOutOfAreaException, PGIncorrectUnitLocationException {
        int width = 8;
        int height = 6;
        PGPlayer player = new PGPlayer();
        PGPlayerArea playerArea = new PGPlayerArea(width, height, player);
        long unitId = 0;
        PGUnitGroup group = null;
        PGUnitState state = PGUnitState.IDLE;
        PGField primaryContainer = playerArea.getField(1,3);

        PGUnit unit = new PGUnitLarge(unitId, player, group, state, primaryContainer) {
            @Override
            public void activate(@NotNull PGActivationType activationType) {

            }

            @NotNull
            @Override
            public PGUnitActivationCheckerCallable getActivationCheckerThread() {
                return null;
            }

            @Override
            public int getPriority() {
                return 0;
            }

            @NotNull
            @Override
            public String getName() {
                return null;
            }

            @NotNull
            @Override
            public String getDescription() {
                return null;
            }

            @Override
            public PGUnitRank getRank() {
                return null;
            }

            @Override
            public void applyEffect(PGUnitEffect effect) {

            }

            @Override
            public void removeEffect(PGUnitEffect effect) {

            }

            @NotNull
            @Override
            public PGUnitAction getUnitAction() {
                return null;
            }
        };

        playerArea.moveUnitToReserve(unit);
        assertNull(primaryContainer.getContainedUnit());
        assertNull(primaryContainer.getRightNeighbor().getContainedUnit());
        assertNull(primaryContainer.getRearNeighbor().getContainedUnit());
        assertNull(primaryContainer.getRightNeighbor().getRearNeighbor().getContainedUnit());
    }
}