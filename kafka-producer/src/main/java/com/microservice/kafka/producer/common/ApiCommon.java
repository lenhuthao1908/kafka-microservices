package com.microservice.kafka.producer.common;

/**
 * ApiCommon
 *
 * @author haoln
 * @version 01-00
 * @since 4/24/2025
 */
public class ApiCommon {

    /*API Default*/
    public static final String API_V1_URI = "/api/v1";
    public static final String CREATE_URI = "/create";
    public static final String LIST_URI = "/list";
    public static final String UPDATE_URI = "/update";
    public static final String DELETE_URI = "/delete";
    public static final String DETAIL_URI = "/detail";
    public static final String NONE_URI = "/";

    /*API Path Variable*/
    public static final String ID_PATH_VARIABLE = "{id}";
    public static final String TOPIC_PATH_VARIABLE = "{topic}";

    /*API Custom*/
    public static final String ACCOUNT_URI = "/account";
    public static final String MESSAGE_URI = "/message";
}
