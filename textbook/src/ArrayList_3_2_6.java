import java.util.ArrayList;
import java.util.List;

public class ArrayList_3_2_6 {
    public static void main(String[] args) {
        List list = new ArrayList<String>();
        list.add("public");
        list.add("static");
        list.add("void");

        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }

        list.remove(1);
        int voidIndex = list.indexOf("void");
        System.out.println("void의 인덱스: " + voidIndex);
    }
}
