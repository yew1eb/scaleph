package cn.sliew.breeze.service.convert;

import cn.sliew.breeze.service.vo.DictVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author gleiyu
 */
@Mapper
public interface DictVoConvert {

    DictVoConvert INSTANCE = Mappers.getMapper(DictVoConvert.class);

    default String toDo(DictVO vo) {
        if (vo == null) {
            return null;
        }
        return vo.getValue();
    }
}