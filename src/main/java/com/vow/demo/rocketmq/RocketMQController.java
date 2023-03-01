package com.vow.demo.rocketmq;

import com.vow.demo.domain.main.user.entity.User;
import com.vow.demo.utils.Result;
import org.apache.rocketmq.client.producer.SendResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rocketmq")
public class RocketMQController {

    @Autowired
    private MQProducerService mqProducerService;

    @GetMapping("/send")
    public void send() {
        User user = new User();
        user.setUserId(11L);
        user.setUsername("vow");
        mqProducerService.send(user);
    }

    @GetMapping("/sendTag")
    public Result<SendResult> sendTag() {
        SendResult sendResult = mqProducerService.sendTagMsg("带有tag的字符消息");
        return Result.success(sendResult);
    }

}

