import java.util.Random;

public class Exponential extends Rng {
    double lambda;
    Random uniform;
    
    public Exponential(double _lambda, int seed) {
        lambda = _lambda;
        uniform = new Random(seed);
    }

    public double getNext() {
        return -Math.log(uniform.nextDouble()) / lambda;
    }
}