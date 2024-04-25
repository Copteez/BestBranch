package Demo.model;

import edu.gemini.app.ocs.model.DataProcRequirement;
import edu.gemini.app.ocs.model.SciencePlan;
import edu.gemini.app.ocs.model.StarSystem;
import jakarta.persistence.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class OurSciencePlan extends SciencePlan {

    @Id
    private int planNo;

    @ManyToMany(mappedBy = "sciencePlans")
    private Set<User> users = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "creator_id", referencedColumnName = "email", nullable = false)
    private User creatorUser;

    @ManyToOne
    @JoinColumn(name = "submitter_id", referencedColumnName = "email", nullable = false)
    private User submitterUser;

    public OurSciencePlan() {
        super();
    }

    public OurSciencePlan(User creator, User submitter, double fundingInUSD, String objectives,
                          StarSystem.CONSTELLATIONS starSystem, Date startDate, Date endDate,
                          TELESCOPELOC telescopeLocation, DataProcRequirement dataProcRequirements) {
        super(creator.getName(), submitter.getName(), fundingInUSD, objectives, starSystem, startDate, endDate, telescopeLocation, dataProcRequirements);
        this.creatorUser = creator;
        this.submitterUser = submitter;
    }

    public int getPlanNo() {
        return planNo;
    }

    public void setPlanNo(int planNo) {
        this.planNo = planNo;
    }

    public User getCreatorUser() {
        return creatorUser;
    }

    public void setCreatorUser(User creatorUser) {
        this.creatorUser = creatorUser;
        super.setCreator(creatorUser.getName());
    }

    public User getSubmitterUser() {
        return submitterUser;
    }

    public void setSubmitterUser(User submitterUser) {
        this.submitterUser = submitterUser;
        super.setSubmitter(submitterUser.getName());
    }
}
