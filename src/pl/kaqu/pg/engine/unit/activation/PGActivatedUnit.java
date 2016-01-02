package pl.kaqu.pg.engine.unit.activation;

/*
    PuzzleGenerals
    Copyright (C) 2016 kaqu

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

public interface PGActivatedUnit {

    /**
     * Call when activating unit. If it is possible by provided type of activation makes unit activated.
     * @param activationType what type of activation was performed
     * @return true if unit was successfully activated, otherwise false
     */
    boolean activate(PGUnitActivationType activationType);

    /**
     * @return what type of activation is available for unit.
     */
    PGUnitActivationType getActivationType();

}
