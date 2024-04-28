package Demo.model;

import edu.gemini.app.ocs.model.DataProcRequirement;
import edu.gemini.app.ocs.model.SciencePlan;
import edu.gemini.app.ocs.model.StarSystem;

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
            setCreator(ourPlan.getCreator().getName());
            if (plan.getSubmitter().equals(null)){
                setSubmitter("");
            }else{
                setSubmitter(ourPlan.getSubmitter().getName());
            }
            setFundingInUSD(ourPlan.getFundingInUSD());
            setObjectives(ourPlan.getObjectives());
            setStarSystem(StarSystem.CONSTELLATIONS.valueOf(ourPlan.getStarSystem().name()));
            setStatus(ourPlan.getStatus());
            Date startDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(ourPlan.getStartDate());
            setStartDate(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(startDate));

            Date endDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(ourPlan.getEndDate());
            setEndDate(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(endDate));

            setTelescopeLocation(TELESCOPELOC.valueOf(ourPlan.getTelescopeLocation().name()));
        }
    }

    @Override
    public ArrayList<DataProcRequirement> getDataProcRequirements() {
        return new ArrayList<>(ourPlan.getDataProcRequirements());
    }

}
