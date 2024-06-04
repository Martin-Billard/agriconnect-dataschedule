package ag.agriconnectdataschedule.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Capteur {
    private Long id;

    private float longitude;

    private float latitude;

    private double temperature;

    private int humidite;

    private int intervalle;

}
