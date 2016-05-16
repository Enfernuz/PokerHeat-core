package com.enfernuz.pokerheat.core;

import java.util.Objects;

import java.util.concurrent.*;

/**
 *
 * @author A. Nerushev
 */
public final class Card {
    
    private static final ConcurrentMap<Integer, Card> POOL;

    private final Rank rank;
    private final Suit suit;
    
    private transient int hashCode;
    private transient String stringette;
    
    static {
        POOL = new ConcurrentHashMap<>();
    }
    
    private Card(Rank rank, Suit suit) {
        
        this.rank = rank;
        this.suit = suit;

        this.stringette = "";
    }
    
    public static Card of(Rank rank, Suit suit) {
        
        final int hash = Objects.hash(rank, suit);
        
        return POOL.computeIfAbsent(hash, value -> {
            
            final Card card = new Card(rank, suit);
            card.hashCode = hash;
            return card;
        });
    }
    
    @Override
    public boolean equals(Object obj) {
        
        if (obj == this) {
            return true;
        } else if ( !(obj instanceof Card) ) {
            return false;
        } else {
            final Card other = (Card) obj;
            return Objects.equals(suit, other.suit)
                    && Objects.equals(rank, other.rank);
        }
    }
    
    @Override
    public int hashCode() {  
        return hashCode;
    }
    
    @Override
    public String toString() {
        
        switch (stringette) {
            case "":
                stringette = String.format("%c%c", rank.getLetter(), suit.getLetter());
            default:
                return stringette;
        }
    }
    
    
    public Rank getRank() {
        return rank;
    }
    
    public Suit getSuit() {
        return suit;
    }
    
    public static enum Rank {
        
        TWO('2', 0),
        THREE('3', 1),
        FOUR('4', 2),
        FIVE('5', 3),
        SIX('6', 4),
        SEVEN('7', 5),
        EIGHT('8', 6),
        NINE('9', 7),
        TEN('T', 8),
        JACK('J', 9),
        QUEEN('Q', 10),
        KING('K', 11),
        ACE('A', 12);
        
        private final char letter;
        private final int index;
        
        private Rank(char letter, int index) {
            this.letter = letter;
            this.index = index;
        }
        
        public char getLetter() {
            return letter;
        }
        
        public int getIndex() {
            return index;
        }
        
        public static Rank fromChar(char letter) {

            Rank result = null;
            for ( Rank rank : Rank.values() ) {
                
                if ( String.CASE_INSENSITIVE_ORDER.compare( String.valueOf( rank.getLetter() ), String.valueOf(letter) ) == 0 ) {
                    result = rank;
                    break;
                }
                
            }
            
            if (result == null) {
                throw new IllegalArgumentException( String.format("Couldn't find a rank for letter '%c'.", letter) );
            } 
            
            return result;
        }

    }
    
    public static enum Suit {
        
        HEARTS('h', 0),
        DIAMONDS('d', 1),
        CLUBS('c', 2),
        SPADES('s', 3);
        
        private final char letter;
        private final int index;
        
        private Suit(char letter, int index) {
            this.letter = letter;
            this.index = index;
        }
        
        public char getLetter() {
            return letter;
        }

        public int getIndex() {
            return index;
        }
        
        public static Suit fromChar(char letter) {
            
            Suit result = null;
            for ( Suit suit : Suit.values() ) {
                
                if ( String.CASE_INSENSITIVE_ORDER.compare( String.valueOf( suit.getLetter() ), String.valueOf(letter) ) == 0 ) {
                    result = suit;
                    break;
                }
                
            }
            
            if (result == null) {
                throw new IllegalArgumentException( String.format("Couldn't find a suit for letter '%c'.", letter) );
            }
            
            return result;
        }
        
    }
    
}
