import java.util.Arrays;
import java.util.List;

public class StreamMap_3_3_8 {
    public static void main(String[] args) {
        String[] lowercaseArray = new String[]{"public", "static", "void"};
        List<String> list = Arrays.asList(lowercaseArray);

        list.stream()
                .map(str -> str.toUpperCase())
                .toList()
                .forEach(s -> System.out.println(s));
    }
}
