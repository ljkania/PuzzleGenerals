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

public enum PGActivationType {
    NONE, // if unit is not actually activated
    INDEPENDENT, // if unit is activated irregular way
    SINGLE_VARTICAL, // if unit is activated once - vertical
    SINGLE_HORIZONTAL, // if unit is activated once - horizontal
    DOUBLE, // if unit is double activated at the same time
    KEEPED_PART_OF, // if unit is part of activation and will not be removed
    REMOVED_PART_OF  // if unit is part of activation and will be removed
}
