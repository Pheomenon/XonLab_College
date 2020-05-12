package com.xonlab.educenter.service;

import com.xonlab.educenter.entity.UcenterMember;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xonlab.educenter.entity.vo.RegisterVo;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author gao
 * @since 2020-04-29
 */
public interface UcenterMemberService extends IService<UcenterMember> {

    String login(UcenterMember member);

    void register(RegisterVo registerVo);

    UcenterMember getOpenIdMember(String openid);

    Integer countRegisterDay(String day);
}
