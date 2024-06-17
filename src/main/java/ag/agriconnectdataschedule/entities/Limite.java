package ag.agriconnectdataschedule.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Limite {
    private Long id;
    private Long idCapteur;
    private Long idActionneurTemp;
    private Long idActionneurHum;
    private Double limitTemp;
    private Integer limitHum;
    private Long dureeActionneurTemp;
    private Long dureeActionneurHum;
}
