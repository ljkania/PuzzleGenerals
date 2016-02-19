package pl.kaqu.pg.engine.gamearea;

import org.jetbrains.annotations.NotNull;
import org.junit.Before;
import org.junit.Test;
import pl.kaqu.pg.engine.error.PGError;
import pl.kaqu.pg.engine.gamearea.PGPlayerArea;
import pl.kaqu.pg.engine.gamearea.behaviour.dispatcher.PGMoveDispatcher;
import pl.kaqu.pg.engine.gamearea.behaviour.dispatcher.PGOrderDispatcher;
import pl.kaqu.pg.engine.player.PGPlayer;
import pl.kaqu.pg.engine.unit.PGUnit;
import pl.kaqu.pg.engine.unit.PGUnitRank;
import pl.kaqu.pg.engine.unit.action.PGUnitAction;
import pl.kaqu.pg.engine.unit.activation.PGActivationType;
import pl.kaqu.pg.engine.unit.activation.PGUnitActivationCheckerCallable;
import pl.kaqu.pg.engine.unit.effect.PGUnitEffect;
import pl.kaqu.pg.engine.unit.effect.PGUnitState;
import pl.kaqu.pg.engine.unit.types.PGUnitHigh;
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

public class PGMoveDispatcherTest {
    @Test
    public void pickUnitToHand_PickFromEmptyField_HandContainsNull() throws PGError {
        int width = 8;
        int height = 6;
        PGPlayer player = new PGPlayer();
        PGPlayerArea playerArea = new PGPlayerArea(width, height, player);

        PGMoveDispatcher.pickUnitToHand(playerArea.getField(1,3), playerArea.hand);

        assertNull(playerArea.hand.getContainedUnit());
    }

    @Test
    public void pickUnitToHand_PickUnitWhenHandIsNotEmpty_HandContainsPreviousUnit() throws PGError {
        int width = 8;
        int height = 6;
        PGPlayer player = new PGPlayer();
        PGPlayerArea playerArea = new PGPlayerArea(width, height, player);
        PGUnit unit1 = new PGUnitLarge(0, player, null, PGUnitState.IDLE, playerArea.getField(1, 3)) {
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

            @Override
            public void activate(@NotNull PGActivationType activationType) {

            }

            @NotNull
            @Override
            public PGUnitActivationCheckerCallable getActivationCheckerThread() {
                return null;
            }
        };
        PGUnit unit2 = new PGUnitLarge(0, player, null, PGUnitState.IDLE, playerArea.hand) {
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

            @Override
            public void activate(@NotNull PGActivationType activationType) {

            }

            @NotNull
            @Override
            public PGUnitActivationCheckerCallable getActivationCheckerThread() {
                return null;
            }
        };


        PGMoveDispatcher.pickUnitToHand(playerArea.getField(1,3), playerArea.hand);

        assertEquals(unit2, playerArea.hand.getContainedUnit());
    }

    @Test
    public void pickUnitToHand_PickUnitWhenHandIsNotEmpty_UnitStaysOnGrid() throws PGError {
        int width = 8;
        int height = 6;
        PGPlayer player = new PGPlayer();
        PGPlayerArea playerArea = new PGPlayerArea(width, height, player);
        PGUnit unit1 = new PGUnitLarge(0, player, null, PGUnitState.IDLE, playerArea.getField(1, 3)) {
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

            @Override
            public void activate(@NotNull PGActivationType activationType) {

            }

            @NotNull
            @Override
            public PGUnitActivationCheckerCallable getActivationCheckerThread() {
                return null;
            }
        };
        PGUnit unit2 = new PGUnitLarge(0, player, null, PGUnitState.IDLE, playerArea.hand) {
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

            @Override
            public void activate(@NotNull PGActivationType activationType) {

            }

            @NotNull
            @Override
            public PGUnitActivationCheckerCallable getActivationCheckerThread() {
                return null;
            }
        };

        PGMoveDispatcher.pickUnitToHand(playerArea.getField(1,3), playerArea.hand);

        assertEquals(unit1, playerArea.getField(1,3).getContainedUnit());
    }

