package com.example.stockbroker.controller;

import com.example.stockbroker.dao.bankdetails;
import com.example.stockbroker.dao.stocks;
import com.example.stockbroker.dao.userBankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class bankController {
    @Autowired
    private userBankRepository bankRepo;

    @RequestMapping(value = "addBankDetails", method = RequestMethod.POST, produces = {"application/json"})
    public String addBank(@RequestBody bankdetails bankData) {
        try {
            bankRepo.save(bankData);
            return "Bank Details Saved Sucessfully";
        }
        catch (Exception e)
        {
            return "Bank Details Already Exist";
        }
    }
    @RequestMapping(value="getBankDetails",method = RequestMethod.POST, produces = {"application/json"})
    public List<bankdetails> getbank(@RequestBody bankdetails bankData){
        List<bankdetails> userBankData = bankRepo.findBankDetailsByEmail(bankData.getEmail());
        return userBankData;
    }
    @RequestMapping(value="deleteBankDetails",method = RequestMethod.POST, produces = {"application/json"})
    public String deletebank(@RequestBody bankdetails bankData){
        try{
            bankRepo.delete(bankData);
            return "Deleted Sucessfully";
        }
        catch (Exception e){
            return e.toString();
        }
    }

    public String UpdateBalance(@RequestBody bankdetails bankData){
        try{
            bankRepo.save(bankData);
            return "Deleted Sucessfully";
        }
        catch (Exception e){
            return e.toString();
        }
    }

}
