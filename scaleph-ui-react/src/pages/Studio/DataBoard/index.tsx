import { cluster, job } from '@/services/studio/index';
import { Card, Col, Row, Statistic } from 'antd';
import { useCallback, useEffect, useState } from 'react';
import { useIntl } from 'umi';

const DataBoard: React.FC = () => {
  const intl = useIntl();
  const [clusterCnt, setClusterCnt] = useState(0);
  const [batchJobCnt, setBatchJobCnt] = useState(0);
  const [realtimeJobCnt, setRealtimeJobCnt] = useState(0);
  useEffect(() => {
    fetchCluster();
    fetchJob({ jobType: 'b' }).then((d) => setBatchJobCnt(d));
    fetchJob({ jobType: 'r' }).then((d) => setRealtimeJobCnt(d));
  }, []);
  // 集群数量
  const fetchCluster = useCallback(async () => {
    const clusterCnt = await cluster();
    setClusterCnt(clusterCnt);
  }, []);
  // 任务数量
  const fetchJob = useCallback(async (params: { jobType: string }) => {
    const clusterCnt = await job(params);
    return clusterCnt;
  }, []);

  return (
    <>
      <Row gutter={4}>
        <Col span="8">
          <Card>
            <Statistic
              title={intl.formatMessage({ id: 'studio.databoard.clusterCnt' })}
              value={clusterCnt}
              valueStyle={{ color: '#3f8600' }}
            />
          </Card>
        </Col>
        <Col span="8">
          <Card>
            <Statistic
              title={intl.formatMessage({ id: 'studio.databoard.batchJobCnt' })}
              value={batchJobCnt}
              valueStyle={{ color: '#3f8600' }}
            />
          </Card>
        </Col>
        <Col span="8">
          <Card>
            <Statistic
              title={intl.formatMessage({ id: 'studio.databoard.realtimeJobCnt' })}
              value={realtimeJobCnt}
              valueStyle={{ color: '#3f8600' }}
            />
          </Card>
        </Col>
      </Row>
    </>
  );
};

export default DataBoard;