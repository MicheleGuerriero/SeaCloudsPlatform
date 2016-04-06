package eu.seaclouds.monitor.monitoringdamgenerator.dcgenerators;

import eu.seaclouds.monitor.monitoringdamgenerator.adpparsing.Module;
import eu.seaclouds.monitor.monitoringdamgenerator.dcgenerators.DataCollectorGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class JavaAppDcGenerator implements DataCollectorGenerator {

    private static Logger logger = LoggerFactory
            .getLogger(JavaAppDcGenerator.class);

    public void addDataCollector(Module module, String monitoringManagerIp,
            int monitoringManagerPort) {

            logger.info("Generating required deployment script for the java-app-level Data Collector.");

            Map<String, String> variables = this
                    .getRequiredEnvVars(module, monitoringManagerIp,
                            monitoringManagerPort);
            
            for(String key : variables.keySet()){
                module.addMonitoringEnvVar(key, variables.get(key));
            }
    }

    private Map<String, String> getRequiredEnvVars(Module module,
            String monitoringManagerIp, int monitoringManagerPort) {

        Map<String, String> toReturn = new HashMap<String, String>();

        toReturn.put(MODACLOUDS_TOWER4CLOUDS_MANAGER_IP, monitoringManagerIp);

        toReturn.put(MODACLOUDS_TOWER4CLOUDS_MANAGER_PORT,
                String.valueOf(monitoringManagerPort));

        return toReturn;

    }
}
