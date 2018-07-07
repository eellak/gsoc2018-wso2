package org.wso2.carbon.identity.claim.sample.service.model;

public class ClaimModel {

    private String key;

    private String lastname;

    private String age ;

    private String dependancies;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getDependancies() {
        return dependancies;
    }

    public void setDependancies(String dependancies) {
        this.dependancies = dependancies;
    }
}
