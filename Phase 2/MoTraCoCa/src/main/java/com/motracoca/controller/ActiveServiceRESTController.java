/*package com.motracoca.controller;

import com.motracoca.model.Service;
import com.motracoca.model.Vin;
import com.motracoca.repositorys.CustomerRepository;
import com.motracoca.services.ActiveServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/services")
public class ActiveServiceRESTController {

        private final ActiveServiceService activeServiceService;

        @Autowired
        public ActiveServiceRESTController(ActiveServiceService activeServiceService, CustomerRepository customerRepository) {
            this.activeServiceService = activeServiceService;
        }

        @Operation(summary = "Get service list by vin and customerId")
         @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the service",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Service.class)) }),
            @ApiResponse(responseCode = "404", description = "Service not found",
                    content = @Content) })

        @GetMapping("/{vin}/{customerId}")
        public List<Service> getActiveServices(@PathVariable String vin, @PathVariable String customerId) {
            Vin vin1 = new Vin(vin);
            long customerId1 = Long.parseLong(customerId);

            // Call ActiveServiceService to retrieve active services
            List<Service> activeServices = activeServiceService.getActiveServices(vin1, customerId1);

            return activeServices;
        }
}


*/