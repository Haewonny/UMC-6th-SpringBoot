package umc.study.base.exception.handler;

import umc.study.base.code.BaseErrorCode;
import umc.study.base.exception.GeneralException;

public class FoodCategoryHandler extends GeneralException {
    public FoodCategoryHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
