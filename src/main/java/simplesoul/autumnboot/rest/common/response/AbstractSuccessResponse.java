package simplesoul.autumnboot.rest.common.response;

/**
 * @author AC
 */
sealed abstract class AbstractSuccessResponse extends AbstractResponseEntity permits
        ResponseEntity,
        ResponseEntity.Done {
}
