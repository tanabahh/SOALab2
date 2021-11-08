package itmo.soalab2.controller;


import itmo.soalab2.model.VehicleDto;
import itmo.soalab2.service.VehicleService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shop")
public class ShopController {
    private final VehicleService vehicleService;

    @Autowired
    public ShopController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @CrossOrigin
    @GetMapping("/search/by-number-of-wheels/{from}/{to}")
    public List<VehicleDto> getVehicle(
        @PathVariable(name = "from") int from,
        @PathVariable(name = "to") int to
    ) {
        return vehicleService.getVehicle(from, to);
    }

    @CrossOrigin
    @PutMapping("/fix-distance/{vehicle-id}")
    public void updateVehicle(
        @PathVariable(name = "vehicle-id") long id,
        @RequestParam(name = "engine-power") long enginePower
    ) {
        vehicleService.updateEnginePower(id, enginePower);
    }

}
