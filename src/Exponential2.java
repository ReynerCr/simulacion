import java.util.Random;

// TODO version 2 de la clase Exponential, posiblemente no se utiliza
public class Exponential2{
    double lambda;
    Random uniform;
    
    public Exponential2(double _lambda, int seed) {
        lambda = _lambda;
        uniform = new Random(seed);
    }
    
    public double getNext2() {
        return Math.log(1-uniform.nextDouble())/(-lambda);
    }
}