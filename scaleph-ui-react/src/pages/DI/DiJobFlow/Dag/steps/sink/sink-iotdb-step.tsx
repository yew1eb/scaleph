import {NsGraph} from "@antv/xflow";
import {ModalFormProps} from '@/app.d';
import {IoTDBParams, STEP_ATTR_TYPE} from "@/pages/DI/DiJobFlow/Dag/constant";
import {JobService} from "@/services/project/job.service";
import {Form, message, Modal} from "antd";
import {DiJob} from "@/services/project/typings";
import {getIntl, getLocale} from "umi";
import {useEffect} from "react";
import {ProForm, ProFormDigit, ProFormSwitch, ProFormText} from "@ant-design/pro-components";

const SinkIoTDBStepForm: React.FC<ModalFormProps<{
  node: NsGraph.INodeConfig;
  graphData: NsGraph.IGraphData;
  graphMeta: NsGraph.IGraphMeta;
}>> = ({data, visible, onCancel, onOK}) => {
  const nodeInfo = data.node.data;
  const jobInfo = data.graphMeta.origin as DiJob;
  const jobGraph = data.graphData;
  const intl = getIntl(getLocale(), true);
  const [form] = Form.useForm();

  useEffect(() => {
    form.setFieldValue(STEP_ATTR_TYPE.stepTitle, nodeInfo.data.displayName);
  }, []);

  return (<Modal
    open={visible}
    title={nodeInfo.data.displayName}
    width={780}
    bodyStyle={{overflowY: 'scroll', maxHeight: '640px'}}
    destroyOnClose={true}
    onCancel={onCancel}
    onOk={() => {
      form.validateFields().then((values) => {
        let map: Map<string, any> = new Map();
        map.set(STEP_ATTR_TYPE.jobId, jobInfo.id);
        map.set(STEP_ATTR_TYPE.jobGraph, JSON.stringify(jobGraph));
        map.set(STEP_ATTR_TYPE.stepCode, nodeInfo.id);
        map.set(STEP_ATTR_TYPE.stepAttrs, values);
        JobService.saveStepAttr(map).then((resp) => {
          if (resp.success) {
            message.success(intl.formatMessage({id: 'app.common.operate.success'}));
            onCancel();
            onOK ? onOK() : null;
          }
        });
      });
    }}
  >
    <ProForm form={form} initialValues={nodeInfo.data.attrs} grid={true} submitter={false}>
      <ProFormText
        name={STEP_ATTR_TYPE.stepTitle}
        label={intl.formatMessage({id: 'pages.project.di.step.stepTitle'})}
        rules={[{required: true}, {max: 120}]}
      />
      <ProFormText
        name={IoTDBParams.nodeUrls}
        label={intl.formatMessage({id: 'pages.project.di.step.iotdb.nodeUrls'})}
        rules={[{required: true}]}
      />
      <ProFormText
        name={IoTDBParams.username}
        label={intl.formatMessage({id: 'pages.project.di.step.iotdb.username'})}
        rules={[{required: true}]}
      />
      <ProFormText
        name={IoTDBParams.password}
        label={intl.formatMessage({id: 'pages.project.di.step.iotdb.password'})}
        rules={[{required: true}]}
      />
      <ProFormDigit
        name={IoTDBParams.batchSize}
        label={intl.formatMessage({id: 'pages.project.di.step.iotdb.batchSize'})}
        initialValue={1024}
        fieldProps={{
          step: 100,
          min: 1
        }}
      />
      <ProFormDigit
        name={IoTDBParams.batchIntervalMs}
        label={intl.formatMessage({id: 'pages.project.di.step.iotdb.batchIntervalMs'})}
        initialValue={1000}
        fieldProps={{
          step: 1000,
          min: 1
        }}
      />
      <ProFormDigit
        name={IoTDBParams.maxRetries}
        label={intl.formatMessage({id: 'pages.project.di.step.iotdb.maxRetries'})}
        colProps={{span: 6}}
        fieldProps={{
          min: 1
        }}
      />
      <ProFormDigit
        name={IoTDBParams.retryBackoffMultiplierMs}
        label={intl.formatMessage({id: 'pages.project.di.step.iotdb.retryBackoffMultiplierMs'})}
        colProps={{span: 9}}
        fieldProps={{
          step: 1000,
          min: 1
        }}
      />
      <ProFormDigit
        name={IoTDBParams.maxRetryBackoffMs}
        label={intl.formatMessage({id: 'pages.project.di.step.iotdb.maxRetryBackoffMs'})}
        colProps={{span: 9}}
        fieldProps={{
          step: 1000,
          min: 1
        }}
      />
      <ProFormDigit
        name={IoTDBParams.defaultThriftBufferSize}
        label={intl.formatMessage({id: 'pages.project.di.step.iotdb.thriftDefaultBufferSize'})}
        fieldProps={{
          step: 1000,
          min: 1
        }}
      />
      <ProFormDigit
        name={IoTDBParams.maxThriftFrameSize}
        label={intl.formatMessage({id: 'pages.project.di.step.iotdb.thriftMaxFrameSize'})}
        fieldProps={{
          step: 1000,
          min: 1
        }}
      />
      <ProFormText
        name={IoTDBParams.zoneId}
        label={intl.formatMessage({id: 'pages.project.di.step.iotdb.zoneId'})}
      />
      <ProFormSwitch
        name={IoTDBParams.enableRpcCompression}
        label={intl.formatMessage({id: 'pages.project.di.step.iotdb.enableRpcCompression'})}
      />
      <ProFormDigit
        name={IoTDBParams.connectionTimeoutInMs}
        label={intl.formatMessage({id: 'pages.project.di.step.iotdb.connectionTimeoutInMs'})}
        fieldProps={{
          step: 1000,
          min: 1
        }}
      />
    </ProForm>
  </Modal>);
}

export default SinkIoTDBStepForm;