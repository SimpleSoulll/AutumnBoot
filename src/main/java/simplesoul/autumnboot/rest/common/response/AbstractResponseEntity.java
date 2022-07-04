package simplesoul.autumnboot.rest.common.response;

/**
 * 接口返回通用类
 *
 * @author AC
 */
sealed class AbstractResponseEntity permits
        AbstractSuccessResponse,
        AbstractFailureResponse {
}
