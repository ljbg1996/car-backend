package com.motracoca.services;

import com.motracoca.entities.ServiceEntity;
import com.motracoca.entities.VehicleEntity;
import com.motracoca.model.Customer;
import com.motracoca.model.Vehicle;
import com.motracoca.model.Vin;
import com.motracoca.store.CustomerStore;
import com.motracoca.store.VehicleStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActiveServiceService {

//    private final CustomerStore customerStore;

    public List<com.motracoca.model.Service> getActiveServices(Vin vin, long customerId) {
//        Customer customer = customerStore.getCustomerById(customerId);
        return null;
    };
    public List<com.motracoca.model.Service> extractServices(Vehicle vehicle, long customerID){
//

        return null;
    }
}

//    private final UserStore userStore;
//
//    public User findUserById(final String userId) {
//        return userStore.findUser(userId);
//    }
//}