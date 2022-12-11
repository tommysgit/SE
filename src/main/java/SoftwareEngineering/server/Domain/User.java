package SoftwareEngineering.server.Domain;

import SoftwareEngineering.server.Common.ErrorCode;
import SoftwareEngineering.server.Common.CustomException.InvalidRequestException;
import SoftwareEngineering.server.Domain.enums.Role;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
// AllArgsConstructor는 빌더패턴에 필수로 필요하고, 한번 생성한 객체를 불변으로 하기위해 private
// @Entity는 기본적으로 생성자를 만들어주지만 접근제어를 위해 @NoArgsConstructor(access = AccessLevel.PROTECTED) 설정, Lazy Loading에서 Entity 프록시가 접근을 못한다.
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Builder
@Getter
public class User extends BaseEntity{
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long userIdx;
    @NotBlank
    @Column(nullable = false)
    private int studentId;
    @NotBlank
    @Size(min = 2, max = 4)
    @Column(nullable = false)
    private String name;
    @NotBlank
    @Column(nullable = false)
    private String email;
    @NotBlank
    @Column(nullable = false)
    private String password;
    @Column(name = "is_delete", nullable = false)
    private char isDelete;
    @NotBlank
    @Column(nullable = false)
    private String phoneNum;

    @Enumerated(EnumType.STRING)
    private Role role;

    @ManyToOne
    @JoinColumn(name = "major_id")
    private Major major;
    @OneToMany(mappedBy = "user")
    private List<Reservation> reservations = new ArrayList<>();



    @OneToMany(mappedBy = "user")
    private List<Team> teams = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Team_User> team_users = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Mercenary> mercenaries = new ArrayList<>();


    public void addReservation(Reservation reservation){
        reservations.add(reservation);
    }

    public void checkPassword(PasswordEncoder passwordEncoder, String encodedPassword){
        if(passwordEncoder.matches(encodedPassword, this.password)){
            throw new InvalidRequestException(ErrorCode.PASSWORD_NOT_EQUAL);
        }
    }
    public String getRole(){
        return this.role.getDescription();
    }
}
