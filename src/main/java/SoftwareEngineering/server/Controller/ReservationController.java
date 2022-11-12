package SoftwareEngineering.server.Controller;

import SoftwareEngineering.server.Common.BaseResponse;
import SoftwareEngineering.server.Domain.Reservation;
import SoftwareEngineering.server.Dto.ReservationDto;
import SoftwareEngineering.server.Service.ReservationService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reservation")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @Operation(description = "예약신청")
    @PostMapping()
    public BaseResponse<Reservation> ReservationSet(@RequestBody ReservationDto.ReservationSetReqDto reservationSetReqDto){
        Reservation savedReservation = reservationService.setReservation(reservationSetReqDto);
        return BaseResponse.<Reservation>builder().message("예약신청완료").data(savedReservation).code(200).build();
    }
}
