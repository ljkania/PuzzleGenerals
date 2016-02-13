package pl.kaqu.pg.engine.unit.types;

import com.sun.istack.internal.NotNull;
import pl.kaqu.pg.engine.error.PGError;
import pl.kaqu.pg.engine.gamearea.PGField;
import pl.kaqu.pg.engine.gamearea.PGUnitContainer;
import pl.kaqu.pg.engine.player.PGPlayer;
import pl.kaqu.pg.engine.unit.PGUnit;
import pl.kaqu.pg.engine.unit.PGUnitGroup;
import pl.kaqu.pg.engine.unit.activation.PGActivatedUnit;
import pl.kaqu.pg.engine.unit.activation.PGActivationType;
import pl.kaqu.pg.engine.unit.effect.PGUnitState;

import java.util.*;

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

public abstract class PGUnitHigh extends PGUnit implements PGActivatedUnit {
    Map<String, PGUnitContainer> containers;

    protected PGUnitHigh(long unitID, PGPlayer owner, PGUnitGroup group, PGUnitState state, @NotNull PGUnitContainer front_of_unit) throws PGError {
        super(unitID, owner, group, state);
        this.containers = new HashMap<>();

        if(front_of_unit instanceof PGField) {
            PGField back_of_unit = ((PGField) front_of_unit).getRearNeighbor();

            if(back_of_unit == null) {
                throw new PGError("Incorrect location of unit");
            }

            this.containers.put("front", front_of_unit);
            this.containers.put("back", back_of_unit);

            List<PGField> toObserve = new ArrayList<>();
            toObserve.add(back_of_unit.getRearNeighbor());
            toObserve.add(back_of_unit.getSecondRearNeighbor());

            if(!toObserve.contains(null)) {
                for(PGField field : toObserve) {
                    field.addObserver(this);
                }
            }

        }
    }

    @Override
    public void update(Observable obj, Object arg) {
        if(this.containers.get("back") instanceof PGField) {
            PGField field = (PGField) this.containers.get("back");
            List<PGUnit> units = new ArrayList<>();

            try {
                units.add(field.getRearNeighbor().getContainedUnit());
                units.add(field.getSecondRearNeighbor().getContainedUnit());
            } catch(NullPointerException e) {
                return;
            }

            if(units.contains(null)) {
                return;
            }

            Set<PGUnitGroup> unique_groups = new HashSet<>();
            unique_groups.add(this.group);
            for(PGUnit unit : units) {
                unique_groups.add(unit.getGroup());
            }

            if(unique_groups.size() == 1) {
                this.state = PGUnitState.ACTIVATED; //TODO: Perform real activation here
            }
        }
    }
}
