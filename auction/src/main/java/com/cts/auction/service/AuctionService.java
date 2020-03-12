package com.cts.auction.service;

import com.cts.auction.dto.BidInformation;
import com.cts.auction.dto.BidWinnerDetails;

import java.util.List;

public interface AuctionService {
    BidWinnerDetails determineWinningBid(final List<BidInformation> bidInformations);
}
