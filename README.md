# scoreReporting
Score reporting system for cuc2017, cuc2018, cuc2019 and cuc2024

# create zip to give to AWS
- update pom version number
- update Profile version number to match
- mvn clean package
- copy scoreReporting jar to deploy
- zip scoreReporting-[version].zip Procfile scoreReporting-[version].jar

# AWS elasticbeanstalk configuration to add
see also https://www.codejava.net/aws/elastic-beanstalk-deploy-spring-boot-application-with-mysql#google_vignette
- select custom configuration
- need to create service roles and ec2 instance profile
- use t3-large t3-medium size
- use loadbalanced instance
- loadbalance: application and add port 443 (need sticky sessions)
- add mysql database default configuration except
   - username: cuc2024 password: chooseone
   - delete on delete instance
- enivornment properties:
   - SERVER_PORT: 5000
   - SPRING_DATASOURCE_PASSWORD: as set above
   - SPRING_DATASOURCE_URL: jdbc:mysql:// + endpoint + :3306/ebdb
   - SPRING_DATASOURCE_USERNAME: cuc2024
   - SPRING_JPA_HIBERNATE_DDL_AUTO: update

# Bugs to fix
- undo halftime gives back timeouts
- update from server on occasion instead of just live

# Ideas for improvements
- Chat with admin
- Buttons for water or medical
- Translate to french
- Add start pull
- Add start ratio for mix
- Ask if observed (so no timing)
- Keep starting soon active for 45 mins
- How to start editing a game on a new iPad
