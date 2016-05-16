package com.enfernuz.pokerheat.core;

import com.google.common.base.MoreObjects;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author A. Nerushev
 */
public class Equity {

    private final AtomicInteger win;
    private final AtomicInteger loss;
    private final AtomicInteger tie;
    
    public Equity() {
        
        this.win = new AtomicInteger(0);
        this.loss = new AtomicInteger(0);
        this.tie = new AtomicInteger(0);
    }
    
    public void addWin() {
        win.incrementAndGet();
    }
    
    public void addLoss() {
        loss.incrementAndGet();
    }
    
    public void addTie() {
        tie.incrementAndGet();
    }
    
    public int getWinOdds() {
        return calculateOdds(OddsType.WIN);
    }
    
    public int getLossOdds() {
        return calculateOdds(OddsType.LOSS);
    }
    
    public int getTieOdds() {
        return calculateOdds(OddsType.TIE);
    }
    
    @Override
    public String toString() {
        
        return MoreObjects.toStringHelper(this)
                .add( "wins", win.get() )
                .add( "losses", loss.get() )
                .add( "ties", tie.get() )
                .toString();
    }
    
    private Integer calculateOdds(OddsType oddsType) {
            
            final int wins = win.get();
            final int losses = loss.get();
            final int ties = tie.get();
            
            final int overall = wins + losses + ties;
            
            if( overall == 0 ) {
                throw new IllegalStateException("Couldn't calculate equity as no predicted results has been given.");
            }

            final int share;
            
            switch(oddsType) {
                case WIN:
                    share = wins;
                    break;
                case LOSS:
                    share = losses;
                    break;
                case TIE:
                    share = ties;
                    break;
                default:
                    throw new IllegalArgumentException( String.format("Unknown odds type: '%s'.", oddsType.toString()) ); 
            }
            
            return BigDecimal.valueOf( (double) share / (double) overall )
                .setScale(2, RoundingMode.CEILING)
                .multiply(BigDecimal.valueOf(100L))
                .intValue();
    }
    
    private static enum OddsType { WIN, LOSS, TIE; }
}
