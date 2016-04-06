package eu.seaclouds.monitor.monitoringdamgenerator.dcgenerators;

import eu.seaclouds.monitor.monitoringdamgenerator.adpparsing.Module;

public interface DataCollectorGenerator {
    public static final String MODACLOUDS_TOWER4CLOUDS_MANAGER_IP = 
            "MODACLOUDS_TOWER4CLOUDS_MANAGER_IP";
    public static final String MODACLOUDS_TOWER4CLOUDS_MANAGER_PORT = 
            "MODACLOUDS_TOWER4CLOUDS_MANAGER_PORT";
    public static final String MODACLOUDS_TOWER4CLOUDS_DC_SYNC_PERIOD = 
            "MODACLOUDS_TOWER4CLOUDS_DC_SYNC_PERIOD";
    public static final String MODACLOUDS_TOWER4CLOUDS_RESOURCES_KEEP_ALIVE_PERIOD = 
            "MODACLOUDS_TOWER4CLOUDS_RESOURCES_KEEP_ALIVE_PERIOD";
    public static final String MODACLOUDS_TOWER4CLOUDS_VM_TYPE = 
            "MODACLOUDS_TOWER4CLOUDS_VM_TYPE";
    public static final String MODACLOUDS_TOWER4CLOUDS_VM_ID = 
            "MODACLOUDS_TOWER4CLOUDS_VM_ID";
    public static final String MODACLOUDS_TOWER4CLOUDS_INTERNAL_COMPONENT_ID = 
            "MODACLOUDS_TOWER4CLOUDS_INTERNAL_COMPONENT_ID";
    public static final String MODACLOUDS_TOWER4CLOUDS_INTERNAL_COMPONENT_TYPE = 
            "MODACLOUDS_TOWER4CLOUDS_INTERNAL_COMPONENT_TYPE";


    public void addDataCollector(Module moduleInfo,
            String monitoringManagerIp,
            int monitoringManagerPort);
}