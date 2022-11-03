package SoftwareEngineering.server.Controller;

import SoftwareEngineering.server.Common.BaseResponse;
import SoftwareEngineering.server.Domain.Major;
import SoftwareEngineering.server.Service.MajorService;
import io.swagger.v3.oas.annotations.Operation;
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
    @GetMapping("/list")
    public BaseResponse<List<Major>> getMajorList(){
        List< Major> majorList = majorService.getMajorList();
        return BaseResponse.<List<Major>>builder().message("성공").code(200).data(majorList).build();
    }

}
