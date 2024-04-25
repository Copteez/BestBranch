package Demo.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class User {
    @Id
    private String email;

    private String password;
    private String name;

    @ManyToMany
    @JoinTable(
            name = "user_science_plans",
            joinColumns = @JoinColumn(name = "user_email", referencedColumnName = "email"),
            inverseJoinColumns = @JoinColumn(name = "plan_no")
    )
    private Set<OurSciencePlan> sciencePlans = new HashSet<>();

    @OneToMany(mappedBy = "creatorUser")
    private Set<OurSciencePlan> createdPlans = new HashSet<>();

    @OneToMany(mappedBy = "submitterUser")
    private Set<OurSciencePlan> submittedPlans = new HashSet<>();

    public User() {}

    public User(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
