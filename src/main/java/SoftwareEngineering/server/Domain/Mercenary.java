package SoftwareEngineering.server.Domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Mercenary extends BaseEntity{
    @Id
    @Column(name = "mercenary_id")
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long mercenaryIdx;

    @Column(name = "id_approval")
    private char isApproval;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private  User user;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;
}
