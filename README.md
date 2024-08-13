# scoreReporting
Score reporting system for cuc2017, cuc2018, cuc2019 and cuc2024

# AWS elasticbeanstalk configuration to add
- use t3-large t3-medium size
- use loadbalanced instance
- loadbalance: application and add port 443 (need sticky sessions)
- add mysql database default configuration except
   - username: cuc2024 password: chooseone
   - delete on delete instance
- enivornment properties:
   - SERVER_PORT: 5000
   - SPRING_DATASOURCE_PASSWORD: as set above
   - SPRING_DATASOURCE_URL: jdbc:mysql:// + rds url + :3306/ebdb
   - SPRING_DATASOURCE_USERNAME: cuc2024
   - SPRING_JPA_HIBERNATE_DDL_AUTO: update
