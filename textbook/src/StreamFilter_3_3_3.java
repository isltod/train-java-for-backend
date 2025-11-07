import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StreamFilter_3_3_3 {
    public static void main(String[] args) {
        Integer[] integerArray = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        List<Integer> list = Arrays.asList(integerArray);
        list.stream()
                .filter(i -> i%2==0)
                .collect(Collectors.toList())
                .forEach(e -> System.out.println(e));
    }
}
