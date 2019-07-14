package demoldap;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapContextSource;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class Application implements CommandLineRunner  {
	
	@Autowired
	private PersonRepository personRepository;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

	@Override
	public void run(String... args) throws Exception {
		
		log.info("start command line...");
		
		log.info("list out all the users");
		
		personRepository.findAll().forEach(p -> {
			log.info(p.toString());
		});
		
		
		log.info("save a person");
		
		Person person = new Person();
		person.setUid("abc");
		person.setSurName("AAA");
		person.setCommonName("aaa");
		person.setUserPassword("abcd1234");
		personRepository.save(person);
		
		log.info("list out all the users");
		
		personRepository.findAll().forEach(p -> {
			log.info(p.toString());
		});
		
		
		//contextSource().getContext("uid=bob,ou=people,dc=springframework,dc=org", "bobspassword");
		
		
	}
	
//    @Bean
//    public LdapTemplate ldapTemplate() {
//        return new LdapTemplate(contextSource());
//    }
//	
//	@Bean
//	public LdapContextSource contextSource() {
//		
//	    LdapContextSource contextSource = new LdapContextSource();
//	     
////	    contextSource.setUrl(env.getRequiredProperty("ldap.url"));
////	    contextSource.setBase(env.getRequiredProperty("ldap.partitionSuffix"));
////	    contextSource.setUserDn(env.getRequiredProperty("ldap.principal"));
////	    contextSource.setPassword(env.getRequiredProperty("ldap.password"));
//
////	    contextSource.setUrl("ldap://192.168.1.188:389");
////	    contextSource.setBase("dc=localhost");
//	    contextSource.setUrl("ldap://localhost:8389");
//	    contextSource.setBase("dc=springframework,dc=org");
//	    //contextSource.setUserDn(env.getRequiredProperty("ldap.principal"));
//	    //contextSource.setPassword(env.getRequiredProperty("ldap.password"));
//	    
//	    return contextSource;
//	}
	

    private String digestSHA(final String password) {
        String base64;
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA");
            digest.update(password.getBytes());
            base64 = Base64.getEncoder()
                .encodeToString(digest.digest());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        return "{SHA}" + base64;
    }

}
