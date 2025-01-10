// 1)나머지는 현재 자리의 값을 구합니다.
// 예를 들어, 숫자를 n으로 나눈 나머지는 가장 낮은 자릿수(일의 자리) 값을 나타냅니다.
// 2)몫은 남은 숫자를 구합니다.
// 나누기를 통해 몫을 구하면, 더 높은 자리의 값을 계산하기 위한 새로운 숫자가 됩니다.

public class KBaseConverter {
    public String toKBase(int n, int k) {
        StringBuilder kBase = new StringBuilder();
        while(n > 0) {
            kBase.append(n % k); // 나머지를 추가
            n = n / k;
        }
        return kBase.reverse().toString();
    }

    public static void main(String[] args) {

        KBaseConverter kBaseConverter = new KBaseConverter();
        System.out.println(kBaseConverter.toKBase(437674, 3));
    }
}