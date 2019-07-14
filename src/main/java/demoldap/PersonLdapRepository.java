package demoldap;
import java.util.List;

import org.springframework.data.ldap.repository.LdapRepository;
import org.springframework.stereotype.Repository;

public interface PersonLdapRepository extends LdapRepository<Person>{

//	Person findByUsername(String username);
//
//	Person findByUsernameAndPassword(String username, String password);
//
//    List<Person> findByUsernameLikeIgnoreCase(String username);
}
