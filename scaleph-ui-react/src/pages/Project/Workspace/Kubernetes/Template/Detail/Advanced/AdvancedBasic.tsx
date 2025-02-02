import {ProCard, ProFormGroup, ProFormSelect, ProFormText} from "@ant-design/pro-components";
import {DICT_TYPE} from "@/constant";
import {DictDataService} from "@/services/admin/dictData.service";

const AdvancedBasic: React.FC = () => {
  return (<ProCard
    title={'Basic'}
    headerBordered
    collapsible={true}>
    <ProFormGroup>
      <ProFormSelect
        name="spec.flinkVersion"
        label={"flinkVersion"}
        colProps={{span: 10, offset: 1}}
        showSearch={true}
        options={["v1_13", "v1_14", "v1_15", "v1_16"]}
        initialValue={"v1_13"}
      />
      <ProFormText
        name="spec.serviceAccount"
        label={'serviceAccount'}
        colProps={{span: 10, offset: 1}}
        initialValue={"flink"}
      />
      <ProFormText
        name="spec.image"
        label={'image'}
        colProps={{span: 10, offset: 1}}
        initialValue={"flink:1.13"}
      />
      <ProFormSelect
        name="spec.imagePullPolicy"
        label={"imagePullPolicy"}
        colProps={{span: 10, offset: 1}}
        request={() => DictDataService.listDictDataByType2(DICT_TYPE.imagePullPolicy)}
        initialValue={"IfNotPresent"}
      />
    </ProFormGroup>
  </ProCard>);
}

export default AdvancedBasic;
