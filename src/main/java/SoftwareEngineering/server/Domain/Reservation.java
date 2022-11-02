package SoftwareEngineering.server.Domain;

import javax.persistence.*;
import java.sql.Date;

public class Reservation extends BaseEntity{
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long reservationIdx;

    @Column
    private char status;

    @Column(name = "is_approval", nullable = false)
    private char isApproval;

    @Column(name = "start_time", nullable = false)
    private Date startTime;

    @Column(name = "end_time", nullable = false)
    private Date endTime;

    @ManyToOne
    @JoinColumn(name ="userIdx")
    private User user;
    @ManyToOne
    @JoinColumn(name = "fieldIdx")
    private Field field;


}
