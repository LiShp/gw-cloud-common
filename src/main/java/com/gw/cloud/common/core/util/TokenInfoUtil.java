package com.gw.cloud.common.core.util;

import com.gw.cloud.common.core.base.entity.AbstractBaseUpdateEntity;
import com.gw.cloud.common.core.enume.OperType;
import com.gw.oauthcm.entity.TokenEntity;
import com.gw.oauthcm.utils.AuthUtil;

/**
 * Token 信息处理 工具类
 * @author dcc
 * @date 2019/9/25
 */
public class TokenInfoUtil {

    /**
     * 赋值token信息给实体对象
     * 添加或编辑时 处理 创建人id,code,name;修改人id,code,name
     * @param updateVO
     * @param operType ADD(0, "添加操作"),UPDATE(1, "编辑操作");
     * @return
     */
    public static AbstractBaseUpdateEntity dealTokenInfo(AbstractBaseUpdateEntity updateVO,int operType){
        TokenEntity tokenInfo = AuthUtil.getTokenInfo();
        if(operType == OperType.ADD.getId() ){
            updateVO.setCreatorId(tokenInfo.getUserId());
            updateVO.setCreatorCode(tokenInfo.getUserCode());
            updateVO.setCreatorName(tokenInfo.getUserName());
        }
        updateVO.setUpdatorId(tokenInfo.getUserId());
        updateVO.setUpdatorCode(tokenInfo.getUserCode());
        updateVO.setUpdatorName(tokenInfo.getUserName());
        return updateVO;
    }
}
