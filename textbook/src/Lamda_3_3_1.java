import java.sql.ClientInfoStatus;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Lamda_3_3_1 {
    public static void main(String[] args) {
        List list = new ArrayList<>();
        list.add("public");
        list.add("static");
        list.add("void");

        list.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });

        list.sort((Comparator<String>) (str1, str2) -> str1.compareTo(str2));
        System.out.println(list);
    }
}
