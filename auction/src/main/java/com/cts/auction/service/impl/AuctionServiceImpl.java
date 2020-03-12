package com.cts.auction.service.impl;

import com.cts.auction.dto.BidInformation;
import com.cts.auction.dto.BidWinnerDetails;
import com.cts.auction.exception.BusinessProcessException;
import com.cts.auction.exception.InvalidInputException;
import com.cts.auction.service.AuctionService;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static com.cts.auction.mapper.BidWinnerMapper.mapBidWinnerDetails;

public class AuctionServiceImpl implements AuctionService {
    private static Logger LOGGER = LoggerFactory.getLogger(AuctionServiceImpl.class);

    private static final int BID_LIMIT = 2;

    @Override
    public BidWinnerDetails determineWinningBid(final List<BidInformation> bidInformations) {
        BidWinnerDetails bidWinnerDetails;
        try {
            if (CollectionUtils.isNotEmpty(bidInformations)) {
                LOGGER.info("Processing {} bid informations.", bidInformations.size());
                if (bidInformations.size() >= BID_LIMIT) {
                    List<BidInformation> bidInformation = bidInformations.stream()
                            .sorted(Comparator.comparing(BidInformation::getMaximumBid).thenComparing(BidInformation::getMaximumBid).reversed())
                            .limit(BID_LIMIT)
                            .collect(Collectors.toList());
                    bidWinnerDetails = getWinningBidderInformation(bidInformation.get(0), bidInformation.get(1));
                } else {
                    bidWinnerDetails = mapBidWinnerDetails(bidInformations.get(0), Boolean.FALSE, null);
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

    private BidWinnerDetails getWinningBidderInformation(final BidInformation bidInformation1, final BidInformation bidInformation2) {
        if (bidInformation1.getMaximumBid().compareTo(bidInformation2.getMaximumBid()) == 0) {
            return mapBidWinnerDetails(bidInformation1, Boolean.TRUE, null);
        }
        return mapBidWinnerDetails(bidInformation1, Boolean.FALSE, bidInformation2.getMaximumBid());
    }

}
