package simplesoul.autumnboot.rest.common.response;

/**
 * @author AC
 */
public sealed class AbstractFailureResponse extends AbstractResponseEntity permits
        ResponseEntity.Exceptional,
        ResponseEntity.Failure,
        ResponseEntity.ValidateError,
        ResponseEntity.Fatal {
}
