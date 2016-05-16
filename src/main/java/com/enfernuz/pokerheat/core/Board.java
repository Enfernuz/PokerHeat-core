package com.enfernuz.pokerheat.core;

import java.util.List;

import com.google.common.collect.*;

import com.google.common.collect.Lists;

import com.enfernuz.pokerheat.core.Street.StreetType;

/**
 *
 * @author A. Nerushev
 */
public final class Board extends AbstractLimitedCollection<Card> {

    private final List<Street> streets;
    
    private Board() {
        
        super( StreetType.FLOP.getSize() + StreetType.TURN.getSize() + StreetType.RIVER.getSize() );
        
        streets = Lists.newArrayListWithExpectedSize(3);
        
        streets.add( Street.of(StreetType.FLOP) );
        streets.add( Street.of(StreetType.TURN) );
        streets.add( Street.of(StreetType.RIVER) );
    }
    
    public static Board of() {
        return new Board();
    }

    @Override
    protected void addElement(Card card) {
    
        for (Street street : streets) {
            if ( !street.isFull() ) {
                street.addElement(card);
                break;
            }
        }
    }

    @Override
    protected Card getElement() {
        
        Card result = null;
        
        for (int i = streets.size() - 1; i >= 0 ; i--) {
            if( !streets.get(i).isEmpty() ) {
                result = streets.get(i).get();
                break;
            }
        }
        
        return result;
    }

    @Override
    public ImmutableCollection<Card> getElements() {
        
        final ImmutableCollection.Builder<Card> cards = ImmutableList.builder();
        streets.forEach( 
                street -> cards.addAll( street.getElements() ) 
        );
        
        return cards.build();
    }

    @Override
    public boolean removeAll(Card e) {
        throw new UnsupportedOperationException("Arbitrary removal of cards from a board is prohibited.");
    }

    public ImmutableList<Street> getStreets() {
        return ImmutableList.copyOf(streets);
    }

}
