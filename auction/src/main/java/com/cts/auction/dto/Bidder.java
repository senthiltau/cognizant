package com.cts.auction.dto;

import java.util.Objects;

public final class Bidder {

    private final String bidderName;

    private Bidder(Builder builder) {
        this.bidderName = builder.getBidderName();
    }

    public String getBidderName() {
        return bidderName;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bidder bidder = (Bidder) o;
        return bidderName.equals(bidder.bidderName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bidderName);
    }

    @Override
    public String toString() {
        return "Bidder{" +
                "bidderName='" + bidderName + '\'' +
                '}';
    }

    public static class Builder {
        private String bidderName;

        public Builder() {
        }

        public Builder(final Bidder bidder) {
            this.bidderName = bidder.getBidderName();
        }

        public Builder bidderName(final String bidderName) {
            this.bidderName = bidderName;
            return this;
        }

        public String getBidderName() {
            return bidderName;
        }

        public static Builder builder() {
            return new Builder();
        }

        public Bidder build() {
            return new Bidder(this);
        }
    }
}
