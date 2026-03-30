package electronique;

public class Resistance extends Composant {

    private final double resistance;

    public Resistance(double resistance) {
        this.resistance = resistance;
    }

    @Override
    public double calculerResistance() {
        return resistance;
    }
}
