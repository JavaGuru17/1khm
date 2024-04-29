package uz.pdp.onekhm.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class URL {
    public static final String BASE_URL = "http://localhost:8080";
    public static final String HEAD_URL = "/api/v1";
    public static final String AUTH_URL = "/auth";
    public static final String LOGIN_URL = "/login";
    public static final String REGISTER_URL = "/register";
    public static final String CATEGORY_URL = "/category";
    public static final String INFO_URL = "/info";
    public static final String POST_URL = "/post";
    public static final String PERMISSION_URL = "/permission";
    public static final String ROLE_URL = "/role";
    public static final String CODE_URL = "/code/{code}";
    public static final String USER_URL = "/user";
    public static final String GET_ALL_URL = "/getAll";
    public static final String SAVE_URL = "/save";
    public static final String DELETE_URL = "/delete";
    public static final String GET_URL = "/get";
    public static final String ID = "/{id}";
    public static final String EMAIL_URL = "/email/{email}";
    public static final String PHONE_NUMBER_URL = "/phoneNumber/{phoneNumber}";
    public static final String NAME = "/{name}";
    public static final String UPDATE_URL = "/update";
    public static final String IMG_URL = "/img";
    public static final String CHANGE_ROLE_URL = "/changeRole/{roleId}/{userId}";
    public static final String ADD_PERMISSION_URL = "/addPermission/{roleId}/{permissionId}";
    public static final String REMOVE_PERMISSION_URL = "/removePermission/{roleId}/{permissionId}";
}
