package SoftwareEngineering.server.Domain;

import lombok.*;

import javax.persistence.*;
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Getter
@Builder
public class Mercenary extends BaseEntity{
    @Id
    @Column(name = "mercenary_id")
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long mercenaryIdx;

    @Column(name = "is_approval")
    private char isApproval;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private  User user;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;
}
