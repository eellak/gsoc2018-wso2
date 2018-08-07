
# WSO2 Identity Server Userstore using Web Services to get claims

This project was added to [Google Summer of Code 2018](https://summerofcode.withgoogle.com/) by the [GFOSS - Open Technologies Alliance](https://gfoss.eu/home-posts/) .

---
**Description**
---
WSO2 Identity and Access Management Server is open source popular identity and access management server throughout the world plus WSO2 Identity Server efficiently undertakes the complex task of identity management across enterprise applications, services, and APIs.

**Implementation**
---
This project is based on the [WSO2 Identity server version 5.4.](https://wso2.com/identity-and-access-management/previous-releases) Currently, WSO2 identity server is consisting of SOAP services and in near future, there will be REST API's which support for all functionalities and which is more effective. In current environment most It support for different user stores like LDAP, JDBC, and MySQL as primary and secondary user stores.

WSO2 Identity server allows configuring multiple user stores to the system that are used to store users and roles. AS there are 2 types of user stores as primary userstore  (mandatory) and secondary user store (optional). And all the user informations are peristing on single user store in this version. From this implementation it will separate as credential userstore and attribute user store. Attribute user store is simply used to store claims details which can be access by providing user credential and secrete.
 with the having facility of creating a new user store the primary data which are saving to primary user store can be separated to different user stores as one for user details and other one is for user attribute (claims) details which can be accessed by providing user credentials and secrete.

**Mentor**
---
- Panagiotis Kranidiotis

**Timeline**
---
- May 14 - May 21

Design and develop the architecture of the project by customizing use case of the project .

- May 22 - June 20

 Implementation an of the custom authenticator

- June 21 - July 20

Implementation of  the web service

- July 21- August 08

Implementation of the  documentation and Testing process.

