package Demo.repository;
import Demo.model.OurSciencePlan;
import org.springframework.data.repository.CrudRepository;

public interface SciplanRepository  extends CrudRepository<OurSciencePlan, Integer>{
}
