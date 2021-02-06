package com.goodskill.mongo;

import com.goodskill.mongo.api.SuccessKilledMongoService;
import com.goodskill.mongo.entity.SuccessKilledDto;
import com.goodskill.mongo.vo.SeckillMockSaveVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigInteger;
import java.util.Date;
import java.util.function.Consumer;

/**
 *
 * @author heng
 */
@SpringBootApplication
public class MongoReactiveApplication {

    @Autowired
    private SuccessKilledMongoService successKilledMongoService;

    public static void main(String[] args) {
        SpringApplication.run(MongoReactiveApplication.class, args);
    }

    /**
     * 监听seckillMongoSave队列消息
     *
     * @return
     */
    @Bean
    public Consumer<SeckillMockSaveVo> seckillMongoSave() {
        return person -> {
            SuccessKilledDto successKilledDto = new SuccessKilledDto();
            successKilledDto.setSeckillId(BigInteger.valueOf(person.getSeckillId()));
            successKilledDto.setUserPhone(person.getUserPhone());
            successKilledDto.setCreateTime(new Date());
            successKilledMongoService.saveRecord(successKilledDto);
        };
    }

}
