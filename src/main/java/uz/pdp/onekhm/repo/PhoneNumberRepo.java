package uz.pdp.onekhm.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.onekhm.domain.PhoneNumber;

public interface PhoneNumberRepo extends JpaRepository<PhoneNumber, String> {
}
