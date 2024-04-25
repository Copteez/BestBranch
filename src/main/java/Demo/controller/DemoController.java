package Demo.controller;

import edu.gemini.app.ocs.OCS;
import edu.gemini.app.ocs.model.SciencePlan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
public class DemoController {
    @CrossOrigin
    @GetMapping("/dashboard")
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
        OCS o = new OCS();
        if (id != null) {
            ArrayList<SciencePlan> sciencePlans = o.getAllSciencePlans();
            for (SciencePlan sp:sciencePlans){
                if (sp.getPlanNo() == Integer.parseInt(id)){
                    return sp;
                }
            }
        }
        return null;
    }

    @CrossOrigin
    @GetMapping("/submission")
    public String submission(Model model){
        OCS o = new OCS();
        // get draft plan
        ArrayList<SciencePlan> AllSciencePlans = o.getAllSciencePlans();
        ArrayList<SciencePlan> DraftSciencePlans = new ArrayList<>();
        for (SciencePlan sp:AllSciencePlans){
            if (sp.getStatus().equals(SciencePlan.STATUS.SAVED)){
                DraftSciencePlans.add(sp);
            }
        }
        System.out.println(DraftSciencePlans);
        model.addAttribute("plans", DraftSciencePlans);
        if (DraftSciencePlans.isEmpty()) {
            model.addAttribute("emptyMessage", "No draft plans.");
        }
        return "submit";
    }

    @PostMapping("/submission")
    public String handleSubmission(@RequestParam("planId") String planId, Model model) {
        OCS o = new OCS();
        String submitResult = "";
        if (planId != null) {
            ArrayList<SciencePlan> sciencePlans = o.getAllSciencePlans();
            for (SciencePlan sp:sciencePlans){
                if (sp.getPlanNo() == Integer.parseInt(planId)){
                    submitResult = o.submitSciencePlan(sp);
                }
            }
        }
        model.addAttribute("submitResult", submitResult);
        return "submitResult";
    }



}

