package SoftwareEngineering.server.Domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
public class Field {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long fieldIdx;

    @Column(nullable = false)
    private String name;

    @Column
    private String notification;

    @Column
    private String sort;

    @OneToMany(mappedBy = "field")
    private List<Board> boards = new ArrayList<>();
    @OneToMany(mappedBy = "field")
    private List<Reservation> reservations = new ArrayList<>();

    public void addBoard(Board board){
        boards.add(board);
    }
    public void addReservation(Reservation reservation){
        reservations.add((reservation));
    }
}
