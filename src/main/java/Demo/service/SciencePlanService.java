//package Demo.service;
//
//import Demo.model.SciencePlan;
//import Demo.repository.SciencePlanRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class SciencePlanService {
//
//    private final SciencePlanRepository sciencePlanRepository;
//
//    @Autowired
//    public SciencePlanService(SciencePlanRepository sciencePlanRepository) {
//        this.sciencePlanRepository = sciencePlanRepository;
//    }
//
//    public List<SciencePlan> getAllSciencePlans() {
//        return sciencePlanRepository.findAll();
//    }
//
//    public List<SciencePlan> getSciencePlansByStatus(SciencePlan.STATUS status) {
//        return sciencePlanRepository.findByStatus(status);
//    }
//
//    public String submitSciencePlan(Integer planId) {
//        return sciencePlanRepository.findById(planId)
//                .map(plan -> {
//                    if (plan.getStatus() == SciencePlan.STATUS.SAVED) {
//                        plan.setStatus(SciencePlan.STATUS.SUBMITTED);
//                        sciencePlanRepository.save(plan);
//                        return "Science Plan submitted successfully!";
//                    } else {
//                        return "Submission failed: Plan is not in a savable state.";
//                    }
//                })
//                .orElse("Submission failed: Science Plan not found.");
//    }
//
//
//
//}
