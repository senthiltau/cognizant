package com.cts.auction.service.impl;

import com.cts.auction.dto.BidInformation;
import com.cts.auction.dto.BidWinnerDetails;
import com.cts.auction.dto.Bidder;
import com.cts.auction.exception.BusinessProcessException;
import com.cts.auction.exception.InvalidInputException;
import com.cts.auction.mapper.BidWinnerMapper;
import com.cts.auction.service.AuctionService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AuctionServiceImplTest {

    private static final String BIDDER_NAME_LINDA = "LINDA";
    private static final String BIDDER_NAME_DAVE = "DAVE";
    private static final String BIDDER_NAME_ERIC = "ERIC";

    private Set<BidInformation> bidInformationList = new HashSet<>();

    private BidInformation bidderLinda;
    private BidInformation bidderDave;
    private BidInformation bidderEric;

    @Mock
    private BidWinnerMapper mapper;

    @InjectMocks
    private AuctionService auctionService = new AuctionServiceImpl();

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        bidderLinda = BidInformation.Builder.builder()
                .bidder(Bidder.Builder.builder().bidderName(BIDDER_NAME_LINDA).build())
                .bidderEntryNumber(1)
                .build();
        bidderDave = BidInformation.Builder.builder()
                .bidder(Bidder.Builder.builder().bidderName(BIDDER_NAME_DAVE).build())
                .bidderEntryNumber(2)
                .build();
        bidderEric = BidInformation.Builder.builder()
                .bidder(Bidder.Builder.builder().bidderName(BIDDER_NAME_ERIC).build())
                .bidderEntryNumber(3)
                .build();

    }

    @Test
    public void testDetermineWinningBidAuctionOne() {
        bidderLinda = new BidInformation.Builder(bidderLinda)
                .startingBid(new BigDecimal("170"))
                .maximumBid(new BigDecimal("240"))
                .autoIncrementAmount(new BigDecimal("3"))
                .build();

        bidderDave = new BidInformation.Builder(bidderDave)
                .startingBid(new BigDecimal("160"))
                .maximumBid(new BigDecimal("243"))
                .autoIncrementAmount(new BigDecimal("7"))
                .build();

        bidderEric = new BidInformation.Builder(bidderEric)
                .startingBid(new BigDecimal("190"))
                .maximumBid(new BigDecimal("240"))
                .autoIncrementAmount(new BigDecimal("4"))
                .build();

        bidInformationList.addAll(Arrays.asList(bidderLinda, bidderDave, bidderEric));
        BidWinnerDetails actual = auctionService.determineWinningBid(bidInformationList, "Record Player");
        assertEquals(new BigDecimal("243"), actual.getWinningBid());
        assertEquals(bidderDave.getBidder().getBidderName(), actual.getBidder().getBidderName());
    }

    @Test
    public void testDetermineWinningBidAuctionTwo() {
        bidderLinda = new BidInformation.Builder(bidderLinda)
                .startingBid(new BigDecimal("30"))
                .maximumBid(new BigDecimal("70"))
                .autoIncrementAmount(new BigDecimal("4"))
                .build();

        bidderDave = new BidInformation.Builder(bidderDave)
                .startingBid(new BigDecimal("30"))
                .maximumBid(new BigDecimal("70"))
                .autoIncrementAmount(new BigDecimal("3"))
                .build();

        bidderEric = new BidInformation.Builder(bidderEric)
                .startingBid(new BigDecimal("40"))
                .maximumBid(new BigDecimal("90"))
                .autoIncrementAmount(new BigDecimal("2"))
                .build();

        bidInformationList.addAll(Arrays.asList(bidderLinda, bidderDave, bidderEric));
        BidWinnerDetails actual = auctionService.determineWinningBid(bidInformationList, "Snow shoes");
        assertEquals(new BigDecimal("72"), actual.getWinningBid());
        assertEquals(bidderEric.getBidder().getBidderName(), actual.getBidder().getBidderName());
    }

    @Test
    public void testDetermineWinningBidAuctionThree() {
        bidderLinda = new BidInformation.Builder(bidderLinda)
                .startingBid(new BigDecimal("20000"))
                .maximumBid(new BigDecimal("65000"))
                .autoIncrementAmount(new BigDecimal("2000"))
                .build();

        bidderDave = new BidInformation.Builder(bidderDave)
                .startingBid(new BigDecimal("10000"))
                .maximumBid(new BigDecimal("70000"))
                .autoIncrementAmount(new BigDecimal("15000"))
                .build();

        bidderEric = new BidInformation.Builder(bidderEric)
                .startingBid(new BigDecimal("22000"))
                .maximumBid(new BigDecimal("70000"))
                .autoIncrementAmount(new BigDecimal("8000"))
                .build();

        bidInformationList.addAll(Arrays.asList(bidderLinda, bidderDave, bidderEric));
        BidWinnerDetails actual = auctionService.determineWinningBid(bidInformationList, "Piano");
        assertEquals(new BigDecimal("70000"), actual.getWinningBid());
        assertEquals(bidderDave.getBidder().getBidderName(), actual.getBidder().getBidderName());
    }

    @Test
    public void testInputValidationException() {
        Assertions.assertThrows(InvalidInputException.class, () -> auctionService.determineWinningBid(null, null));
    }

    @Test
    public void testBusinessProcessException() {
        bidInformationList.addAll(Arrays.asList(bidderLinda, bidderDave, bidderEric));
        Assertions.assertThrows(BusinessProcessException.class, () -> auctionService.determineWinningBid(bidInformationList, null));
    }
}