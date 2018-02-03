package dealzo.repository;

import dealzo.document.Deal;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DealRepository extends MongoRepository<Deal, String>, QueryByExampleExecutor<Deal> {
    List<Deal> findByTitle(String title);

}
