package SoftwareEngineering.server.Domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class Major {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private String majorIdx;

    @Column
    private String name;
    @OneToMany(mappedBy = "major")
    private List<User> users = new ArrayList<>();

    public void addUser(User user){
        users.add(user);
    }
}
