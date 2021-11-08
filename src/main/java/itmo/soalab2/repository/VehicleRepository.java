package itmo.soalab2.repository;

import itmo.soalab2.model.VehicleDto;
import java.util.List;
import java.util.Objects;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class VehicleRepository {

    private final RestTemplate restTemplate = new RestTemplate();
    private String BASE_URL = "https://localhost:8444/SOALab1-1.0-SNAPSHOT/api/vehicle";

    public List<VehicleDto> getVehicle() {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(BASE_URL);
        ResponseEntity<List<VehicleDto>> responseEntity = null;
        try {
            responseEntity = restTemplate.exchange(builder.toUriString(),
                HttpMethod.GET, null,
                new ParameterizedTypeReference<List<VehicleDto>>(){});
        } catch (Exception e) {
            System.out.println("something goes wrong");
        }
        return Objects.requireNonNull(responseEntity.getBody());
    }

    public void updateVehicle(Long id, long enginePower) {
        UriComponentsBuilder builder =
            UriComponentsBuilder.fromHttpUrl(BASE_URL + "/" + id.toString())
            .queryParam("engine-power", enginePower);
        try {
            restTemplate.exchange(builder.toUriString(),
                HttpMethod.PUT, null,
                new ParameterizedTypeReference<VehicleDto>(){});
        } catch (Exception e) {
            System.out.println("something goes wrong");
        }
    }
}
