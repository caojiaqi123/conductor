/*
 * Copyright 2020 Netflix, Inc.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package com.netflix.conductor.elasticsearch;

import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;

import com.netflix.conductor.elasticsearch.es7.ElasticSearchV7Module;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;

public class ElasticSearchModule extends AbstractModule {
    @Override
    protected void configure() {

        ElasticSearchConfiguration esConfiguration =
                new SystemPropertiesElasticSearchConfiguration();

        bind(ElasticSearchConfiguration.class).to(SystemPropertiesElasticSearchConfiguration.class);

        bind(RestClient.class)
                .toProvider(ElasticSearchRestClientProvider.class)
                .in(Singleton.class);
        bind(RestClientBuilder.class)
                .toProvider(ElasticSearchRestClientBuilderProvider.class)
                .in(Singleton.class);

        install(new ElasticSearchV7Module(esConfiguration));
    }
}
