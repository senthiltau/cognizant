package com.cts.auction;

import com.cts.auction.dto.BidInformation;
import com.cts.auction.dto.Bidder;
import com.cts.auction.service.AuctionService;
import com.cts.auction.service.impl.AuctionServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@SpringBootApplication
public class AuctionApplication {

	private static final String BIDDER_NAME_LINDA = "LINDA";
	private static final String BIDDER_NAME_DAVE = "DAVE";
	private static final String BIDDER_NAME_ERIC = "ERIC";

	private List<BidInformation> bidInformationList = new LinkedList<>();

	private static BidInformation bidderLinda;
	private static BidInformation bidderDave;
	private static BidInformation bidderEric;


	public static void main(String[] args) {
		SpringApplication.run(AuctionApplication.class, args);
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
		processAuction();
	}

	private static void processAuction() {
		AuctionService auctionService = new AuctionServiceImpl();
		Set<BidInformation> biddingForRecordPlayer = new HashSet<>();

		initializeBidOne();
		biddingForRecordPlayer.addAll(Arrays.asList(bidderLinda, bidderDave, bidderEric));
		System.out.println("Auction One:" + auctionService.determineWinningBid(biddingForRecordPlayer, "Record Player").toString());

		biddingForRecordPlayer.clear();
		initializeBidTwo();
		biddingForRecordPlayer.addAll(Arrays.asList(bidderLinda, bidderDave, bidderEric));
		System.out.println("Auction Two:" + auctionService.determineWinningBid(biddingForRecordPlayer, "Snow Shoes").toString());

		biddingForRecordPlayer.clear();
		initializeBidThree();
		biddingForRecordPlayer.addAll(Arrays.asList(bidderLinda, bidderDave, bidderEric));
		System.out.println("Auction Three:" + auctionService.determineWinningBid(biddingForRecordPlayer, "Piano").toString());


	}

	private static void initializeBidTwo() {
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
	}

	private static void initializeBidOne() {
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
	}

	private static void initializeBidThree() {
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
	}

}
