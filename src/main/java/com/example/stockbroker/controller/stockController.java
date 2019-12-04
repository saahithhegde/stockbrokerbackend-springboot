package com.example.stockbroker.controller;
import com.example.stockbroker.dao.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/")
public class stockController {

    @Autowired
    private userStockRepository userStocksRepo;

    @Autowired
    private userBankRepository bankRep;

    @RequestMapping(value="getUserStocks",method = RequestMethod.POST,produces = {"application/json"})
    public List<stocks> getStocks(@RequestBody stocks stockData) {
        List<stocks> userStockData = userStocksRepo.findStockByEmail(stockData.getEmail());
        return userStockData;

    }

    @RequestMapping(value="buyCurrentStocks",method = RequestMethod.POST)
    public String buyStocks(@RequestBody stockbuysell stockbuyselldetails) {
        try {
            stocks stockData = new stocks();
            stockData=userStocksRepo.findStockByStocktableid(stockbuyselldetails.getStocktableid());
            for (bankdetails userBankData : bankRep.findBankDetailsByAccountno(stockbuyselldetails.getAccountno()))
            {
                Integer newQuantity = stockData.getQuantity() + stockbuyselldetails.getQuantity();
                //Double stockprice = getCurrentStockPrice(stockData.getTickersymbol());
                Double stockprice = 10.0;
                if (userBankData.getBalance() >= (newQuantity * stockprice)) {
                    userBankData.setBalance(userBankData.getBalance() - (newQuantity * stockprice));
                    stockData.setQuantity(newQuantity);
                    userStocksRepo.save(stockData);
                    bankRep.save(userBankData);
                    return "Sucessefully Bought Stocks";
                } else {
                    return "Not enough Funds";
                }
            }
            return "no account exists,please try again";
        }

        catch (Exception e){
            return e.toString();
        }

    }
    @RequestMapping(value="sellCurrentStocks",method = RequestMethod.POST,produces = {"application/json"})
    public String sellStocks(@RequestBody stockbuysell stockbuyselldetails) {
        try {
            stocks stockData = new stocks();
            stockData = userStocksRepo.findStockByStocktableid(stockbuyselldetails.getStocktableid());
            Integer newQuantity = stockData.getQuantity() - stockbuyselldetails.getQuantity();
            for (bankdetails userBankData : bankRep.findBankDetailsByAccountno(stockbuyselldetails.getAccountno())) {
                //Double stockprice = getCurrentStockPrice(stockData.getTickersymbol());
                Double stockprice = 10.0;
                userBankData.setBalance(userBankData.getBalance() + (stockbuyselldetails.getQuantity() * stockprice));
                stockData.setQuantity(newQuantity);
                if (newQuantity == 0) {
                    userStocksRepo.delete(stockData);
                    return "sucessfully deleted stocks";
                }
                    userStocksRepo.save(stockData);
                    bankRep.save(userBankData);
            }
            return "Sucessefully sold Stocks";
        } catch (Exception e) {
            return e.toString();
        }
    }

    @RequestMapping(value="updateUserStocks",method = RequestMethod.POST,produces = {"application/json"})
    public String updateStocks(@RequestBody stocks stockData) {
        try {
            if (stockData.getQuantity() == 0) {
                userStocksRepo.delete(stockData);

            } else {
                userStocksRepo.save(stockData);
            }
            return "sucessful";
        }
        catch (Exception e)
        {
            return e.toString();
        }
    }

    public static Double getCurrentStockPrice(String tickerSymbol)
    {
        final String uri = "http://localhost:8081/current";


        RestTemplate restTemplate = new RestTemplate();
        Double currentPrice=restTemplate.postForObject(uri,tickerSymbol,Double.class);

        return currentPrice;
    }

    public static List<stocks> getAllStocks(String tickerSymbol)
    {
        final String uri = "http://localhost:8081/getStocks";

        RestTemplate restTemplate = new RestTemplate();
        List<stocks> allStocks=restTemplate.postForObject(uri,tickerSymbol,List.class);

        return allStocks;
    }

}
