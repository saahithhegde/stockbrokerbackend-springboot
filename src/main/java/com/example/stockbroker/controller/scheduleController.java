package com.example.stockbroker.controller;

import com.example.stockbroker.dao.schduleRepository;
import com.example.stockbroker.dao.userBankRepository;
import com.example.stockbroker.dao.userStockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/")
public class scheduleController {
    @Autowired
    private userBankRepository bankRepo;

    @Autowired
    private schduleRepository schRepo;

    @Autowired
    private userStockRepository userStocksRepo;



}
