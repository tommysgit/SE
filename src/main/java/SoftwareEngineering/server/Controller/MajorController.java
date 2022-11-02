package SoftwareEngineering.server.Controller;

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
    public void getMajorList(){
        List< Major> majorList = majorService.getMajorList();
    }

}
