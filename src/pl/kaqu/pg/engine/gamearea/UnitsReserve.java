package pl.kaqu.pg.engine.gamearea;

/*
	PuzzleGenerals
	Copyright (C) 2016 Zixxy
	
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

import java.util.Collection;
import java.util.Map;

import pl.kaqu.pg.engine.unit.PGUnit;


public class UnitsReserve {

	private Collection<PGUnit> units;
	
	public UnitsReserve(Collection<PGUnit> units) {
		this.units = units;
	}
	
	public void addUnitToReserve(PGUnit unit) {
		units.add(unit);
	}
	
	public Map<PGCoordinate ,PGUnit> provideUnits() {
		//TODO provide random units that meet game conditions.
		return null;
	}
	
	public int sizeOfReserve() {
		return units.size();
	}
}
