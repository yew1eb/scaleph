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

package cn.sliew.scaleph.plugin.seatunnel.flink.connectors.fake.source;

import cn.sliew.scaleph.plugin.framework.property.*;
import com.fasterxml.jackson.databind.JsonNode;

public enum FakeProperties {
    ;

    public static final PropertyDescriptor<JsonNode> SCHEMA = new PropertyDescriptor.Builder<JsonNode>()
            .name("schema")
            .description(
                    "Table structure description ,you should assign schema option to tell connector how to parse data to the row you want.")
            .type(PropertyType.OBJECT)
            .parser(Parsers.JSON_PARSER)
            .properties(Property.Required)
            .addValidator(Validators.NON_BLANK_VALIDATOR)
            .validateAndBuild();

    public static final PropertyDescriptor<Integer> ROW_NUM = new PropertyDescriptor.Builder<Integer>()
            .name("row.num")
            .description("Table structure description ,you should assign schema option to tell connector how to parse data to the row you want.")
            .type(PropertyType.INT)
            .parser(Parsers.INTEGER_PARSER)
            .defaultValue("5")
            .addValidator(Validators.POSITIVE_INTEGER_VALIDATOR)
            .validateAndBuild();

    public static final PropertyDescriptor<Integer> SPLIT_NUM = new PropertyDescriptor.Builder<Integer>()
            .name("split.num")
            .description("the number of splits generated by the enumerator for each degree of parallelism")
            .type(PropertyType.INT)
            .parser(Parsers.INTEGER_PARSER)
            .defaultValue("1")
            .addValidator(Validators.POSITIVE_INTEGER_VALIDATOR)
            .validateAndBuild();

    public static final PropertyDescriptor<Long> SPLIT_READ_INTERVAL = new PropertyDescriptor.Builder<Long>()
            .name("split.read-interval")
            .description("The interval(mills) between two split reads in a reader")
            .type(PropertyType.INT)
            .parser(Parsers.LONG_PARSER)
            .defaultValue("1")
            .addValidator(Validators.POSITIVE_LONG_VALIDATOR)
            .validateAndBuild();

    public static final PropertyDescriptor<Integer> MAP_SIZE = new PropertyDescriptor.Builder<Integer>()
            .name("map.size")
            .description("The size of map type that connector generated")
            .type(PropertyType.INT)
            .parser(Parsers.INTEGER_PARSER)
            .defaultValue("5")
            .addValidator(Validators.POSITIVE_INTEGER_VALIDATOR)
            .validateAndBuild();

    public static final PropertyDescriptor<Integer> ARRAY_SIZE = new PropertyDescriptor.Builder<Integer>()
            .name("array.size")
            .description("The size of array type that connector generated")
            .type(PropertyType.INT)
            .parser(Parsers.INTEGER_PARSER)
            .defaultValue("5")
            .addValidator(Validators.POSITIVE_INTEGER_VALIDATOR)
            .validateAndBuild();

    public static final PropertyDescriptor<Integer> BYTES_SIZE = new PropertyDescriptor.Builder<Integer>()
            .name("bytes.length")
            .description("The length of bytes type that connector generated")
            .type(PropertyType.INT)
            .parser(Parsers.INTEGER_PARSER)
            .defaultValue("5")
            .addValidator(Validators.POSITIVE_INTEGER_VALIDATOR)
            .validateAndBuild();

    public static final PropertyDescriptor<Integer> STRING_SIZE = new PropertyDescriptor.Builder<Integer>()
            .name("string.length")
            .description("The length of string type that connector generated")
            .type(PropertyType.INT)
            .parser(Parsers.INTEGER_PARSER)
            .defaultValue("5")
            .addValidator(Validators.POSITIVE_INTEGER_VALIDATOR)
            .validateAndBuild();

}
