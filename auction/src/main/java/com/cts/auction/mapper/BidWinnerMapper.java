package com.cts.auction.mapper;

import com.cts.auction.dto.BidInformation;
import com.cts.auction.dto.BidWinnerDetails;

import java.math.BigDecimal;

public class BidWinnerMapper {

    public static BidWinnerDetails mapBidWinnerDetails(final BidInformation bidInformation1, final BidInformation bidInformation2, final String auctionItem) {
        final BigDecimal maximumBidOfSecondBidder = bidInformation2 == null ? BigDecimal.ZERO : bidInformation2.getMaximumBid();
        return BidWinnerDetails.Builder.builder()
                .bidder(bidInformation1.getBidder())
                .winningBid(getIncrementedMaxBid(bidInformation1, maximumBidOfSecondBidder))
                .auctionItem(auctionItem)
                .build();
    }

    private static BigDecimal getIncrementedMaxBid(final BidInformation bidInformation, final BigDecimal maximumBidOfSecondBidder) {
        return bidInformation.getMaximumBid().subtract(maximumBidOfSecondBidder).compareTo(bidInformation.getAutoIncrementAmount()) < 0 ? bidInformation.getMaximumBid() :
                getWinningAmountByAddingAutoIncrement(bidInformation, maximumBidOfSecondBidder);
    }

    private static BigDecimal getWinningAmountByAddingAutoIncrement(final BidInformation bidInformation, final BigDecimal maximumBidOfSecondBidder) {
        final BigDecimal autoIncrementValue = bidInformation.getAutoIncrementAmount();
        BigDecimal result = bidInformation.getMaximumBid();

        for (BigDecimal index = result; index.compareTo(maximumBidOfSecondBidder) > 0; index.subtract(autoIncrementValue)) {
            result = result.subtract(autoIncrementValue);
            if (result.compareTo(maximumBidOfSecondBidder) == 0 || result.compareTo(maximumBidOfSecondBidder) < 0) {
                break;
            }
        }
        return result.add(autoIncrementValue);
    }
}
