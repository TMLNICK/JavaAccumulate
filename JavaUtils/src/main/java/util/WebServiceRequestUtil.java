package util;

import org.springframework.util.ObjectUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * @author mltang
 * @version 2022/11/1 21:58
 * @since JDK8
 */
public class WebServiceRequestUtil {
    public Map<String, Object> webServiceRequestUtil(String requesturl, String soapXML) throws IOException {

        HttpURLConnection connection = null;
        OutputStream os = null;
        Map<String, Object> reslut = new HashMap<>();

        try {
            //1：创建服务地址
            URL url = new URL(requesturl);
            //2：打开到服务地址的一个连接
            connection = (HttpURLConnection) url.openConnection();
            //3：设置连接参数
            //3.1设置发送方式：POST必须大写
            connection.setRequestMethod("POST");
            //3.2设置数据格式：Content-type
            connection.setRequestProperty("content-type", "text/xml;charset=utf-8");
            //3.3设置输入输出，新创建的connection默认是没有读写权限的，
            connection.setDoInput(true);
            connection.setDoOutput(true);

            //4：组织SOAP协议数据，发送给服务端
            os = connection.getOutputStream();
            os.write(soapXML.getBytes());
        } catch (IOException e) {
//            throw new myException("链接webservice出错！url为：" + requesturl + ";exception:" + e);
        }

        StringBuilder sb = new StringBuilder();
        InputStream is = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        try {
            String temp = null;
            //5：接收服务端的响应
            int responseCode = connection.getResponseCode();

            if (200 == responseCode) {//表示服务端响应成功
                is = connection.getInputStream();
                isr = new InputStreamReader(is);
                br = new BufferedReader(isr);

                while ((temp = br.readLine()) != null) {
                    sb.append(temp);
                }

                //这是我自己封装了一个map的返回格式，封装了headr和body
//                reslut = ReturnUtil.returnjSON(BackStatus.BACK_STATUS_OK, "success", sb.toString());
            } else {
                is = connection.getInputStream();
                isr = new InputStreamReader(is);
                br = new BufferedReader(isr);

                while ((temp = br.readLine()) != null) {
                    sb.append(temp);
                }
//                reslut = ReturnUtil.returnjSON(BackStatus.BACK_STATUS_EXCEPTION + "", "请求出错，http响应为："+responseCode, sb.toString());

            }
        } catch (IOException e) {
//            throw new myException("返回结果解析出错" + e);
        } finally {
//            if(!ObjectUtils.isEmpty(br)){
//                br.close();
//            }
//            if(!Objects.isNull(isr)){
//                isr.close();
//            }
//            if(!Objects.isNull(is)){
//                is.close();
//            }
//            if(!Objects.isNull(os)){
//                os.close();
//            }
        }


        return reslut;
    }

    public static String getXML(String phoneNum){
        String soapXML = "<?xml version=\"1.0\" encoding=\"utf-8\"?>"
                +"<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">"
                +"<soap:Body>"
                +"<getMobileCodeInfo xmlns=\"http://WebXml.com.cn/\">"
                +"<mobileCode>"+phoneNum+"</mobileCode>"
                +"<userID></userID>"
                +"</getMobileCodeInfo>"
                +" </soap:Body>"
                +"</soap:Envelope>";
        return soapXML;
    }
}


