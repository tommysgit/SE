package SoftwareEngineering.server.Domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class Board extends BaseEntity{
    @Id
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
    @JoinColumn(name = "userIdx")
    private User user;
    @ManyToOne
    @JoinColumn(name = "fieldIdx")
    private Field field;
}
