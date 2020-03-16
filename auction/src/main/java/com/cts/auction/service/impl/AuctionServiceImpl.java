package com.cts.auction.service.impl;

import com.cts.auction.dto.BidInformation;
import com.cts.auction.dto.BidWinnerDetails;
import com.cts.auction.exception.BusinessProcessException;
import com.cts.auction.exception.InvalidInputException;
import com.cts.auction.service.AuctionService;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.cts.auction.mapper.BidWinnerMapper.mapBidWinnerDetails;

@Service
public class AuctionServiceImpl implements AuctionService {
    private static Logger LOGGER = LoggerFactory.getLogger(AuctionServiceImpl.class);

    private static final int BID_LIMIT = 2;

    @Override
    public BidWinnerDetails determineWinningBid(final Set<BidInformation> bidInformations, final String auctionItem) {
        BidWinnerDetails bidWinnerDetails;
        try {
            if (CollectionUtils.isNotEmpty(bidInformations)) {
                LOGGER.info("Processing {} bid informations.", bidInformations.size());
                if (bidInformations.size() >= BID_LIMIT) {
                    List<BidInformation> bidInformation = bidInformations.stream()
                            .map(bid -> bid.getBuilder()
                                    .maximumBid(getMaximumPossibleBid(bid.getStartingBid(), bid.getMaximumBid(), bid.getAutoIncrementAmount()))
                                    .build())
                            .sorted(Comparator.comparing(BidInformation::getMaximumBid).reversed())
                            .limit(BID_LIMIT)
                            .collect(Collectors.toList());
                    Collections.sort(bidInformation, new BidComparator());
                    bidWinnerDetails = mapBidWinnerDetails(bidInformation.get(0), bidInformation.get(1), auctionItem);
                } else {
                    bidWinnerDetails = mapBidWinnerDetails(bidInformations.iterator().next(), null, auctionItem);
                }
                LOGGER.info("Winner is {} with maximum bid amount {}", bidWinnerDetails.getBidder().getBidderName(), bidWinnerDetails.getWinningBid());
                return bidWinnerDetails;
            } else {
                LOGGER.error("No Bidding information found.");
                throw new InvalidInputException("No bidding information found.");
            }
        } catch (Exception e) {
            LOGGER.error("Error processing the auction information {}", bidInformations, e);
            if(e instanceof InvalidInputException) {
                throw e;
            }
            throw new BusinessProcessException("Could not process the bid information provided", e);
        }
    }

    private BigDecimal getMaximumPossibleBid(final BigDecimal startingBid, final BigDecimal maximumBid, final BigDecimal autoIncrementAmount) {
        BigDecimal result = startingBid;
        for (BigDecimal index = startingBid; index.compareTo(maximumBid) < 0 || index.compareTo(maximumBid) == 0; index.add(autoIncrementAmount)) {
            result = result.add(autoIncrementAmount);
            if (result.compareTo(maximumBid) == 0 || result.compareTo(maximumBid) > 0) {
                break;
            }
        }
        return result.compareTo(maximumBid) > 0 ? maximumBid : result;
    }

    private class BidComparator implements Comparator<BidInformation> {

        @Override
        public int compare(BidInformation o1, BidInformation o2) {
            int compareValue = -o1.getMaximumBid().compareTo(o2.getMaximumBid()); //reverse sort
            if (compareValue == 0) {
                return o1.getBidderEntryNumber().compareTo(o2.getBidderEntryNumber());
            }
            return compareValue;
        }
    }
}
