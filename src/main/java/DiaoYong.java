import javax.xml.transform.Result;
import java.io.IOException;

public class DiaoYong  {
    public static void main(String[] args) throws IOException {
        CaseApi aCase = new Case();
        Case.Result result = aCase.calculatePayout(100, 95, 10);
        System.out.println(result);
    }
}
