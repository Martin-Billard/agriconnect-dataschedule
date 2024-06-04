package ag.agriconnectdataschedule.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Releve {
    private long id;
    private LocalDate dateReleve;
    private int humitide;
    private double temperature;
    private Long idCapteur;
}
