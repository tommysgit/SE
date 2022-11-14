package SoftwareEngineering.server.Controller;

import SoftwareEngineering.server.Common.BaseResponse;
import SoftwareEngineering.server.Domain.Field;
import SoftwareEngineering.server.Service.FieldService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/filed")
public class FieldController {
    private final FieldService fieldService;

    @Operation(description = "구장 목록 조회")
    @GetMapping
    public BaseResponse<List<Field>> getFieldList(){
        List<Field> fieldList = fieldService.getFieldList();
        return BaseResponse.<List<Field>>builder().message("성공").data(fieldList).code(200).build();
    }
}
