package SoftwareEngineering.server.Domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

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

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "field_id", nullable = false)
    private Field field;
}
