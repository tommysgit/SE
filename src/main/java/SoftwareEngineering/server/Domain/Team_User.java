package SoftwareEngineering.server.Domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Team_User extends BaseEntity{

    @Id
    @Column(name = "team_user_id")
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long teamUserIdx;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private char status;
}
