package SoftwareEngineering.server.Domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

public class User {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long userIdx;
    @NotBlank
    @Size(min = 2, max = 4)
    @Column(name = "name", nullable = false)
    private String name;
    @NotBlank
    @Column(name = "email", nullable = false)
    private String email;
    @NotBlank
    @Column(name = "password", nullable = false)
    private String password;
    @NotBlank
    @Column(name = "is_delete", nullable = false)
    private char isDelete;

    @ManyToOne
    @JoinColumn(name = "majorIdx")
    private Major major;
    @OneToMany(mappedBy = "user")

    private List<Reservation> reservations = new ArrayList<>();
    @OneToMany(mappedBy = "user")

    private List<Board> boards = new ArrayList<>();
    public void addReservation(Reservation reservation){
        reservations.add(reservation);
    }
}
