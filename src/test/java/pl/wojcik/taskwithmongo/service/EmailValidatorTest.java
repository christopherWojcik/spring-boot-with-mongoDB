package pl.wojcik.taskwithmongo.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.wojcik.taskwithmongo.controller.AbstractSpringTest;

import static org.assertj.core.api.Assertions.assertThat;

class EmailValidatorTest extends AbstractSpringTest {

    @Test
    void shouldVerifyGoodEmailsAndReturnTrue() {
        boolean validation_1 = EmailValidator.validate(TestConstants.EMAIL_1);
        boolean validation_2 = EmailValidator.validate(TestConstants.EMAIL_2);
        boolean validation_3 = EmailValidator.validate(TestConstants.EMAIL_3);

        assertThat(validation_1).isTrue();
        assertThat(validation_2).isTrue();
        assertThat(validation_3).isTrue();
    }

    @Test
    void shouldValidateWrongEmailsAndReturnFalse() {
        boolean validation_1 = EmailValidator.validate("example com@gmail.com");
        boolean validation_2 = EmailValidator.validate("TestConstants.EMAIL_2");
        boolean validation_3 = EmailValidator.validate("");

        assertThat(validation_1).isFalse();
        assertThat(validation_2).isFalse();
        assertThat(validation_3).isFalse();
    }

    @Test
    void shouldThrowNullPointerExceptionFromNullEmail() {
        Assertions.assertThrows(NullPointerException.class, () -> EmailValidator.validate(null));
    }
}