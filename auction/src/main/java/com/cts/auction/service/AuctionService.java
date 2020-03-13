package com.cts.auction.service;

import com.cts.auction.dto.BidInformation;
import com.cts.auction.dto.BidWinnerDetails;

import java.util.List;
import java.util.Set;

public interface AuctionService {
    BidWinnerDetails determineWinningBid(final Set<BidInformation> bidInformations, final String auctionItem);
}
