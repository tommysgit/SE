package SoftwareEngineering.server.Controller;

import SoftwareEngineering.server.Common.BaseResponse;
import SoftwareEngineering.server.Dto.BoardDto;
import SoftwareEngineering.server.Service.BoardService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;

    @Operation(description = "구인글 목록 조회")
    @GetMapping
    public BaseResponse<List<BoardDto.BoardListResDto>> getBoardList(){
        return BaseResponse.<List<BoardDto.BoardListResDto>>builder().message("성공").code(200).data(boardService.getBoardList()).build();
    }

    @Operation(description = "구인글 등록")
    @PostMapping()
    public BaseResponse serBoard(BoardDto.BoardSetReqDto boardSetReqDto){
        boardService.setBoard(boardSetReqDto);
        return BaseResponse.builder().message("성공").code(200).build();
    }
}
