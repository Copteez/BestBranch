package Demo.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.text.ParseException;

import Demo.model.OurSciencePlan;
import Demo.model.OurSciencePlanAdapter;
import Demo.model.SciencePlanAdapter;
import Demo.model.User;
import Demo.repository.SciplanRepository;
import edu.gemini.app.ocs.model.DataProcRequirement;
import edu.gemini.app.ocs.model.SciencePlan;
import edu.gemini.app.ocs.model.StarSystem;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import edu.gemini.app.ocs.OCS;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
public class DemoController {

    @Autowired
    private SciplanRepository sciplanRepository;

    static OCS o = new OCS();

    @CrossOrigin
    @GetMapping("/dashboard")
    public String dashboard(Model model, HttpSession session) throws ParseException {
        User user = (User) session.getAttribute("loggininUser");
        if (user != null) {
            model.addAttribute("username", user.getName());
        } else {
            return "redirect:/login";
        }

        ArrayList<SciencePlan> sciencePlans = o.getAllSciencePlans();

        ArrayList<OurSciencePlan> ourSciencePlans = new ArrayList<>();
        for (SciencePlan plan : sciencePlans) {
            ourSciencePlans.add(new OurSciencePlanAdapter(plan));
        }

        model.addAttribute("plans", ourSciencePlans);

        model.addAttribute("totalPlans", ourSciencePlans.size());

        Integer draftPlan = 0;
        Integer submittedPlan = 0;
        for (OurSciencePlan sp : ourSciencePlans) {
            if (sp.getStatus().equals(SciencePlan.STATUS.SAVED)) {
                draftPlan++;
            }
            if (sp.getStatus().equals(SciencePlan.STATUS.SUBMITTED)) {
                submittedPlan++;
            }
        }
        model.addAttribute("draftPlan", draftPlan);
        model.addAttribute("submittedPlan", submittedPlan);

        return "Dashboard";
    }

    // Show SciencePlan detail
    @CrossOrigin
    @GetMapping("/sciPlan/{id}")
    public @ResponseBody String getSciencePlanByNo(@PathVariable("id") String id, Model model, HttpSession session) throws ParseException {
        User user = (User) session.getAttribute("loggininUser");
        if (user != null) {
            model.addAttribute("username", user.getName());
        } else {
            return "redirect:/login";
        }

        ArrayList<SciencePlan> sciencePlans = o.getAllSciencePlans();

        ArrayList<OurSciencePlan> ourSciencePlans = new ArrayList<>();
        for (SciencePlan plan : sciencePlans) {
            ourSciencePlans.add(new OurSciencePlanAdapter(plan));
        }

        if (id != null) {
            for (OurSciencePlan sp : ourSciencePlans) {
                if (sp.getPlanNo() == Integer.parseInt(id)) {
                    model.addAttribute("plans", (sp));
                    return "Dashboard"; // Wait for Sciplan detial html file
                }
            }
        }
        return "Dashboard";
    }

    @GetMapping("/CreateSciPlan")
    public String CreateSciPlan() {
        return "CreateSciPlan";
    }

    @PostMapping("/CreateSciPlan")
    public String handelCreateSciPlan(
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
    ) throws ParseException {
        DataProcRequirement newDataProcRequirements = new DataProcRequirement(fileType, fileQuality, colorType, contrast, brightness, saturation, highlights, exposure, shadows, whites, blacks, luminance, hue);
        Date currentDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        calendar.add(Calendar.DATE, 90);
        Date afterDate = calendar.getTime();
        System.out.println("afterDate:" + afterDate + " currentDate:" + currentDate);
        OurSciencePlan newOurSciPlan = new OurSciencePlan(UserController.getLoginUser(), UserController.getLoginUser(), 800813.69420, objectives, StarSystem.CONSTELLATIONS.Libra, currentDate, afterDate, SciencePlan.TELESCOPELOC.CHILE, newDataProcRequirements);
        o.createSciencePlan(new SciencePlanAdapter(newOurSciPlan));
        sciplanRepository.save(newOurSciPlan);

        return "redirect:/dashboard";
    }

