/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.eventmesh.storage.pravega.config;

import org.apache.eventmesh.api.factory.StoragePluginFactory;
import org.apache.eventmesh.storage.pravega.PravegaConsumerImpl;
import org.apache.eventmesh.storage.pravega.PravegaProducerImpl;

import java.net.URI;

import org.junit.Assert;
import org.junit.Test;

public class PravegaStorageConfigTest {

    @Test
    public void getConfigWhenPravegaConsumerInit() {
        PravegaConsumerImpl consumer =
            (PravegaConsumerImpl) StoragePluginFactory.getMeshMQPushConsumer("pravega");

        PravegaStorageConfig config = consumer.getClientConfiguration();
        assertConfig(config);
    }

    @Test
    public void getConfigWhenPravegaProducerInit() {
        PravegaProducerImpl producer =
            (PravegaProducerImpl) StoragePluginFactory.getMeshMQProducer("pravega");

        PravegaStorageConfig config = producer.getClientConfiguration();
        assertConfig(config);
    }

    private void assertConfig(PravegaStorageConfig config) {
        Assert.assertEquals(URI.create("tcp://127.0.0.1:816"), config.getControllerURI());
        Assert.assertEquals("scope-success!!!", config.getScope());
        Assert.assertTrue(config.isAuthEnabled());
        Assert.assertEquals("username-success!!!", config.getUsername());
        Assert.assertEquals("password-success!!!", config.getPassword());
        Assert.assertTrue(config.isTlsEnable());
        Assert.assertEquals("truststore-success!!!", config.getTruststore());
        Assert.assertEquals(816, config.getClientPoolSize());
        Assert.assertEquals(1816, config.getQueueSize());
    }
}
