package Demo.repository;

import Demo.model.SciencePlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SciencePlanRepository extends JpaRepository<SciencePlan, Integer> {
    // This interface now has a findAll() method inherited from JpaRepository
    List<SciencePlan> findByStatus(SciencePlan.STATUS status);

}