package com.cts.auction.dto;

import java.math.BigDecimal;
import java.util.Objects;

public final class BidWinnerDetails {
    private final Bidder bidder;

    private final BigDecimal winningBid;

    private BidWinnerDetails(final Builder builder) {
        this.bidder = builder.getBidder();
        this.winningBid = builder.getWinningBid();
    }

    public Bidder getBidder() {
        return bidder;
    }

    public BigDecimal getWinningBid() {
        return winningBid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BidWinnerDetails that = (BidWinnerDetails) o;
        return bidder.equals(that.bidder) &&
                winningBid.equals(that.winningBid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bidder, winningBid);
    }

    @Override
    public String toString() {
        return "BidWinnerDetails{" +
                "bidder=" + bidder +
                ", winningBid=" + winningBid +
                '}';
    }

    public static class Builder {
        private Bidder bidder;

        private  BigDecimal winningBid;

        public Builder(final BidWinnerDetails bidWinnerDetails) {
            this.bidder = bidWinnerDetails.getBidder();
            this.winningBid = bidWinnerDetails.getWinningBid();
        }

        public Builder() {

        }

        public Builder bidder(final Bidder bidder) {
            this.bidder = bidder;
            return this;
        }

        public Builder winningBid(final BigDecimal winningBid) {
            this.winningBid = winningBid;
            return this;
        }

        public Bidder getBidder() {
            return bidder;
        }

        public BigDecimal getWinningBid() {
            return winningBid;
        }

        public static Builder builder() {
            return new Builder();
        }

        public BidWinnerDetails build() {
            return new BidWinnerDetails(this);
        }
    }
}
