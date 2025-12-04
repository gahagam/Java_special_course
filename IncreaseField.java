import java.util.Comparator;

// увелич.
public class IncreaseField<T extends MusicCollection> implements Comparator<T> {
    @Override
    public int compare(T o1, T o2) {
        return Double.compare(o1.getSpecialValue(), o2.getSpecialValue());
    }
}
