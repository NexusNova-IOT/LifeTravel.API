package com.nexusnova.lifetravelapi.config;

import com.nexusnova.lifetravelapi.app.assets.api.transformation.RegisterMultipleTemperatureCommandFromRequestDtoAssembler;
import com.nexusnova.lifetravelapi.app.assets.domain.services.TemperatureCommandService;
import com.nexusnova.lifetravelapi.app.assets.resources.requests.TemperatureRequestDto;
import jakarta.transaction.Transactional;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.MessageProducer;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

@Configuration
public class MqttConfig {

    private static final String MQTT_URL = "tcp://localhost:1883";
    private static final String CLIENT_ID = "spring-boot-mqtt";
    private static final int BATCH_SIZE = 10;
    private static final Logger logger = Logger.getLogger(MqttConfig.class.getName());

    private final TemperatureCommandService temperatureCommandService;
    List<TemperatureRequestDto> batch = new ArrayList<>();

    public MqttConfig(TemperatureCommandService temperatureCommandService) {
        this.temperatureCommandService = temperatureCommandService;
    }

    @Bean
    public MessageChannel mqttInputChannel() {
        return new DirectChannel();
    }

    @Bean
    public MessageProducer inbound() {
        MqttPahoMessageDrivenChannelAdapter adapter =
                new MqttPahoMessageDrivenChannelAdapter(MQTT_URL, CLIENT_ID, "temperatures/#");
        adapter.setCompletionTimeout(5000);
        adapter.setConverter(new DefaultPahoMessageConverter());
        adapter.setQos(1);
        adapter.setOutputChannel(mqttInputChannel());
        return adapter;
    }

    @ServiceActivator(inputChannel = "mqttInputChannel")
    @Transactional
    public void handleMessage(Message<?> message) {
        String topic = Objects.requireNonNull(message.getHeaders().get("mqtt_receivedTopic")).toString();
        String payload = message.getPayload().toString();
        long departmentId = extractDepartmentId(topic);

        try {
            TemperatureRequestDto temperatureDto = new TemperatureRequestDto();
            temperatureDto.setValue(Double.parseDouble(payload));
            temperatureDto.setDepartmentId(departmentId);
            batch.add(temperatureDto);
        } catch (NumberFormatException e) {
            logger.warning("Error parsing temperature: " + e.getMessage());
        }

        if (false) {
            logger.info("Received temperature: " + payload + " for department: " + departmentId);
        }

        if (batch.size() >= BATCH_SIZE) {
            temperatureCommandService.handle(RegisterMultipleTemperatureCommandFromRequestDtoAssembler.toCommandFromDto(batch));
            batch.clear();
        }
    }

    private long extractDepartmentId(String topic) {
        String[] parts = topic.split("/");
        if (parts.length < 2 || !parts[1].startsWith("department")) {
            throw new IllegalArgumentException("Invalid MQTT topic: " + topic);
        }
        try {
            return Long.parseLong(parts[1].substring(10));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid department ID in topic: " + topic, e);
        }
    }

    @Bean
    public MqttConnectOptions mqttConnectOptions() {
        return new MqttConnectOptions();
    }
}
