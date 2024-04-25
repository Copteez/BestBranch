package Demo.controller;

import edu.gemini.app.ocs.model.SciencePlan;
import Demo.repository.SciplanRepository;
import edu.gemini.app.ocs.OCS;
import edu.gemini.app.ocs.model.DataProcRequirement;
import edu.gemini.app.ocs.model.StarSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Date;

@Controller
public class SciplanController {

    @Autowired
    private SciplanRepository sciplanRepository;

    @GetMapping("/CreateSciPlan")
    public String CreateSciPlan() {
        return "CreateSciPlan";
    }

    @PostMapping("/CreateSciPlan")
    public String handelCreateSciPlan(@RequestParam("Creator") String Creator,
                                      @RequestParam("PlanID") int PlanID,
                                      @RequestParam("PlanName") String PlanName,
                                      @RequestParam("objectives") String objectives,
                                      @RequestParam("fileType") String fileType,
                                      @RequestParam("fileQuality") String fileQuality,
                                      @RequestParam("colorType") String colorType,
                                      @RequestParam("contrast") double contrast,
                                      @RequestParam("brightness") double brightness,
                                      @RequestParam("saturation") double saturation,
                                      @RequestParam("highlights") double highlights,
                                      @RequestParam("exposure") double exposure,
                                      @RequestParam("shadows") double shadows,
                                      @RequestParam("whites") double whites,
                                      @RequestParam("blacks") double blacks,
                                      @RequestParam("luminance") double luminance,
                                      @RequestParam("hue") double hue
    ) {
        DataProcRequirement newDataProcRequirements = new DataProcRequirement(fileType, fileQuality, colorType, contrast, brightness, saturation, highlights, exposure, shadows, whites, blacks, luminance, hue);
        SciencePlan newSciPlan = new SciencePlan(Creator, "submitter_placeholder", 800813.69420, objectives, StarSystem.CONSTELLATIONS.Andromeda, new Date(), new Date(), edu.gemini.app.ocs.model.SciencePlan.TELESCOPELOC.HAWAII, newDataProcRequirements);
        OCS o = new OCS();
        o.createSciencePlan(newSciPlan);

        return "sciplanCreated";
    }

    @GetMapping("/testing")
    public String testSciPlan(@RequestParam("PlanID") int PlanID) {
        OCS o = new OCS();
        SciencePlan testPlan = o.getSciencePlanByNo(PlanID);
        o.testSciencePlan(testPlan);
        return "redirect:/dashboard";
    }


}