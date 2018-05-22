package pl.jkan.carrental.carcatalog;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
interface CarDetailsRepository extends CrudRepository<CarDetails, String> {

}
