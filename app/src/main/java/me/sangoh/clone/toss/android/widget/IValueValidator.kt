package me.sangoh.clone.toss.android.widget

/**
 * 값을 올바른지 검사하는 인터페이스
 */
interface IValueValidator {

    /**
     * 값이 유효한지 체크하는 function
     * @param 검사할 값
     * @return 유효한 값이면 true, 아님 false
     */
    fun validation(value: String): Boolean

}