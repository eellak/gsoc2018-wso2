
# WSO2 Identity Server Userstore using Web Services to get claims


**Description**
---

WSO2 Identity and Access Management Server is open source popular identity and access management server throughout the world plus WSO2 Identity Server efficiently undertakes the complex task of identity management across enterprise applications, services, and APIs.

This scenario in WSO2IS which is required to first user needs to authenticate with primary usestore and get authenticated user's attributes. From user attributes list, need to get a specific attribute which is pre-configured by the end user and then call an web app to obtain external claims. Thereafter, this external claim's values should be used in the response object to send to the client application.

**Google summer of codes**
---

Google Summer of Code participant: Isuri Anuradha ([isu97](https://github.com)

Organization : [GFOSS - Open Technologies Alliance](https://gfoss.eu)

**Final report**
---

- Link : [Final Documentation](https://gist.github.com/isuri97/1dcdce22deebaa7f6b9d057bf7075902)

**Work Done**
---

- Implementation of custom authenticator : [custom federated authenticator](https://github.com/eellak/gsoc2018-wso2/tree/master/components/org.wso2.carbon.identity.sample.custom.authenticator)

- Implementation of web service : [sample web service](https://github.com/eellak/gsoc2018-wso2/tree/master/components/sample_web_application)


**Special Links**
---
 Links :

* Link to the timeline : [Timeline](https://docs.google.com/spreadsheets/d/1oHRgznE82yOQ7dBfqKD3IIba4ZlfTBd8lKsjUgp_fRU/edit?usp=sharing)

* Link to the project page : [GSOC Project](https://summerofcode.withgoogle.com/projects/#5787476321370112)

* Link to installation guide : [Installation guide](https://github.com/eellak/gsoc2018-wso2/wiki/Installation-Guide)

* Link to developer guide : [Developer guide](https://github.com/eellak/gsoc2018-wso2/wiki/Developer-Guide)

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

