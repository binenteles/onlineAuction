package com.sda.onlineAuction.service;

import com.sda.onlineAuction.dto.BidDto;
import com.sda.onlineAuction.mapper.BidMapper;
import com.sda.onlineAuction.model.Bid;
import com.sda.onlineAuction.repository.BidRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BidService {

    @Autowired
    private BidRepository bidRepository;

    @Autowired
    private BidMapper bidMapper;

    public void placeBid(BidDto bidDto, String productId, String userEmail) {
//        System.out.println("Adaug valoarea " + bidDto.getValue() + " pentru produsul cu id-ul " + productId
//                + " si utilizatorul cu emailul " + userEmail);
        Bid bid = bidMapper.map(bidDto, productId, userEmail);
        bidRepository.save(bid);
    }
}
