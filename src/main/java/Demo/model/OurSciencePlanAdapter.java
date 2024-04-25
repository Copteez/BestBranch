package Demo.model;

import edu.gemini.app.ocs.model.DataProcRequirement;
import edu.gemini.app.ocs.model.SciencePlan;
import edu.gemini.app.ocs.model.StarSystem;
import jakarta.persistence.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class OurSciencePlanAdapter extends OurSciencePlan {
    private SciencePlan sciplan;

    public OurSciencePlanAdapter(SciencePlan plan) throws ParseException {
        super();
        if (plan != null) {
            this.sciplan = plan;
            this.setPlanNo(plan.getPlanNo());
            this.setCreatorUser(new User(plan.getCreator(), "", plan.getCreator()));
            this.setSubmitterUser(new User(plan.getSubmitter(), "", plan.getSubmitter()));
            this.setFundingInUSD(plan.getFundingInUSD());
            this.setObjectives(plan.getObjectives());
            this.setStarSystem(StarSystem.CONSTELLATIONS.valueOf(plan.getStarSystem().name()));

            Date startDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(plan.getStartDate());
            this.setStartDate(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(startDate));

            Date endDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(plan.getEndDate());
            this.setEndDate(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(endDate));

            this.setTelescopeLocation(TELESCOPELOC.valueOf(plan.getTelescopeLocation().name()));
            this.setStatus(plan.getStatus());
        }
    }
}
