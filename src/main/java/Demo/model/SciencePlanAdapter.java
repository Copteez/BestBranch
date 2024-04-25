package Demo.model;

import edu.gemini.app.ocs.model.DataProcRequirement;
import edu.gemini.app.ocs.model.SciencePlan;
import edu.gemini.app.ocs.model.StarSystem;
import edu.gemini.app.ocs.model.SciencePlan.TELESCOPELOC;
import edu.gemini.app.ocs.model.SciencePlan.STATUS;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class SciencePlanAdapter extends SciencePlan {
    private OurSciencePlan ourPlan;

    public SciencePlanAdapter(OurSciencePlan plan) throws ParseException {
        super();
        if (plan != null) {
            this.ourPlan = plan;
            setPlanNo(ourPlan.getPlanNo());
            setCreator(ourPlan.getCreatorUser().getName());
            setSubmitter(ourPlan.getSubmitterUser().getName());
            setFundingInUSD(ourPlan.getFundingInUSD());
            setObjectives(ourPlan.getObjectives());
            setStarSystem(StarSystem.CONSTELLATIONS.valueOf(ourPlan.getStarSystem().name()));

            Date startDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(ourPlan.getStartDate());
            setStartDate(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(startDate));

            Date endDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(ourPlan.getEndDate());
            setEndDate(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(endDate));

            setTelescopeLocation(TELESCOPELOC.valueOf(ourPlan.getTelescopeLocation().name()));
            setStatus(STATUS.valueOf(ourPlan.getStatus().name()));
        }
    }

    @Override
    public ArrayList<DataProcRequirement> getDataProcRequirements() {
        return new ArrayList<>(ourPlan.getDataProcRequirements());
    }

}
