package com.enfernuz.pokerheat.core;

import java.util.Objects;

import com.google.common.collect.ImmutableSet;

import static java.util.Objects.*;

import static com.google.common.base.Preconditions.*;

/**
 *
 * @author A. Nerushev
 */
public final class Street extends AbstractLimitedSet<Card> {

    private final StreetType type;
    
    private Street(StreetType type) {
        
        super( type.getSize() );
        
        this.type = type;
    }
    
    private Street(StreetType type, ImmutableSet<? extends Card> cards) {
        
        super( type.getSize() );
        
        this.type = type;

        cards.forEach( this::addElement );
    }
    
    public static Street of(StreetType type) {
        
        requireNonNull(type != null, "The parameter 'type' must not be null.");
        
        return new Street(type);
    }
    
    public static Street of(StreetType type, ImmutableSet<? extends Card> cards) {
        
        requireNonNull(type != null, "The parameter 'type' must not be null.");
        checkArgument(
                cards.size() == type.getSize(), 
                String.format("The size of 'cards' must match the desired street type size (%d).", type.getSize())
        );
        
        return new Street(type, cards);
    }

    public StreetType getType() {
        return type;
    }
    
    public enum StreetType {

        FLOP(3),
        TURN(1),
        RIVER(1);

        private final int size;

        private StreetType(int size) {
            this.size = size;
        }

        public int getSize() {
            return size;
        }

    }
}
