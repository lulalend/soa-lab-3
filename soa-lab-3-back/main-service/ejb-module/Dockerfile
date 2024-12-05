FROM bitnami/wildfly:32.0.0

COPY ./soa-lab-3-back/main-service/ejb-module/build/libs/ejb-module-0.0.1-SNAPSHOT.war /opt/bitnami/wildfly/standalone/deployments/
COPY ./soa-lab-3-back/main-service/ejb-module/standalone.xml /opt/bitnami/wildfly/standalone/configuration/

RUN /opt/bitnami/wildfly/bin/add-user.sh -a -u user -p user
RUN /opt/bitnami/wildfly/bin/add-user.sh -u user1 -p user1

CMD ["/opt/bitnami/wildfly/bin/standalone.sh", "-b", "0.0.0.0"]
