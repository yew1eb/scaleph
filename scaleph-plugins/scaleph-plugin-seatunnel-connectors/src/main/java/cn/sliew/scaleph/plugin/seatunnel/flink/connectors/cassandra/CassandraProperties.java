/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.sliew.scaleph.plugin.seatunnel.flink.connectors.cassandra;

import cn.sliew.scaleph.plugin.framework.property.*;

public enum CassandraProperties {
    ;

    public static final PropertyDescriptor<String> HOST = new PropertyDescriptor.Builder<String>()
            .name("host")
            .description("Cassandra cluster address, the format is host:port")
            .type(PropertyType.STRING)
            .parser(Parsers.STRING_PARSER)
            .properties(Property.Required)
            .addValidator(Validators.NON_BLANK_VALIDATOR)
            .validateAndBuild();

    public static final PropertyDescriptor<String> KEYSPACE = new PropertyDescriptor.Builder<String>()
            .name("keyspace")
            .description("The Cassandra keyspace.")
            .type(PropertyType.STRING)
            .parser(Parsers.STRING_PARSER)
            .properties(Property.Required)
            .addValidator(Validators.NON_BLANK_VALIDATOR)
            .validateAndBuild();

    public static final PropertyDescriptor<String> USERNAME = new PropertyDescriptor.Builder<String>()
            .name("username")
            .description("Cassandra user username.")
            .type(PropertyType.STRING)
            .parser(Parsers.STRING_PARSER)
            .addValidator(Validators.NON_BLANK_VALIDATOR)
            .validateAndBuild();

    public static final PropertyDescriptor<String> PASSWORD = new PropertyDescriptor.Builder<String>()
            .name("password")
            .description("Cassandra user password.")
            .type(PropertyType.STRING)
            .parser(Parsers.STRING_PARSER)
            .addValidator(Validators.NON_BLANK_VALIDATOR)
            .validateAndBuild();

    public static final PropertyDescriptor<String> DATACENTER = new PropertyDescriptor.Builder<String>()
            .name("datacenter")
            .description("The Cassandra datacenter")
            .type(PropertyType.STRING)
            .parser(Parsers.STRING_PARSER)
            .defaultValue("datacenter1")
            .addValidator(Validators.NON_BLANK_VALIDATOR)
            .validateAndBuild();

    public static final PropertyDescriptor<String> CONSISTENCY_LEVEL = new PropertyDescriptor.Builder<String>()
            .name("consistency_level")
            .description("The Cassandra write consistency level")
            .type(PropertyType.STRING)
            .parser(Parsers.STRING_PARSER)
            .defaultValue("LOCAL_ONE")
            .addValidator(Validators.NON_BLANK_VALIDATOR)
            .validateAndBuild();

}
