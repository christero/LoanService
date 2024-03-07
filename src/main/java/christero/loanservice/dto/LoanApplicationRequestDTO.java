package christero.loanservice.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class LoanApplicationRequestDTO {

    @Valid
    @NotEmpty(message = "Minst én låntaker må oppgis")
    private List<LanetakerDTO> lanetakere;

    @Min(message = "Lånebeløpet må være minst 1000", value = 1000)
    private double lanebelop;

    @NotBlank(message = "Formålet med lånet må oppgis")
    private String behov;

    @Data
    public static class LanetakerDTO {

        @NotBlank(message = "Fødselsnummer må oppgis")
        private String fnr;

        @NotBlank(message = "Navn må oppgis")
        private String navn;
    }
}
