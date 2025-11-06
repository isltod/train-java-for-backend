import java.util.Optional;

public class Optional_3_3_13 {
    private static Optional<String> getSomeString() {
        return Optional.ofNullable("public static void");
    }

    public static void main(String[] args) {
        Optional<String> string = getSomeString();

        //이게 안티 패턴이고 지양해야 한다? isPresent를 if문처럼 사용했다?
        if (string.isPresent()) {
            System.out.println(string.get().toUpperCase());
        }

        //이게 더 낫다고? 더 정신없는데?
        string.ifPresent(s -> System.out.println(s.toUpperCase()));
    }
}
