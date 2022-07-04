package simplesoul.autumnboot.rest.common.response;

/**
 * 接口返回通用类
 *
 * @author AC
 */
public sealed class AbstractResponseEntity permits
        ResponseEntity,
        ResponseEntity.Done,
        ResponseEntity.Exceptional {
}
