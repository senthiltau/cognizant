package com.cts.auction.dto;

import java.math.BigDecimal;
import java.util.Objects;

public final class BidInformation {

    private final Bidder bidder;

    private final BigDecimal startingBid;

    private final BigDecimal maximumBid;

    private final BigDecimal autoIncrementAmount;

    private final Integer bidderEntryNumber;


    private BidInformation(final Builder builder) {
        this.bidder = builder.getBidder();
        this.startingBid = builder.getStartingBid();
        this.maximumBid = builder.getMaximumBid();
        this.autoIncrementAmount = builder.getAutoIncrementAmount();
        this.bidderEntryNumber = builder.getBidderEntryNumber();
    }

    public Bidder getBidder() {
        return bidder;
    }

    public BigDecimal getStartingBid() {
        return startingBid;
    }

    public BigDecimal getMaximumBid() {
        return maximumBid;
    }

    public BigDecimal getAutoIncrementAmount() {
        return autoIncrementAmount;
    }

    public Integer getBidderEntryNumber() {
        return bidderEntryNumber;
    }

    public Builder getBuilder() {
        return new Builder(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BidInformation that = (BidInformation) o;
        return bidderEntryNumber == that.bidderEntryNumber &&
                bidder.equals(that.bidder) &&
                startingBid.equals(that.startingBid) &&
                maximumBid.equals(that.maximumBid) &&
                autoIncrementAmount.equals(that.autoIncrementAmount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bidder, startingBid, maximumBid, autoIncrementAmount, bidderEntryNumber);
    }

    @Override
    public String toString() {
        return "BidInformation{" +
                "bidder=" + bidder +
                ", startingBid=" + startingBid +
                ", maximumBid=" + maximumBid +
                ", autoIncrementAmount=" + autoIncrementAmount +
                ", bidderEntryNumber=" + bidderEntryNumber +
                '}';
    }

    public static class Builder {
        private Bidder bidder;

        private BigDecimal startingBid;

        private BigDecimal maximumBid;

        private BigDecimal autoIncrementAmount;

        private Integer bidderEntryNumber;

        public Builder() {
        }

        public Builder(final BidInformation bidInformation) {
            this.bidder = bidInformation.getBidder();
            this.startingBid = bidInformation.getStartingBid();
            this.maximumBid = bidInformation.getMaximumBid();
            this.autoIncrementAmount = bidInformation.getAutoIncrementAmount();
            this.bidderEntryNumber = bidInformation.getBidderEntryNumber();
        }

        public Builder bidder(final Bidder bidder) {
            this.bidder = bidder;
            return this;
        }

        public Builder startingBid(final BigDecimal startingBid) {
            this.startingBid = startingBid;
            return this;
        }

        public Builder maximumBid(final BigDecimal maximumBid) {
            this.maximumBid = maximumBid;
            return this;
        }

        public Builder autoIncrementAmount(final BigDecimal autoIncrementAmount) {
            this.autoIncrementAmount = autoIncrementAmount;
            return this;
        }

        public Builder bidderEntryNumber(final Integer bidderEntryNumber) {
            this.bidderEntryNumber = bidderEntryNumber;
            return this;
        }

        public Bidder getBidder() {
            return bidder;
        }

        public BigDecimal getStartingBid() {
            return startingBid;
        }

        public BigDecimal getMaximumBid() {
            return maximumBid;
        }

        public BigDecimal getAutoIncrementAmount() {
            return autoIncrementAmount;
        }

        public int getBidderEntryNumber() {
            return bidderEntryNumber;
        }

        public static Builder builder() {
            return new Builder();
        }

        public BidInformation build() {
            return new BidInformation(this);
        }
    }
}
