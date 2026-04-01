package electronique;

import java.util.List;

public class CircuitParallele extends Circuit {
    public CircuitParallele(List<Composant> composants) {
        super(composants);
    }

    @Override
    public double calculerResistance() {
        double totalParallele = 0;

        for (Composant c : composants){
            totalParallele += 1.0/c.calculerResistance();
        }

        return 1.0/totalParallele;
    }
}
