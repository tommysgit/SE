package SoftwareEngineering.server.Domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
@Entity
public class Reservation extends BaseEntity{
    @Id
    @Column(name = "reservation_id")
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long reservationIdx;

    @Column
    private char status;

    @Column(name = "is_approval", nullable = false)
    private char isApproval;

    @Column(name = "start_time", nullable = false)
    private LocalDateTime startTime;

    @Column(name = "end_time")
    private LocalDateTime endTime;

    @ManyToOne
    @JoinColumn(name ="user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "field_id")
    private Field field;

    @OneToMany(mappedBy = "reservation")
    private List<Board> boards = new ArrayList<>();
}
