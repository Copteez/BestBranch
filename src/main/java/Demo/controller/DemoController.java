package Demo.controller;

import java.util.List;
import Demo.model.SciencePlan;
import Demo.repository.SciencePlanRepository;
import Demo.service.SciencePlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import edu.gemini.app.ocs.OCS;

import java.util.ArrayList;

@Controller
public class DemoController {

//    private final SciencePlanService sciencePlanService;
//
//    @Autowired
//    public DemoController(SciencePlanService sciencePlanService) {
//        this.sciencePlanService = sciencePlanService;
//    }

    @Autowired
    private SciencePlanRepository SciencePlanRepository;

    @CrossOrigin
    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        OCS o = new OCS();
        List<SciencePlan> allSciencePlans = o.getAllSciencePlans();
        // get plan list
        model.addAttribute("plans", allSciencePlans);

        // get #all sciplan
        model.addAttribute("totalPlans", allSciencePlans.size());

        // get #draft sciplan & #submitted sciplan using Stream API
        long draftPlanCount = allSciencePlans.stream()
                .filter(sp -> sp.getStatus() == SciencePlan.STATUS.SAVED)
                .count();
        long submittedPlanCount = allSciencePlans.stream()
                .filter(sp -> sp.getStatus() == SciencePlan.STATUS.SUBMITTED)
                .count();

        model.addAttribute("draftPlan", draftPlanCount);
        model.addAttribute("submittedPlan", submittedPlanCount);

        return "Dashboard";
    }

    @CrossOrigin
    @GetMapping("/sciPlan/{id}")
    public @ResponseBody SciencePlan getSciencePlanByNo(@PathVariable("id") String id){
        OCS o = new OCS();
        if (id != null) {
            for (SciencePlan sp: o.getAllSciencePlans()){
                if (sp.getPlanNo() == Integer.parseInt(id)){
                    return sp;
                }
            }
        }
        return null;
    }

    @CrossOrigin
    @GetMapping("/submission")
    public String submission(Model model) {
        OCS o = new OCS();
        List<SciencePlan> draftSciencePlans = o.getSciencePlansByStatus(SciencePlan.STATUS.SAVED);

        model.addAttribute("plans", draftSciencePlans);
        if (draftSciencePlans.isEmpty()) {
            model.addAttribute("emptyMessage", "No draft plans available.");
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