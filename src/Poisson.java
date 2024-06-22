import java.util.Random;

public class Poisson {
    private Random random;
    private double lambda;
    private double L;

    public Poisson(double _lambda, int seed) {
        lambda = _lambda;
        random = new Random(seed);
        L = 1 / lambda;
    }

    // ni idea de esto, función de Knuth adaptada
    public double poissonRandomNumber() {
        double L = Math.exp(-lambda);
        int k = 0;
        double p = 1;
        do {
            k = k + 1;
            double u = random.nextDouble();
            p = p * u;
        } while (p > L);
        return k - 1;
    }

    // creo que lambda si puede ser igual a 1/lambda en este caso
    // porque la función de distribución exponencial es la misma si no
    public double poissonRandomInterarrivalDelay(double L) {
        return (Math.log(1.0 - random.nextDouble()) / -L);
    }

    public double poissonRandomInterarrivalDelay() {
        return (Math.log(1.0 - random.nextDouble()) / -lambda);
    }
}
