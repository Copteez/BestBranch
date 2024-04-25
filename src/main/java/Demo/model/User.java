package Demo.model;

import Demo.repository.UserRepository;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

@Entity
public class User {
    @Id
    private String email;
    private String password;

    // name: "user_science_plans" - the name of the join table.
    // joinColumns: uses "user_email" to link rows in this table to the User entity.
    // inverseJoinColumns: uses "plan_no" to link rows in this table to the SciencePlan entity.
    @ManyToMany
    @JoinTable(
            name = "user_science_plans",
            joinColumns = @JoinColumn(name = "user_email"),
            inverseJoinColumns = @JoinColumn(name = "plan_no")
    )
    // To ensures that each SciencePlan is unique within the set, and operations like add, remove, and check for existence are efficient.
    private Set<SciencePlan> sciencePlans = new HashSet<>();

    public User() {}

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}