    @Test
    public void pickUnitToHand_LargeUnitPickedFromPrimaryContainer_HandContainsUnit() throws PGError {
        int width = 8;
        int height = 6;
        PGPlayer player = new PGPlayer();
        PGPlayerArea playerArea = new PGPlayerArea(width, height, player);

        PGUnit unit = new PGUnitLarge(0, player, null, PGUnitState.IDLE, playerArea.getField(1, 3)) {
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

            @Override
            public void activate(@NotNull PGActivationType activationType) {

            }

            @NotNull
            @Override
            public PGUnitActivationCheckerCallable getActivationCheckerThread() {
                return null;
            }
        };

        PGMoveDispatcher.pickUnitToHand(playerArea.getField(1,3), playerArea.hand);

        assertEquals(unit, playerArea.hand.getContainedUnit());
    }

    @Test
    public void pickUnitToHand_LargeUnitPickedFromNotPrimaryContainer_HandContainsUnit() throws PGError {
        int width = 8;
        int height = 6;
        PGPlayer player = new PGPlayer();
        PGPlayerArea playerArea = new PGPlayerArea(width, height, player);

        PGUnit unit = new PGUnitLarge(0, player, null, PGUnitState.IDLE, playerArea.getField(1, 3)) {
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

            @Override
            public void activate(@NotNull PGActivationType activationType) {

            }

            @NotNull
            @Override
            public PGUnitActivationCheckerCallable getActivationCheckerThread() {
                return null;
            }
        };

        PGMoveDispatcher.pickUnitToHand(playerArea.getField(2,4), playerArea.hand);

        assertEquals(unit, playerArea.hand.getContainedUnit());
    }

    @Test
    public void pickUnitToHand_LargeUnitPickedFromPrimaryContainer_UnitIsNotOnTheGridAnymore() throws PGError {
        int width = 8;
        int height = 6;
        PGPlayer player = new PGPlayer();
        PGPlayerArea playerArea = new PGPlayerArea(width, height, player);

        new PGUnitLarge(0, player, null, PGUnitState.IDLE, playerArea.getField(1, 3)) {
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

            @Override
            public void activate(@NotNull PGActivationType activationType) {

            }

            @NotNull
            @Override
            public PGUnitActivationCheckerCallable getActivationCheckerThread() {
                return null;
            }
        };

        PGMoveDispatcher.pickUnitToHand(playerArea.getField(1,3), playerArea.hand);

        assertNull(playerArea.getField(1,3).getContainedUnit());
        assertNull(playerArea.getField(2,3).getContainedUnit());
        assertNull(playerArea.getField(1,4).getContainedUnit());
        assertNull(playerArea.getField(2,4).getContainedUnit());
    }

    @Test
    public void pickUnitToHand_LargeUnitPickedFromNotPrimaryContainer_UnitIsNotOnTheGridAnymore() throws PGError {
        int width = 8;
        int height = 6;
        PGPlayer player = new PGPlayer();
        PGPlayerArea playerArea = new PGPlayerArea(width, height, player);

        new PGUnitLarge(0, player, null, PGUnitState.IDLE, playerArea.getField(1, 3)) {
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

            @Override
            public void activate(@NotNull PGActivationType activationType) {

            }

            @NotNull
            @Override
            public PGUnitActivationCheckerCallable getActivationCheckerThread() {
                return null;
            }
        };

        PGMoveDispatcher.pickUnitToHand(playerArea.getField(2,4), playerArea.hand);

        assertNull(playerArea.getField(1,3).getContainedUnit());
        assertNull(playerArea.getField(2,3).getContainedUnit());
        assertNull(playerArea.getField(1,4).getContainedUnit());
        assertNull(playerArea.getField(2,4).getContainedUnit());
    }

    @Test
    public void dropUnitFromHand_DropFromEmptyHand_HandContainsNull() throws PGError {
        int width = 8;
        int height = 6;
        PGPlayer player = new PGPlayer();
        PGPlayerArea playerArea = new PGPlayerArea(width, height, player);

        PGMoveDispatcher.dropUnitFromHand(playerArea.hand, playerArea.getField(1,3));

        assertNull(playerArea.hand.getContainedUnit());
    }

    @Test
    public void dropUnitFromHand_DropFromEmptyHand_GridHasNotChanged() throws PGError {
        int width = 8;
        int height = 6;
        PGPlayer player = new PGPlayer();
        PGPlayerArea playerArea = new PGPlayerArea(width, height, player);

        PGMoveDispatcher.dropUnitFromHand(playerArea.hand, playerArea.getField(1,3));

        assertNull(playerArea.getField(1,3).getContainedUnit());
    }

