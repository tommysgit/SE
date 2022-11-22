package SoftwareEngineering.server.Controller;

import SoftwareEngineering.server.Common.BaseResponse;
import SoftwareEngineering.server.Domain.Reservation;
import SoftwareEngineering.server.Dto.ReservationDto;
import SoftwareEngineering.server.Service.ReservationService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@RestController
@RequestMapping("/reservation")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @PreAuthorize("hasRole('ROLE_MEMBER')")
    @Operation(description = "예약신청")
    @PostMapping()
    public BaseResponse<Reservation> ReservationSet(@RequestBody ReservationDto.ReservationSetReqDto reservationSetReqDto, @AuthenticationPrincipal User userInfo){
        Reservation savedReservation = reservationService.setReservation(reservationSetReqDto, userInfo);
        return BaseResponse.<Reservation>builder().message("예약신청완료").data(savedReservation).code(200).build();
    }

    @PreAuthorize("hasRole('ROLE_MEMBER')")
    @Operation(description = "예약조회")
    @GetMapping()
    public BaseResponse<ReservationDto.ReservationGetResDto> ReservationGet( @RequestParam("localDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate localDate, @RequestParam Long fieldIdx){

        return BaseResponse.<ReservationDto.ReservationGetResDto>builder().message("조회 성공").data(reservationService.getReservation(localDate, fieldIdx)).code(200).build();
    }
}
