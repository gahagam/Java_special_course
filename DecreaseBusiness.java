import java.util.Comparator;

// уменьш.
public class DecreaseBusiness<T extends MusicCollection> implements Comparator<T> {
    @Override
    public int compare(T o1, T o2) {
        double value1 = o1.calculateEffectiveListeningTime();
        double value2 = o2.calculateEffectiveListeningTime();
        return Double.compare(value2, value1);
    }
}
