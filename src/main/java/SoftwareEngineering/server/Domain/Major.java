package SoftwareEngineering.server.Domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Entity
public class Major {
    @Id
    @Column(name = "major_id")
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long majorIdx;

    @Column
    private String name;
    @OneToMany(mappedBy = "major")
    private List<User> users = new ArrayList<>();

    public void addUser(User user){
        users.add(user);
    }
}
