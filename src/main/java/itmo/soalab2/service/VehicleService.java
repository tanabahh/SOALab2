package itmo.soalab2.service;

import itmo.soalab2.model.VehicleDto;
import itmo.soalab2.model.VehicleType;
import itmo.soalab2.repository.VehicleRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VehicleService {
    private final VehicleRepository vehicleRepository;

    @Autowired
    VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    public List<VehicleDto> getVehicle(int minWheelCount, int maxWheelCount) {
        List<VehicleDto> vehicles = vehicleRepository.getVehicle();
        return vehicles.stream().filter(
            vehicleDto ->  (minWheelCount <= getWheelCount(vehicleDto.getType())) &&
                (getWheelCount(vehicleDto.getType()) <= maxWheelCount)
        ).collect(Collectors.toList());
    }

    public void updateEnginePower(long id, long enginePower) {
        vehicleRepository.updateVehicle(id, enginePower);
    }

    private int getWheelCount(VehicleType type) {
        if (type.equals(VehicleType.CAR)) {
            return 4;
        }
        if (type.equals(VehicleType.BOAT)) {
            return 0;
        }
        return 1;
    }
}
