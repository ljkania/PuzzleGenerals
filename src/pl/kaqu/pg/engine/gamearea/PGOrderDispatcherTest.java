package pl.kaqu.pg.engine.gamearea;

import org.jetbrains.annotations.NotNull;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import pl.kaqu.pg.engine.error.PGError;
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

public class PGOrderDispatcherTest {
    static int width;
    static int height;
    static PGPlayer player;
    PGPlayerArea playerArea;

    @BeforeClass
    public static void init() {
        width = 8;
        height = 6;
        player = new PGPlayer();
    }

    @Before
    public void initField() {
        playerArea = new PGPlayerArea(width, height, player);
    }

    @Test
    public void reorderUnits_EveryUnitHasTheSamePriorityAndStandAsCloseToFrontAsPossible_GridDoesNotChange() throws PGError {
        PGUnit unit1 = new PGUnitLarge(0, player, null, PGUnitState.IDLE, playerArea.getField(0,0)) {
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
        PGUnit unit2 = new PGUnitLarge(0, player, null, PGUnitState.IDLE, playerArea.getField(2,0)) {
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
        PGUnit unit3 = new PGUnitHigh(0, player, null, PGUnitState.IDLE, playerArea.getField(3,2)) {
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
        PGUnit unit4 = new PGUnitSmall(0, player, null, PGUnitState.IDLE, playerArea.getField(3,4)) {
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
        PGUnit unit5 = new PGUnitSmall(0, player, null, PGUnitState.IDLE, playerArea.getField(4,0)) {
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
        PGUnit unit6 = new PGUnitLarge(0, player, null, PGUnitState.IDLE, playerArea.getField(4,1)) {
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
        PGUnit unit7 = new PGUnitHigh(0, player, null, PGUnitState.IDLE, playerArea.getField(5,3)) {
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
        PGUnit unit8 = new PGUnitSmall(0, player, null, PGUnitState.IDLE, playerArea.getField(5,5)) {
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


        PGOrderDispatcher.reorderUnits(playerArea);

        assertEquals(unit1.getPrimaryUnitContainer(), playerArea.getField(0,0));
        assertEquals(unit2.getPrimaryUnitContainer(), playerArea.getField(2,0));
        assertEquals(unit3.getPrimaryUnitContainer(), playerArea.getField(3,2));
        assertEquals(unit4.getPrimaryUnitContainer(), playerArea.getField(3,4));
        assertEquals(unit5.getPrimaryUnitContainer(), playerArea.getField(4,0));
        assertEquals(unit6.getPrimaryUnitContainer(), playerArea.getField(4,1));
        assertEquals(unit7.getPrimaryUnitContainer(), playerArea.getField(5,3));
        assertEquals(unit8.getPrimaryUnitContainer(), playerArea.getField(5,5));
    }

    @Test
    public void reorderUnits_SmallUnitAtTheBackOfAnotherHasBiggerPriority_SmallIsMovedToFront() throws PGError {
        PGUnit unit1 = new PGUnitLarge(0, player, null, PGUnitState.IDLE, playerArea.getField(0,0)) {
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
        PGUnit unit2 = new PGUnitSmall(0, player, null, PGUnitState.IDLE, playerArea.getField(0,2)) {
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
                return 1;
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

        PGOrderDispatcher.reorderUnits(playerArea);

        assertEquals(unit1.getPrimaryUnitContainer(), playerArea.getField(0, 1));
        assertEquals(unit2.getPrimaryUnitContainer(), playerArea.getField(0,0));
    }

    @Test
    public void reorderUnits_OneUnitSomewhereOnTheGrid_UnitIsMovedToFrontRow() throws PGError {
        PGUnit unit = new PGUnitLarge(0, player, null, PGUnitState.IDLE, playerArea.getField(3,4)) {
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

        PGOrderDispatcher.reorderUnits(playerArea);

        assertEquals(unit.getPrimaryUnitContainer(), playerArea.getField(3,0));
    }

}