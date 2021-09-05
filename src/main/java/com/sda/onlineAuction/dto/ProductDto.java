package com.sda.onlineAuction.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProductDto {

    private String id;
    private String name;
    private String description;
    private String startBiddingPrice;
    private String category;
    private String endDateTime;
    private String image;
    private String currentBidPrice;
    private String loggedUserMaxBid;


}
