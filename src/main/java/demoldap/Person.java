package demoldap;

import lombok.Data;
import org.springframework.ldap.odm.annotations.*;

import javax.naming.Name;

@Entry(base = "ou=people,dc=springframework,dc=org", objectClasses = "inetOrgPerson")
@Data
public class Person {

    @Id
    private Name id;
    
    @DnAttribute(value = "uid", index = 3)
    private String uid;
    
    @Attribute(name = "cn")
    private String commonName;
    
    @Attribute(name = "sn")
    private String surName;
    
    private String userPassword;

}