package api;

import java.io.IOException;

public interface CaseApi {
   Case.Result calculatePayout(int bet, int rtp, int runs) throws IOException;
}
