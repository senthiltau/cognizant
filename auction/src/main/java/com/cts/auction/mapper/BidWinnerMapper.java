package com.cts.auction.mapper;

import com.cts.auction.dto.BidInformation;
import com.cts.auction.dto.BidWinnerDetails;

import java.math.BigDecimal;

public class BidWinnerMapper {

    public static BidWinnerDetails mapBidWinnerDetails(final BidInformation bidInformation, final Boolean conflicted, BigDecimal maximumBidOfSecondBidder) {
        BigDecimal maximumBid = bidInformation.getMaximumBid();
        BigDecimal winningBidAmount = conflicted ? maximumBid : getIncrementedMaxBid(bidInformation, maximumBidOfSecondBidder).compareTo(maximumBid) > 0
                ? maximumBid : getIncrementedMaxBid(bidInformation, maximumBidOfSecondBidder);
        return BidWinnerDetails.Builder.builder()
                .bidder(bidInformation.getBidder())
                .winningBid(winningBidAmount)
                .build();
    }

    private static BigDecimal getIncrementedMaxBid(final BidInformation bidInformation, final BigDecimal maximumBidOfSecondBidder) {
        return bidInformation.getMaximumBid().subtract(maximumBidOfSecondBidder).compareTo(bidInformation.getAutoIncrementAmount()) < 0 ? bidInformation.getMaximumBid() :
                getWinningAmountByAddingAutoIncrement(bidInformation, maximumBidOfSecondBidder);
    }

    private static BigDecimal getWinningAmountByAddingAutoIncrement(final BidInformation bidInformation, final BigDecimal maximumBidOfSecondBidder) {
        Long startIndex = Long.parseLong(bidInformation.getStartingBid().toPlainString());
        Long maxBid = Long.parseLong(maximumBidOfSecondBidder.toPlainString());
        long autoIncrementValue = Long.parseLong(bidInformation.getAutoIncrementAmount().toPlainString());

        BigDecimal result = BigDecimal.ZERO;
        Long index = startIndex;
        while (index < maxBid + autoIncrementValue + 1) {
            Long addedAmount = index + autoIncrementValue;
            if (addedAmount.longValue() > maxBid.longValue()) {
                result = new BigDecimal(addedAmount);
                break;
            }
            index += autoIncrementValue;
        }
        return result;
    }
}
