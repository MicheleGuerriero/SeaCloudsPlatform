/**
 * Copyright 2014 SeaClouds
 * Contact: SeaClouds
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package eu.seaclouds.platform.planner.core.application.topology.nodetemplate;

import eu.seaclouds.platform.planner.core.DamGenerator;
import eu.seaclouds.platform.planner.core.application.topology.nodetemplate.datacollectors.Datacollector;
import eu.seaclouds.platform.planner.core.application.topology.nodetemplate.host.ComputeNodeTemplate;
import eu.seaclouds.platform.planner.core.application.topology.nodetemplate.host.PlatformNodeTemplate;

import java.util.Map;

public class NodeTemplateFactory {

    public static NodeTemplate createNodeTemplate(Map<String, Object> applicationTemplate,
                                                  String nodeTemplateId) {

        Map<String, Object> topologyTemplate = (Map<String, Object>) applicationTemplate.get(DamGenerator.TOPOLOGY_TEMPLATE);
        Map<String, Object> nodeTemplates = (Map<String, Object>) topologyTemplate.get(DamGenerator.NODE_TEMPLATES);
        Map<String, Object> module = (Map<String, Object>) nodeTemplates.get(nodeTemplateId);

        String moduleType = (String) module.get(NodeTemplate.TYPE);

        if (ComputeNodeTemplate.isSupported(moduleType)) {
            return new ComputeNodeTemplate(applicationTemplate, nodeTemplateId);
        } else if (PlatformNodeTemplate.isSupported(moduleType)) {
            return new PlatformNodeTemplate(applicationTemplate, nodeTemplateId);
        } else if (Datacollector.isSupported(moduleType)) {
            return new Datacollector(applicationTemplate, nodeTemplateId);
        } else {
            return new AbstractNodeTemplate(applicationTemplate, nodeTemplateId);
        }

    }

}
