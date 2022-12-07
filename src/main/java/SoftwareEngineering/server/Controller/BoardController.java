package SoftwareEngineering.server.Controller;

import SoftwareEngineering.server.Common.BaseResponse;
import SoftwareEngineering.server.Dto.BoardDto;
import SoftwareEngineering.server.Dto.BoardListResDto;
import SoftwareEngineering.server.Service.BoardService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;

    @Operation(description = "구인글 목록 조회", summary = "구인글 목록 조회")
    @GetMapping
    public BaseResponse<List<BoardListResDto>> getBoardList(){
        return BaseResponse.<List<BoardListResDto>>builder().message("성공").code(200).data(boardService.getBoardList()).build();
    }

    @PreAuthorize("hasRole('ROLE_MEMBER')")
    @Operation(description = "구인글 등록", summary = "구인글 등록")
    @PostMapping()
    public BaseResponse serBoard(@Valid @RequestBody BoardDto.BoardSetReqDto boardSetReqDto, @AuthenticationPrincipal org.springframework.security.core.userdetails.User userInfo){

        boardService.setBoard(boardSetReqDto, userInfo);
        return BaseResponse.builder().message("성공").code(200).build();
    }

    @PreAuthorize("hasRole('ROLE_MEMBER')")
    @Operation(description = "구인 참여", summary = "구인 참여")
    @PostMapping("signUp/{boardIdx}")
    public BaseResponse setMercenary(@PathVariable Long boardIdx, @AuthenticationPrincipal org.springframework.security.core.userdetails.User userInfo){
        boardService.setMercenary(boardIdx, userInfo);
        return BaseResponse.builder().message("성공").code(200).build();
    }

}
