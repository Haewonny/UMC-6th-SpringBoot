package umc.study.base.exception.handler;

import umc.study.base.code.BaseErrorCode;
import umc.study.base.exception.GeneralException;

public class TempHandler extends GeneralException {
    public TempHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
