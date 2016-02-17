package pl.kaqu.pg.engine.unit.activation;

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


import org.jetbrains.annotations.NotNull;
import pl.kaqu.pg.engine.unit.PGUnit;

import java.util.concurrent.Callable;

// http://stackoverflow.com/questions/3929361/how-to-wait-for-all-tasks-in-an-threadpoolexecutor-to-finish-without-shutting-do
public abstract class PGUnitActivationCheckerCallable implements Callable<PGActivationType> {

    private final PGUnit unit;

    public PGUnitActivationCheckerCallable(@NotNull PGUnit unit) {
        this.unit = unit;
    }

    @Override
    public PGActivationType call() throws Exception {
        return checkActivation();
    }

    abstract @NotNull protected PGActivationType checkActivation();
}
