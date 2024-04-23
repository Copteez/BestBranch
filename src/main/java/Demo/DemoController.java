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
        model.addAttribute("plans", o.getAllSciencePlans());
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

