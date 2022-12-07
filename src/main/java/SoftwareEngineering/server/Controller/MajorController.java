package SoftwareEngineering.server.Controller;

import SoftwareEngineering.server.Common.ApiCallResponse;
import SoftwareEngineering.server.Common.BaseResponse;
import SoftwareEngineering.server.Domain.Major;
import SoftwareEngineering.server.Dto.MajorDto;
import SoftwareEngineering.server.Service.MajorService;
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
@RequestMapping("/major")
public class MajorController {
    private final MajorService majorService;
    @Operation(summary = "학과 목록 조회")
    @GetMapping
    //@ApiResponse(responseCode = "200", description = "로그인 성공", content = @Content(schema = @Schema(implementation = ApiCallResponse.ApiCallResponseMajorList.class)))
    public BaseResponse<List<MajorDto.MajorListResDto>> getMajorList(){
        List<MajorDto.MajorListResDto> majorList = majorService.getMajorList();
        return BaseResponse.<List<MajorDto.MajorListResDto>>builder().message("성공").code(200).data(majorList).build();
    }

}