    @GetMapping("/testing")
    public String testSciPlan(Model model, HttpSession session) throws ParseException {
        ArrayList<SciencePlan> sciencePlans = o.getAllSciencePlans();
        ArrayList<OurSciencePlan> ourSciencePlans = new ArrayList<>();
        ArrayList<SciencePlan> savedSciencePlans = new ArrayList<>();

        for (SciencePlan plan : sciencePlans) {
            ourSciencePlans.add(new OurSciencePlanAdapter(plan));
        }

        for (OurSciencePlan sp : ourSciencePlans) {
            if (sp.getStatus().equals(SciencePlan.STATUS.SAVED)) {
                savedSciencePlans.add(sp);
            }
        }
        model.addAttribute("plans", savedSciencePlans);

        return "testing";
    }

    @PostMapping("/testing")
    public String handelTestSciPlan(@RequestParam("planId") String planId, Model model, HttpSession session) throws ParseException {
        User user = (User) session.getAttribute("loggininUser");
        if (user != null) {
            model.addAttribute("username", user.getName());
        } else {
            return "redirect:/login";
        }

        OCS o = new OCS();
        SciencePlan testPlan = o.getSciencePlanByNo(Integer.parseInt(planId));
        String result = o.testSciencePlan(testPlan);
        System.out.println("\n\nresult of sciPLan ID:"+ Integer.parseInt(planId)+result);
        return "redirect:/dashboard";
    }


    @CrossOrigin
    @GetMapping("/submission")
    public String submission(Model model, HttpSession session) throws ParseException {
        User user = (User) session.getAttribute("loggininUser");
        if (user != null) {
            model.addAttribute("username", user.getName());
        } else {
            return "redirect:/login";
        }
        ArrayList<SciencePlan> allSciencePlans = o.getAllSciencePlans();
        ArrayList<SciencePlan> savedSciencePlans = new ArrayList<>();
        for (SciencePlan sp : allSciencePlans) {
            if (sp.getStatus().equals(SciencePlan.STATUS.SAVED)) {
                savedSciencePlans.add(sp);
            }
        }

        ArrayList<OurSciencePlan> oursavedSciencePlans = new ArrayList<>();
        for (SciencePlan plan : savedSciencePlans) {
            oursavedSciencePlans.add(new OurSciencePlanAdapter(plan));
        }
        model.addAttribute("plans", oursavedSciencePlans);
        return "submit";
    }


    @PostMapping("/submission")
    public String handleSubmission(@RequestParam("planId") String planId, Model model, HttpSession session) throws ParseException {
        User user = (User) session.getAttribute("loggininUser");
        if (user != null) {
            model.addAttribute("username", user.getName());
        } else {
            return "redirect:/login";
        }

        String submitResult = "";

        ArrayList<SciencePlan> allSciencePlans = o.getAllSciencePlans();
        ArrayList<OurSciencePlan> ourSciencePlans = new ArrayList<>();
        for (SciencePlan plan : allSciencePlans) {
            ourSciencePlans.add(new OurSciencePlanAdapter(plan));
        }

        for (OurSciencePlan sp : ourSciencePlans) {
            if (sp.getPlanNo() == Integer.parseInt(planId)) {
                if (sp.getStatus().equals(SciencePlan.STATUS.TESTED)) {
                    sp.setSubmitterUser(user);
                    submitResult = o.submitSciencePlan(new SciencePlanAdapter(sp));
                    model.addAttribute("submitResult", submitResult);
                    return "submitResult";
                }
            }
            submitResult = o.submitSciencePlan(new SciencePlanAdapter(sp));
        }

        model.addAttribute("submitResult", submitResult);
        return "submitResult";
    }


}