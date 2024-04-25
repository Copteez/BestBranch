package Demo.model;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class SciencePlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int planNo;
    private String name;

    @Enumerated(EnumType.STRING)
    private STATUS status;

    @ManyToMany(mappedBy = "sciencePlans")
    private Set<User> users = new HashSet<>();

    public enum STATUS {
        DRAFT, SAVED, SUBMITTED, APPROVED, REJECTED
    }

    public SciencePlan() {}

    public SciencePlan(int planNo, String name) {
        this.planNo = planNo;
        this.name = name;
        this.status = STATUS.DRAFT;
    }

    // Getters and setters
    public int getPlanNo() {
        return planNo;
    }

    public void setPlanNo(int planNo) {
        this.planNo = planNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public STATUS getStatus() {
        return status;
    }

    public void setStatus(STATUS status) {
        this.status = status;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public void addUser(User user) {
        this.users.add(user);
        user.getSciencePlans().add(this);
    }

    /**
     * Simulates the testing of a science plan.
     * @return boolean indicating if the plan passed testing.
     */
//    public boolean testPlan() {
//        // Placeholder logic for plan testing
//        System.out.println("Testing Science Plan No: " + planNo);
//        // Testing could involve various checks based on the plan's details
//        // Here we simply assume it always passes for demonstration purposes
//        return true;
//    }

    /**
     * Submits the science plan, changing its status to SUBMITTED.
     */
//    public void submitPlan() {
//        if (this.status == STATUS.SAVED || this.status == STATUS.DRAFT) {
//            this.status = STATUS.SUBMITTED;
//            System.out.println("Submitted Science Plan No: " + planNo + " for approval.");
//            // In a real scenario, you might want to notify some service or log the submission
//        } else {
//            System.out.println("Science Plan No: " + planNo + " cannot be submitted in its current state: " + status);
//        }
//    }
}
