package org.example.Service;

import org.example.Model.SellerModel;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SellerService {

    Set<SellerModel> sellerSet;

    public SellerService(){sellerSet = new HashSet<>();}

    public Set<SellerModel> getSellerSet() {
        return sellerSet;
    }


    public void addSellerSet(SellerModel sellerModel){sellerSet.add(sellerModel);}

    public void addSeller(SellerModel s){sellerSet.add(s);}

    //Method to check if a seller with given name exists in the sellerlist
    public boolean doesSellerExist(String sellerName){
        return sellerSet.stream().anyMatch(s-> s.getSellerName().equals(sellerName));

    }
}
