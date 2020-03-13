package com.cts.auction.dto;

import java.math.BigDecimal;
import java.util.Objects;

public final class BidWinnerDetails {
    private final Bidder bidder;

    private final BigDecimal winningBid;

    private final String auctionItem;

    private BidWinnerDetails(final Builder builder) {
        this.bidder = builder.getBidder();
        this.winningBid = builder.getWinningBid();
        this.auctionItem = builder.getAuctionItem();
    }

    public Bidder getBidder() {
        return bidder;
    }

    public BigDecimal getWinningBid() {
        return winningBid;
    }

    public String getAuctionItem() {
        return auctionItem;
    }

    public Builder builder() {
        return new Builder(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BidWinnerDetails that = (BidWinnerDetails) o;
        return bidder.equals(that.bidder) &&
                winningBid.equals(that.winningBid) &&
                auctionItem.equals(that.auctionItem);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bidder, winningBid, auctionItem);
    }

    @Override
    public String toString() {
        return "Bid Winner Details \n" +
                "Bidder:" + bidder.getBidderName() + "\n" +
                "Winning Bid amount:" + winningBid + "\n" +
                "Auctioned Item:" + auctionItem;
    }

    public static class Builder {
        private Bidder bidder;

        private  BigDecimal winningBid;

        private String auctionItem;

        public Builder(final BidWinnerDetails bidWinnerDetails) {
            this.bidder = bidWinnerDetails.getBidder();
            this.winningBid = bidWinnerDetails.getWinningBid();
            this.auctionItem = bidWinnerDetails.getAuctionItem();
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

        public Builder auctionItem(final String auctionItem) {
            this.auctionItem = auctionItem;
            return this;
        }

        public Bidder getBidder() {
            return bidder;
        }

        public BigDecimal getWinningBid() {
            return winningBid;
        }

        public String getAuctionItem() {
            return auctionItem;
        }

        public static Builder builder() {
            return new Builder();
        }

        public BidWinnerDetails build() {
            return new BidWinnerDetails(this);
        }
    }
}
