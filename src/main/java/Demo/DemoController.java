package Demo;

import edu.gemini.app.ocs.OCS;
import edu.gemini.app.ocs.model.SciencePlan;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class DemoController {
    @CrossOrigin
    @GetMapping("/")
    public ArrayList<SciencePlan> getAllSciencePlans() {
        OCS o = new OCS();
        System.out.println(o.getAllSciencePlans());
        return o.getAllSciencePlans();
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

