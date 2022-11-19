package SoftwareEngineering.server.Domain;

import lombok.Getter;

import javax.persistence.*;
import java.util.List;

@Getter
@Entity
public class Team extends BaseEntity{
    @Id
    @Column(name = "team_id")
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long teamIdx;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "team")
    private List<Team_User> team_users;
}
