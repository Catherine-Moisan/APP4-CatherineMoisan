package electronique;

import java.util.List;

public class CircuitSerie extends Circuit {

    public CircuitSerie(List<Composant> composants) {
        super(composants);
    }

    @Override
    public double calculerResistance() {
        double totalSerie = 0;
        for (Composant c : composants){
            totalSerie += c.calculerResistance();
        }
        return totalSerie;
    }
}
