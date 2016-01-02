package pl.kaqu.pg.engine.unit;

import pl.kaqu.pg.engine.player.PGPlayer;
import pl.kaqu.pg.engine.unit.effect.PGUnitEffect;
import pl.kaqu.pg.engine.unit.effect.PGUnitState;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
public abstract class PGUnit implements Serializable {

    public final long unitID;

    protected PGPlayer owner;
    protected PGUnitAssociation association;
    protected PGUnitState state;

    protected List<PGUnitEffect> currentEffects;

    protected PGUnit(long unitID, PGPlayer owner, PGUnitAssociation association){
        this.unitID = unitID;
        if (owner != null){
            this.owner = owner;
        } else {
            this.owner = PGPlayer.NO_PLAYER;
        }
        if (association != null){
            this.association = association;
        } else {
            this.association = PGUnitAssociation.NONE;
        }
        this.state = PGUnitState.IDLE;
        this.currentEffects = new ArrayList<>();
    }

    public PGPlayer getOwner() {
        return owner;
    }

    public PGUnitAssociation getAssociation() {
        return association;
    }

    protected void setAssociation(PGUnitAssociation association) {
        this.association = association;
    }

    public PGUnitState getState() {
        return state;
    }

    public void setState(PGUnitState state) {
        this.state = state;
    }

    public abstract String getName();
    public abstract String getDescription();
    public abstract PGUnitRank getRank();

    public abstract void applyEffect(PGUnitEffect effect);
    public abstract void removeEffect(PGUnitEffect effect);

    public List<PGUnitEffect> getCurrentEffects() {
        return currentEffects;
    }
}
