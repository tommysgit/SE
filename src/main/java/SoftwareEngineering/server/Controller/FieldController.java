package SoftwareEngineering.server.Controller;

import SoftwareEngineering.server.Common.ApiCallResponse;
import SoftwareEngineering.server.Common.BaseResponse;
import SoftwareEngineering.server.Domain.Field;
import SoftwareEngineering.server.Dto.FieldDto;
import SoftwareEngineering.server.Service.FieldService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/field")
public class FieldController {
    private final FieldService fieldService;

    @Operation(description = "구장 목록 조회", summary = "구장 목록 조회")
    @GetMapping
    //@ApiResponse(responseCode = "200", description = "로그인 성공", content = @Content(schema = @Schema(implementation = ApiCallResponse.ApiCallResponseFieldList.class)))
    public BaseResponse<List<FieldDto.FiledListResDto>> getFieldList(){
        List<FieldDto.FiledListResDto> fieldList = fieldService.getFieldList();
        return BaseResponse.<List<FieldDto.FiledListResDto>>builder().message("성공").data(fieldList).code(200).build();
    }
}
