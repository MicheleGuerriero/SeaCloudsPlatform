package eu.seaclouds.monitor.monitoringdamgenerator.rulesgenerators;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.polimi.tower4clouds.rules.MonitoringRules;
import it.polimi.tower4clouds.rules.ObjectFactory;
import eu.seaclouds.monitor.monitoringdamgenerator.adpparsing.Module;

public class NuroRulesGenerator {

    private static final Logger logger = LoggerFactory
            .getLogger(NuroRulesGenerator.class);

    public static final ObjectFactory factory = new ObjectFactory();

    public MonitoringRules generateMonitoringRules(Module module) {

        MonitoringRules toReturn = factory.createMonitoringRules();

        logger.info("NuroApplication module found. Generating all the custom monitoring rules");

        toReturn.getMonitoringRules().addAll(
                this.generateThirtySecondsRuntimeRule(module)
                        .getMonitoringRules());
        toReturn.getMonitoringRules().addAll(
                this.generateThirtySecondsPlayerCountRule(module)
                        .getMonitoringRules());
        toReturn.getMonitoringRules().addAll(
                this.generateThirtySecondsRequestCountRule(module)
                        .getMonitoringRules());
        toReturn.getMonitoringRules().addAll(
                this.generateThirtySecondsThroughputRule(module)
                        .getMonitoringRules());
        toReturn.getMonitoringRules().addAll(
                this.generateNuroSlaRules(module)
                        .getMonitoringRules());

        return toReturn;
    }

    private MonitoringRules generateNuroSlaRules(Module module) {

        Map<String, String> parameters = new HashMap<String, String>();
        MonitoringRules toReturn = new MonitoringRules();
        parameters.put("samplingTime", "5");
        
        if(module.existResponseTimeRequirement()){
            toReturn.getMonitoringRules().addAll(RuleSchemaGenerator.fillMonitoringRuleSchema(
                    "nuroThirtySecondsSlaRuntimeRule" + module.getModuleName(), "30", "30",
                    "InternalComponent", module.getModuleName(),
                    "NUROServerLastTenSecondsAverageRunTime", parameters,
                    "Average", "InternalComponent",
                    "METRIC > " + module.getResponseTime(),
                    "NUROServerLastThirtySecondsAverageRunTime_Violation_" + module.getModuleName()).getMonitoringRules());
        }
        
        return toReturn;

    }

    private MonitoringRules generateThirtySecondsRuntimeRule(Module module) {

        Map<String, String> parameters = new HashMap<String, String>();
        parameters.put("samplingTime", "5");
        return RuleSchemaGenerator.fillMonitoringRuleSchema(
                "nuroThirtySecondsRuntimeRule" + module.getModuleName(), "30", "30",
                "InternalComponent", module.getModuleName(),
                "NUROServerLastTenSecondsAverageRunTime", parameters,
                "Average", "InternalComponent", null,
                "NUROServerLastThirtySecondsAverageRunTime_" + module.getModuleName());

    }

    private MonitoringRules generateThirtySecondsPlayerCountRule(Module module) {

        Map<String, String> parameters = new HashMap<String, String>();
        parameters.put("samplingTime", "10");
        return RuleSchemaGenerator.fillMonitoringRuleSchema(
                "nuroThirtySecondsPlayerCountRule" + module.getModuleName(), "30", "30",
                "InternalComponent", module.getModuleName(),
                "NUROServerLastTenSecondsPlayerCount", parameters, "Sum",
                "InternalComponent", null,
                "NUROServerLastThirtySecondsPlayerCount_" + module.getModuleName());

    }

    private MonitoringRules generateThirtySecondsRequestCountRule(Module module) {

        Map<String, String> parameters = new HashMap<String, String>();
        parameters.put("samplingTime", "10");
        return RuleSchemaGenerator.fillMonitoringRuleSchema(
                "nuroThirtySecondsRequestCountRule" + module.getModuleName(), "30", "30",
                "InternalComponent", module.getModuleName(),
                "NUROServerLastTenSecondsRequestCount", parameters, "Sum",
                "InternalComponent", null,
                "NUROServerLastThirtySecondsRequestCount_" + module.getModuleName());

    }

    private MonitoringRules generateThirtySecondsThroughputRule(Module module) {

        Map<String, String> parameters = new HashMap<String, String>();
        parameters.put("samplingTime", "5");
        return RuleSchemaGenerator.fillMonitoringRuleSchema(
                "nuroThirtySecondsThroughput" + module.getModuleName(), "30", "30", "InternalComponent",
                module.getModuleName(),
                "NUROServerLastTenSecondsAverageThroughput", parameters,
                "Average", "InternalComponent", null,
                "NUROServerLastThirtySecondsAverageThroughput_" + module.getModuleName());
    }

}
