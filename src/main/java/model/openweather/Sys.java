package model.openweather;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Sys {
    private String all;
    private String type;
    private String id;
    private String country;
    private String sunrise;
    private String sunset;
}
