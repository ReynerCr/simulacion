
public class RNG {
    // Generate a random number between 0 and 1 with exponential distribution
    // with mean 1/lambda
    public static double exp(double lambda) {
        return -Math.log(1 - Math.random()) / lambda;
    }
}
