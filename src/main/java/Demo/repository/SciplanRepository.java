package Demo.repository;
import Demo.model.SciencePlan;
import org.springframework.data.repository.CrudRepository;

public interface SciplanRepository  extends CrudRepository<SciencePlan, Integer>{
}
