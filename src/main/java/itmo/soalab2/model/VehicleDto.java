package itmo.soalab2.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.joda.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.joda.ser.LocalDateSerializer;
import java.io.Serializable;
import java.time.LocalDate;

public class VehicleDto implements Serializable {
    private int id;
    private String name;
    private int x;
    private int y;
//    @JsonDeserialize(using= LocalDateDeserializer.class)
//    @JsonSerialize(using = LocalDateSerializer.class)
    private MyDate creationDate;
    private long enginePower;
    private VehicleType type;
    private FuelType fuelType;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public MyDate getCreationDate() {
        return creationDate;
    }

    public long getEnginePower() {
        return enginePower;
    }

    public VehicleType getType() {
        return type;
    }

    public FuelType getFuelType() {
        return fuelType;
    }

    public static class MyDate{
        @JsonProperty("year")
        public String year;
        @JsonProperty("month")
        public String month;
        @JsonProperty("day")
        public String day;
    }
}
