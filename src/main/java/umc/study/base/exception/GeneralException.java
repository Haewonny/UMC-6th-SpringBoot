package umc.study.base.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import umc.study.base.code.BaseErrorCode;
import umc.study.base.code.ErrorReasonDto;

@Getter
@AllArgsConstructor
public class GeneralException extends RuntimeException {
    private BaseErrorCode code;

    public ErrorReasonDto getErrorReason() {
        return this.code.getReason();
    }

    public ErrorReasonDto getErrorReasonHttpStatus() {
        return this.code.getReasonHttpStatus();
    }
}
