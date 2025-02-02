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

package cn.sliew.scaleph.engine.seatunnel.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.sliew.scaleph.dao.entity.master.ws.WsDiJobStep;
import cn.sliew.scaleph.dao.mapper.master.ws.WsDiJobStepMapper;
import cn.sliew.scaleph.engine.seatunnel.service.WsDiJobStepService;
import cn.sliew.scaleph.engine.seatunnel.service.convert.WsDiJobStepConvert;
import cn.sliew.scaleph.engine.seatunnel.service.dto.WsDiJobStepDTO;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

/**
 * @author gleiyu
 */
@Service
public class WsDiJobStepServiceImpl implements WsDiJobStepService {

    @Autowired
    private WsDiJobStepMapper diJobStepMapper;

    @Override
    public List<WsDiJobStepDTO> listJobStep(Long jobId) {
        List<WsDiJobStep> list = diJobStepMapper.selectByJobId(jobId);
        return WsDiJobStepConvert.INSTANCE.toDto(list);
    }

    @Override
    public WsDiJobStepDTO selectOne(Long jobId, String stepCode) {
        WsDiJobStep step = diJobStepMapper.selectOne(
                new LambdaQueryWrapper<WsDiJobStep>()
                        .eq(WsDiJobStep::getJobId, jobId)
                        .eq(WsDiJobStep::getStepCode, stepCode)
        );
        return WsDiJobStepConvert.INSTANCE.toDto(step);
    }

    @Override
    public int update(WsDiJobStepDTO wsDiJobStepDTO) {
        WsDiJobStep step = WsDiJobStepConvert.INSTANCE.toDo(wsDiJobStepDTO);
        LambdaUpdateWrapper<WsDiJobStep> updateWrapper = new LambdaUpdateWrapper<WsDiJobStep>()
                .eq(WsDiJobStep::getJobId, step.getJobId())
                .eq(WsDiJobStep::getStepCode, step.getStepCode());
        return diJobStepMapper.update(step, updateWrapper);
    }

    @Override
    public int upsert(WsDiJobStepDTO diJobStep) {
        LambdaQueryWrapper<WsDiJobStep> queryWrapper = new LambdaQueryWrapper<WsDiJobStep>()
                .eq(WsDiJobStep::getJobId, diJobStep.getJobId())
                .eq(WsDiJobStep::getStepCode, diJobStep.getStepCode());
        WsDiJobStep step = diJobStepMapper.selectOne(queryWrapper);
        WsDiJobStep jobStep = WsDiJobStepConvert.INSTANCE.toDo(diJobStep);
        if (step == null) {
            return diJobStepMapper.insert(jobStep);
        }
        return diJobStepMapper.update(jobStep,
                new LambdaUpdateWrapper<WsDiJobStep>()
                        .eq(WsDiJobStep::getJobId, jobStep.getJobId())
                        .eq(WsDiJobStep::getStepCode, jobStep.getStepCode())
        );
    }

    @Override
    public int deleteByProjectId(Collection<Long> projectIds) {
        return diJobStepMapper.deleteByProjectId(projectIds);
    }

    @Override
    public int deleteByJobId(Collection<Long> jobIds) {
        return diJobStepMapper.deleteByJobId(jobIds);
    }

    @Override
    public int deleteSurplusStep(Long jobId, List<String> stepCodeList) {
        return diJobStepMapper.delete(
                new LambdaQueryWrapper<WsDiJobStep>()
                        .eq(WsDiJobStep::getJobId, jobId)
                        .notIn(CollectionUtil.isNotEmpty(stepCodeList), WsDiJobStep::getStepCode,
                                stepCodeList)
        );
    }

    @Override
    public int clone(Long sourceJobId, Long targetJobId) {
        return diJobStepMapper.clone(sourceJobId, targetJobId);
    }
}
