/*
 * Copyright 2014, The OpenNMS Group
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License. You may obtain
 * a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 *     
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.opennms.newts.rest;

import static com.google.inject.name.Names.named;

import org.opennms.newts.api.DefaultSampleProcessorService;
import org.opennms.newts.api.SampleProcessorService;

import com.codahale.metrics.MetricRegistry;
import com.google.inject.AbstractModule;


/**
 * Base Guice configuration.
 * 
 * @author eevans
 */
public class NewtsGuiceModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(MetricRegistry.class).annotatedWith(named("newtsMetricRegistry")).toInstance(new MetricRegistry());
        bind(SampleProcessorService.class).to(DefaultSampleProcessorService.class);
    }

}
