package com.motracoca.services;

import com.motracoca.entities.VehicleEntity;
import com.motracoca.model.Vin;
import com.motracoca.repositorys.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActiveServiceService {

    CustomerRepository customerRepository;

    public ActiveServiceService() {
    }


    public List<com.motracoca.model.Service> getActiveServices(Vin vin, long customerId) {
//        CustomerStore customerStore = new CustomerStore()
//        Customer customer = customerStore.getCustomerById(customerId);
        return null;
    };
    public List<com.motracoca.model.Service> extractServices(VehicleEntity vehicle, long customerID){
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