package com.auction.bid.controller;

import com.auction.bid.model.Bid;
import com.auction.bid.service.BidService;
import com.auction.bid.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/bidder")
public class BidController {

    @Autowired
    private BidService bidService;

    @Autowired
    private ItemService itemService;

    @GetMapping("/bidder-items")  // Change made here
    public String showItemsForBidding(Model model) {
        model.addAttribute("items", itemService.getAllItems());
        return "bidder-items";
    }

    @PostMapping("/bid")
    public String placeBid(@ModelAttribute Bid bid) {
        bidService.saveBid(bid);
        return "redirect:/bidder/bidHistory";
    }

    @GetMapping("/bidHistory")
    public String viewBidHistory(Model model) {
        model.addAttribute("bids", bidService.getAllBids());
        return "bid-history";
    }
}