    @Test
    public void dropUnitFromHand_LargeUnitDropedOnEmptyContainers_HandContainsNull() throws PGError {
        int width = 8;
        int height = 6;
        PGPlayer player = new PGPlayer();
        PGPlayerArea playerArea = new PGPlayerArea(width, height, player);

        PGUnit unit = new PGUnitLarge(0, player, null, PGUnitState.IDLE, playerArea.hand) {
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

            @Override
            public void activate(@NotNull PGActivationType activationType) {

            }

            @NotNull
            @Override
            public PGUnitActivationCheckerCallable getActivationCheckerThread() {
                return null;
            }
        };

        PGMoveDispatcher.dropUnitFromHand(playerArea.hand, playerArea.getField(2,4));

        assertNull(playerArea.hand.getContainedUnit());
    }

    @Test
    public void dropUnitFromHand_LargeUnitDropedOnEmptyContainers_UnitIsOnGrid() throws PGError {
        int width = 8;
        int height = 6;
        PGPlayer player = new PGPlayer();
        PGPlayerArea playerArea = new PGPlayerArea(width, height, player);

        PGUnit unit = new PGUnitLarge(0, player, null, PGUnitState.IDLE, playerArea.getField(1, 3)) {
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

            @Override
            public void activate(@NotNull PGActivationType activationType) {

            }

            @NotNull
            @Override
            public PGUnitActivationCheckerCallable getActivationCheckerThread() {
                return null;
            }
        };

        PGMoveDispatcher.dropUnitFromHand(playerArea.hand, playerArea.getField(2,4));

        assertEquals(unit, playerArea.getField(2,4).getContainedUnit());
    }

    @Test
    public void dropUnitFromHand_LargeUnitDropedOnNotEmptyContainers_HandDoesNotChange() throws PGError {
        int width = 8;
        int height = 6;
        PGPlayer player = new PGPlayer();
        PGPlayerArea playerArea = new PGPlayerArea(width, height, player);

        PGUnit unit1 = new PGUnitLarge(0, player, null, PGUnitState.IDLE, playerArea.hand) {
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

            @Override
            public void activate(@NotNull PGActivationType activationType) {

            }

            @NotNull
            @Override
            public PGUnitActivationCheckerCallable getActivationCheckerThread() {
                return null;
            }
        };
        PGUnit unit2 = new PGUnitSmall(0, player, null, PGUnitState.IDLE, playerArea.getField(3,5)) {
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

            @Override
            public void activate(@NotNull PGActivationType activationType) {

            }

            @NotNull
            @Override
            public PGUnitActivationCheckerCallable getActivationCheckerThread() {
                return null;
            }
        };

        PGMoveDispatcher.dropUnitFromHand(playerArea.hand, playerArea.getField(2,4));

        assertEquals(unit1, playerArea.hand.getContainedUnit());
    }

    @Test
    public void dropUnitFromHand_LargeUnitDropedOnNotEmptyContainers_GridDoesNotChange() throws PGError {
        int width = 8;
        int height = 6;
        PGPlayer player = new PGPlayer();
        PGPlayerArea playerArea = new PGPlayerArea(width, height, player);

        PGUnit unit1 = new PGUnitLarge(0, player, null, PGUnitState.IDLE, playerArea.hand) {
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

            @Override
            public void activate(@NotNull PGActivationType activationType) {

            }

            @NotNull
            @Override
            public PGUnitActivationCheckerCallable getActivationCheckerThread() {
                return null;
            }
        };
        PGUnit unit2 = new PGUnitSmall(0, player, null, PGUnitState.IDLE, playerArea.getField(3,5)) {
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

            @Override
            public void activate(@NotNull PGActivationType activationType) {

            }

            @NotNull
            @Override
            public PGUnitActivationCheckerCallable getActivationCheckerThread() {
                return null;
            }
        };

        PGMoveDispatcher.dropUnitFromHand(playerArea.hand, playerArea.getField(2,4));

        assertNull(playerArea.getField(2,4).getContainedUnit());
        assertNull(playerArea.getField(3,4).getContainedUnit());
        assertNull(playerArea.getField(2,5).getContainedUnit());
        assertEquals(unit2, playerArea.getField(3,5).getContainedUnit());
    }
}