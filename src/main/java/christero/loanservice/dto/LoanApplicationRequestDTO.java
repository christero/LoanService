package christero.loanservice.dto;

import lombok.Data;

import java.util.List;

@Data
public class LoanApplicationRequestDTO {

    private List<LanetakerDTO> lanetakere;
    private double lanebelop;
    private String behov;

    @Data
    public static class LanetakerDTO {
        private String fnr;
        private String navn;
    }
}
