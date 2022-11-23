package SoftwareEngineering.server.Domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity

public class Board extends BaseEntity{
    @Id
    @Column(name = "board_id")
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long boardIdx;
    @Column
    private String title;
    @Column
    private String content;
    @NotBlank
    @Column(name = "is_delete", nullable = false)
    private char isDelete;

    @NotBlank
    @Column
    private int limit;

    @ManyToOne
    @JoinColumn(name = "reservation_id", nullable = false)
    private Reservation reservation;

    @OneToMany(mappedBy = "board")
    private List<Mercenary> mercenaries = new ArrayList<>();
}
