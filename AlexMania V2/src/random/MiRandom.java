package random;

public class MiRandom {
    private long seed;
    private static final long multiplier = 0x5DEECE66DL;
    private static final long addend = 0xBL;
    private static final long mask = (1L << 48) - 1;

    public MiRandom(long seed) {
        this.seed = (seed ^ multiplier) & mask;
    }

    public double nextDouble() {
        seed = (seed * multiplier + addend) & mask;
        return (double) seed / (1L << 48);
    }

    public int nextIntInRange(int min, int max) {
        if (min >= max) {
            throw new IllegalArgumentException("El valor mínimo debe ser menor que el valor máximo");
        }

        int range = max - min + 1;
        return min + (int) (nextDouble() * range);
    }
}
