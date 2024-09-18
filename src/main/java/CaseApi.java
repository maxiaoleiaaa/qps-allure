import javax.xml.transform.Result;
import java.io.IOException;

public interface CaseApi {
   Case.Result calculatePayout(int bet, int rtp, int runs) throws IOException;
}
