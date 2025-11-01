package chijouProjects.gestion_stock_api.handlers;

import chijouProjects.gestion_stock_api.exception.ErrorCodes;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorDto {

    private Integer httpCode;

    private String message;

    private ErrorCodes code;

    private List<String> errors = new ArrayList<>();
}
