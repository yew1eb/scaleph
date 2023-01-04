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

package cn.sliew.scaleph.engine.flink.service.convert;

import cn.sliew.milky.common.util.JacksonUtil;
import cn.sliew.scaleph.common.convert.BaseConvert;
import cn.sliew.scaleph.dao.entity.master.resource.ResourceClusterCredential;
import cn.sliew.scaleph.dao.entity.master.resource.ResourceFlinkRelease;
import cn.sliew.scaleph.dao.entity.master.ws.WsFlinkClusterConfig;
import cn.sliew.scaleph.engine.flink.service.dto.KubernetesOptionsDTO;
import cn.sliew.scaleph.engine.flink.service.dto.WsFlinkClusterConfigDTO;
import cn.sliew.scaleph.resource.service.convert.ClusterCredentialConvert;
import cn.sliew.scaleph.resource.service.convert.FlinkReleaseConvert;
import com.fasterxml.jackson.core.type.TypeReference;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Map;

@Mapper(uses = {FlinkReleaseConvert.class, ClusterCredentialConvert.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface WsFlinkClusterConfigConvert extends BaseConvert<WsFlinkClusterConfig, WsFlinkClusterConfigDTO> {
    WsFlinkClusterConfigConvert INSTANCE = Mappers.getMapper(WsFlinkClusterConfigConvert.class);

    @Override
    default WsFlinkClusterConfig toDo(WsFlinkClusterConfigDTO dto) {
        if (dto == null) {
            return null;
        }
        WsFlinkClusterConfig entity = new WsFlinkClusterConfig();
        entity.setId(dto.getId());
        entity.setCreateTime(dto.getCreateTime());
        entity.setCreator(dto.getCreator());
        entity.setEditor(dto.getEditor());
        entity.setUpdateTime(dto.getUpdateTime());
        entity.setProjectId(dto.getProjectId());
        entity.setName(dto.getName());
        entity.setFlinkVersion(dto.getFlinkVersion());
        entity.setResourceProvider(dto.getResourceProvider());
        entity.setDeployMode(dto.getDeployMode());
        entity.setFlinkRelease(FlinkReleaseConvert.INSTANCE.toDo(dto.getFlinkRelease()));
        entity.setFlinkReleaseId(entity.getFlinkRelease().getId());
        entity.setClusterCredential(ClusterCredentialConvert.INSTANCE.toDo(dto.getClusterCredential()));
        entity.setClusterCredentialId(entity.getClusterCredential().getId());
        if (dto.getKubernetesOptionsDTO() != null) {
            entity.setKubernetesOptions(JacksonUtil.toJsonString(dto.getKubernetesOptionsDTO()));
        }
        if (!CollectionUtils.isEmpty(dto.getConfigOptions())) {
            entity.setConfigOptions(JacksonUtil.toJsonString(dto.getConfigOptions()));
        }
        entity.setRemark(dto.getRemark());
        return entity;
    }

    @Override
    default WsFlinkClusterConfigDTO toDto(WsFlinkClusterConfig entity) {
        if (entity == null) {
            return null;
        }
        WsFlinkClusterConfigDTO dto = new WsFlinkClusterConfigDTO();
        BeanUtils.copyProperties(entity, dto);
        if (entity.getFlinkReleaseId() != null && entity.getFlinkRelease() == null) {
            ResourceFlinkRelease flinkRelease = new ResourceFlinkRelease();
            flinkRelease.setId(entity.getFlinkReleaseId());
            entity.setFlinkRelease(flinkRelease);
        }
        dto.setFlinkRelease(FlinkReleaseConvert.INSTANCE.toDto(entity.getFlinkRelease()));
        if (entity.getClusterCredentialId() != null && entity.getClusterCredential() == null) {
            ResourceClusterCredential clusterCredential = new ResourceClusterCredential();
            clusterCredential.setId(entity.getClusterCredentialId());
            entity.setClusterCredential(clusterCredential);
        }
        dto.setClusterCredential(ClusterCredentialConvert.INSTANCE.toDto(entity.getClusterCredential()));
        if (StringUtils.hasText(entity.getKubernetesOptions())) {
            dto.setKubernetesOptionsDTO(JacksonUtil.parseJsonString(entity.getKubernetesOptions(), KubernetesOptionsDTO.class));
        }
        if (StringUtils.hasText(entity.getConfigOptions())) {
            dto.setConfigOptions(JacksonUtil.parseJsonString(entity.getConfigOptions(), new TypeReference<Map<String, String>>() {
            }));
        }
        return dto;
    }
}