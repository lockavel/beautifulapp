package com.qf.beautifulapp.service.impl;

import com.qf.beautifulapp.entity.Order;
import com.qf.beautifulapp.dao.OrderDao;
import com.qf.beautifulapp.result.ResponseData;
import com.qf.beautifulapp.service.OrderService;
import com.qf.beautifulapp.util.SendSms;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import javax.xml.crypto.dsig.keyinfo.RetrievalMethod;
import java.util.Date;
import java.util.UUID;
import java.util.regex.Pattern;

/**
 * (Order)表服务实现类
 *
 * @author makejava
 * @since 2022-04-29 11:21:59
 */
@Service("orderService")
public class OrderServiceImpl implements OrderService {
    @Resource
    private OrderDao orderDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Order queryById(Long id) {
        return this.orderDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param order       筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @Override
    public Page<Order> queryByPage(Order order, PageRequest pageRequest) {
        long total = this.orderDao.count(order);
        return new PageImpl<>(this.orderDao.queryAllByLimit(order, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param order 实例对象
     * @return 实例对象
     */
    @Override
    public ResponseData insert(Order order) {
        //用户预约以后，会收到一条由平台发送的短信，凭借短信验证码进行服务验证
        //0.对手机号进行验证(正则表达式)

        if (Pattern.matches("^1[3-9]\\d{9}$", order.getUsertell())) {
            //1.先生成随机的验证码//随机的验证码0,1,2,3,4,5
            String code = UUID.randomUUID().toString().substring(0,5);//取下标0-4 5不取取前不取后
            //2.配置数据
            order.setOpenid(code);
            order.setOrderstate("0");
            order.setPlacedate(new Date());

            //3.将数据添加到数据库中
            int insert = orderDao.insert(order);
            if(insert>0){
                //4.发送短信 --- 手机号
                Boolean res = SendSms.sendcode(order.getUsertell(),code);
                if(!res){
                    return new ResponseData("201","短信发送失败满请校验手机号是否正确");
                }
                return new ResponseData();
            }else{
                return new ResponseData("202","系统繁忙！请稍后再试");
            }
        }else{
            return new ResponseData("203","手机号有误，请重试！");
        }
    }

    /**
     * 修改数据
     *
     * @param order 实例对象
     * @return 实例对象
     */
    @Override
    public Order update(Order order) {
        this.orderDao.update(order);
        return this.queryById(order.getId());
    }



    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {

        return this.orderDao.deleteById(id) > 0;
    }
}
