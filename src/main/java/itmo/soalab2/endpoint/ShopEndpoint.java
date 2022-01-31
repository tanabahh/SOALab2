package itmo.soalab2.endpoint;

import generated.*;
import itmo.soalab2.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class ShopEndpoint {

    @Autowired
    private VehicleService vehicleService;

    private final String NAMESPACE = "/generated";

    @PayloadRoot(namespace = NAMESPACE, localPart = "getVehicleRequest")
    @ResponsePayload
    public GetVehicleResponse getVehicle(@RequestPayload GetVehicleRequest request) {
        generated.GetVehicleResponse response = new GetVehicleResponse();
        vehicleService.getVehicle(request.getFrom(), request.getTo()).forEach(
                it -> {
                    VehicleDto dto = new VehicleDto();
                    MyDate date = new MyDate();
                    date.setDay(it.getCreationDate().day);
                    date.setMonth(it.getCreationDate().month);
                    date.setYear(it.getCreationDate().year);
                    dto.setCreationDate(date);
                    dto.setEnginePower(it.getEnginePower());
                    dto.setId(it.getId());
                    dto.setFuelType(FuelType.fromValue(it.getFuelType().name()));
                    dto.setType(VehicleType.fromValue(it.getType().name()));
                    dto.setName(it.getName());
                    dto.setX(it.getX());
                    dto.setY(it.getY());
                    response.getItems().add(dto);
                }
        );
        return response;
        //response.setItems();
        //return vehicleService.getVehicle(request.getFrom(), request.getTo());
    }

    @CrossOrigin
    @PayloadRoot(namespace = NAMESPACE, localPart = "updateVehicleRequest")
    @ResponsePayload
    public void updateVehicle(@RequestPayload UpdateVehicleRequest request) {
        vehicleService.updateEnginePower(request.getId(), request.getEnginePower());
    }
}
