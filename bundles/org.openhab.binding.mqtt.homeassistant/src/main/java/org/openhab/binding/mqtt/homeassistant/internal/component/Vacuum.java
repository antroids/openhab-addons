/**
 * Copyright (c) 2010-2021 Contributors to the openHAB project
 *
 * See the NOTICE file(s) distributed with this work for additional
 * information.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 *
 * SPDX-License-Identifier: EPL-2.0
 */
package org.openhab.binding.mqtt.homeassistant.internal.component;

import com.google.gson.annotations.SerializedName;
import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;
import org.openhab.binding.mqtt.homeassistant.internal.config.dto.AbstractChannelConfiguration;

import java.util.List;

/**
 * A MQTT vacuum component, following the https://www.home-assistant.io/integrations/vacuum.mqtt/ specification.
 *
 * @author Anton Kharuzhy - Initial contribution
 */
@NonNullByDefault
public class Vacuum extends AbstractComponent<Vacuum.ChannelConfiguration> {
    public static final String SCHEMA_LEGACY = "legacy";
    public static final String SCHEMA_STATE = "state";

    public static final String COMMAND_TURN_ON = "turn_on"; // Begin cleaning
    public static final String COMMAND_TURN_OFF = "turn_off"; // Turn the Vacuum off
    public static final String COMMAND_RETURN_HOME = "return_home"; // Return to base/dock
    public static final String COMMAND_RETURN_TO_BASE = "return_to_base"; // Return to base/dock
    public static final String COMMAND_START = "start";
    public static final String COMMAND_STOP = "stop"; // Stop the Vacuum
    public static final String COMMAND_CLEAN_SPOT = "clean_spot"; // Initialize a spot cleaning cycle
    public static final String COMMAND_LOCATE = "locate"; // Locate the vacuum (typically by playing a song)
    public static final String COMMAND_START_PAUSE = "start_pause"; // Toggle the vacuum between cleaning and stopping
    public static final String COMMAND_PAUSE = "pause"; // Pause the vacuum
    public static final String COMMAND_BATTERY = "battery";
    public static final String COMMAND_STATUS = "status";
    public static final String COMMAND_FAN_SPEED = "fan_speed";
    public static final String COMMAND_SEND_COMMAND = "send_command";

    public static final List<String> LEGACY_BASIC_COMMANDS = List.of(COMMAND_TURN_ON, COMMAND_TURN_OFF, COMMAND_STOP,
            COMMAND_RETURN_TO_BASE, COMMAND_CLEAN_SPOT, COMMAND_LOCATE, COMMAND_START_PAUSE);
    public static final List<String> LEGACY_DEFAULT_FEATURES = List.of(COMMAND_TURN_ON, COMMAND_TURN_OFF, COMMAND_STOP,
            COMMAND_RETURN_HOME, COMMAND_BATTERY, COMMAND_STATUS, COMMAND_CLEAN_SPOT);
    public static final List<String> LEGACY_SUPPORTED_FEATURES = List.of(COMMAND_TURN_ON, COMMAND_TURN_OFF, COMMAND_PAUSE,
            COMMAND_STOP, COMMAND_RETURN_HOME, COMMAND_BATTERY, COMMAND_STATUS, COMMAND_LOCATE, COMMAND_CLEAN_SPOT,
            COMMAND_FAN_SPEED, COMMAND_SEND_COMMAND);

    public static final List<String> STATE_BASIC_COMMANDS = List.of(COMMAND_START, COMMAND_PAUSE, COMMAND_RETURN_TO_BASE,
            COMMAND_STOP, COMMAND_CLEAN_SPOT, COMMAND_LOCATE);
    public static final List<String> STATE_DEFAULT_FEATURES = List.of(COMMAND_START, COMMAND_STOP, COMMAND_RETURN_HOME,
            COMMAND_STATUS, COMMAND_BATTERY, COMMAND_CLEAN_SPOT);
    public static final List<String> STATE_SUPPORTED_FEATURES = List.of(COMMAND_START, COMMAND_STOP, COMMAND_PAUSE,
            COMMAND_RETURN_HOME, COMMAND_BATTERY, COMMAND_STATUS, COMMAND_LOCATE, COMMAND_CLEAN_SPOT, COMMAND_FAN_SPEED,
            COMMAND_SEND_COMMAND);

    /**
     * Configuration class for MQTT component
     */
    static class ChannelConfiguration extends AbstractChannelConfiguration {
        ChannelConfiguration() {
            super("MQTT Vacuum");
        }

        // Legacy MQTT vacuum configuration section.

        @SerializedName("battery_level_template")
        protected @Nullable String batteryLevelTemplate;
        @SerializedName("battery_level_topic")
        protected @Nullable String batteryLevelTopic;

        @SerializedName("charging_template")
        protected @Nullable String chargingTemplate;
        @SerializedName("charging_topic")
        protected @Nullable String chargingTopic;

        @SerializedName("cleaning_template")
        protected @Nullable String cleaningTemplate;
        @SerializedName("cleaning_topic")
        protected @Nullable String cleaningTopic;

        @SerializedName("command_topic")
        protected @Nullable String commandTopic;

        @SerializedName("docked_template")
        protected @Nullable String dockedTemplate;
        @SerializedName("docked_topic")
        protected @Nullable String dockedTopic;

        @SerializedName("enabled_by_default")
        protected @Nullable Boolean enabledByDefault = true;

        @SerializedName("error_template")
        protected @Nullable String errorTemplate;
        @SerializedName("error_topic")
        protected @Nullable String errorTopic;

        @SerializedName("fan_speed_list")
        protected @Nullable List<String> fanSpeedList;
        @SerializedName("fan_speed_template")
        protected @Nullable String fanSpeedTemplate;
        @SerializedName("fan_speed_topic")
        protected @Nullable String fanSpeedTopic;

        @SerializedName("payload_clean_spot")
        protected @Nullable String payloadCleanSpot = "clean_spot";
        @SerializedName("payload_locate")
        protected @Nullable String payloadLocate = "locate";
        @SerializedName("payload_return_to_base")
        protected @Nullable String payloadReturnToBase = "return_to_base";
        @SerializedName("payload_start_pause")
        protected @Nullable String payloadStartPause = "start_pause"; // Legacy only
        @SerializedName("payload_stop")
        protected @Nullable String payloadStop = "payload_stop";
        @SerializedName("payload_turn_off")
        protected @Nullable String payloadTurnOff = "turn_off";
        @SerializedName("payload_turn_on")
        protected @Nullable String payloadTurnOn = "turn_on";

        @SerializedName("schema")
        protected String schema = SCHEMA_LEGACY;

        // Features as payload
        @SerializedName("send_command_topic")
        protected @Nullable String sendCommandTopic;

        @SerializedName("set_fan_speed_topic")
        protected @Nullable String setFanSpeedTopic;

        @SerializedName("supported_features")
        protected @Nullable List<String> supportedFeatures = LEGACY_DEFAULT_FEATURES;


        // State MQTT vacuum configuration section.

        // Start/Pause replaced by 2 payloads
        @SerializedName("payload_pause")
        protected @Nullable String payloadPause = "pause";
        @SerializedName("payload_start")
        protected @Nullable String payloadStart = "start";



    }

    /**
     * Creates component based on generic configuration and component configuration type.
     *
     * @param componentConfiguration generic componentConfiguration with not parsed JSON config
     * @param clazz                  target configuration type
     */
    public Vacuum(ComponentFactory.ComponentConfiguration componentConfiguration, Class<ChannelConfiguration> clazz) {
        super(componentConfiguration, clazz);
    }
}
