# scoreReporting
Score reporting system for cuc2017, cuc2018 and cuc2019

# AWS elasticbeanstalk configuration to add
- use t2-small size
- use loadbalanced instance
- loadbalance: application and add port 443 (need sticky sessions)
- add mysql database default configuration except
   - username: cuc2019 password: chooseone
   - delete on delete instance
- enivornment properties:
   - SERVER_PORT: 5000
   - SPRING_DATASOURCE_PASSWORD: one set above
   - SPRING_DATASOURCE_URL: jdbc:mysql:// + rds url + /ebdb
   - SPRING_DATASOURCE_USERNAME: cuc2019
   - SPRING_JPA_DATABASE_PLATFORM: org.hibernate.dialect.MySQL5Dialect
   - SPRING_JPA_HIBERNATE_DDL_AUTO: update
