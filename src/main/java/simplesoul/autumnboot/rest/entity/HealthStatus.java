package simplesoul.autumnboot.rest.entity;

import lombok.AllArgsConstructor;

import javax.validation.Valid;

/**
 * @author AC
 */
@AllArgsConstructor
public class HealthStatus {

    private String state;

    private int errors;

    private int warnings;
}
