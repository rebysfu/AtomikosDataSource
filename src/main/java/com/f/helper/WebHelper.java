package com.f.helper;

import com.f.vo.ResponseVo;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * User: bvsoo
 * Date: 2018/9/26
 * Time: 下午1:46
 */
public final class WebHelper {

    /**
     * @param request
     * @return
     */
    public static boolean ajax(HttpServletRequest request) {
        String ajaxFlag = request.getHeader("X-Requested-With");
        return ajaxFlag != null && "XMLHttpRequest".equals(ajaxFlag);
    }


    /**
     * @param response
     * @param httpStatus
     * @throws IOException
     */
    public static void writeMessage(HttpServletResponse response, HttpStatus httpStatus) throws IOException {
        writeMessage(response, ResponseVo.builder().code(httpStatus).build());
    }

    /**
     * @param response
     * @throws IOException
     */
    public static void writeMessage(HttpServletResponse response, ResponseVo responseVo) throws IOException {
        response.setStatus(HttpStatus.OK.value());
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        PrintWriter printWriter = response.getWriter();
        printWriter.write(responseVo.toJSONString());
        printWriter.flush();
        IOHelper.close(printWriter);
    }


}
