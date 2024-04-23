package Demo;

import edu.gemini.app.ocs.OCS;
import edu.gemini.app.ocs.model.SciencePlan;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;

@Controller
public class DemoController {
    @CrossOrigin
    @GetMapping("/")
    public String dashboard(Model model) {
        OCS o = new OCS();
        System.out.println(o.getAllSciencePlans());
        // get plan list
        model.addAttribute("plans", o.getAllSciencePlans());

        // get #all sciplan
        model.addAttribute("totalPlans", o.getAllSciencePlans().size());

        // get #draft sciplan & #submitted sciplan
        Integer draftPlan = 0;
        Integer submittedPlan = 0;
        ArrayList<SciencePlan> AllSciencePlans = o.getAllSciencePlans();
        for (SciencePlan sp:AllSciencePlans){
            if (sp.getStatus().equals(SciencePlan.STATUS.SAVED)){
                draftPlan+=1;
            }
            if (sp.getStatus().equals(SciencePlan.STATUS.SUBMITTED)){
                submittedPlan+=1;
            }
        }
        model.addAttribute("draftPlan", draftPlan);
        model.addAttribute("submittedPlan", submittedPlan);

        return "Dashboard";
    }

    @CrossOrigin
    @GetMapping("/sciPlan/{id}")
    public @ResponseBody SciencePlan getSciencePlanByNo(@PathVariable("id") String id){
        OCS ocs = new OCS();
        if (id != null) {
            ArrayList<SciencePlan> sciencePlans = ocs.getAllSciencePlans();
            for (SciencePlan sp:sciencePlans){
                if (sp.getPlanNo() == Integer.parseInt(id)){
                    return sp;
                }
            }
        }
        return null;
    }


}

