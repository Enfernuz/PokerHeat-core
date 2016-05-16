package com.enfernuz.pokerheat.core;

import java.util.*;

import java.util.concurrent.ConcurrentLinkedDeque;

import com.google.common.collect.*;

/**
 *
 * @author A. Nerushev
 */
public final class CommonCardDeck extends AbstractLimitedCollection<Card> {
    
    private static final int DECK_SIZE = 52;
    
    private final Deque<Card> cards;

    public static CommonCardDeck createNew() {
        return new CommonCardDeck();
    }
    
    private CommonCardDeck() {
        
        super(DECK_SIZE);
        
        cards = new ConcurrentLinkedDeque<>();
        
        for ( Card.Suit suit : Card.Suit.values() ) {
            for ( Card.Rank value : Card.Rank.values() ) {
                addElement( Card.of(value, suit) );
            }
        }

    }
    
    @Override
    protected void addElement(Card card) {
        
        Objects.requireNonNull(card != null, "The parameter 'card' must not be null.");
        
        cards.addLast(card);
    }

    @Override
    protected Card getElement() {
        return cards.pollLast();
    }

    @Override
    public ImmutableList<Card> getElements() {
        return ImmutableList.copyOf(cards);
    }

    protected void shuffle(int times) {
        
        final Random rnd = new Random( System.nanoTime() );
        
        final List<Card> tempCards = Lists.newArrayListWithExpectedSize( cards.size() );
        tempCards.addAll(cards);
        
        for (int i = 0; i < times; i++) {
            Collections.shuffle(tempCards, rnd);
        }

        cards.clear();
        cards.addAll(tempCards);
    }
    
    @Override
    public boolean removeAll(Card card) {
        return cards.removeIf( card::equals );
    }

}
