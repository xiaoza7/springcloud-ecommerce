package com.lmq.pay.alipay;

import java.io.FileWriter;
import java.io.IOException;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {

	// ↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

	// 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
	public static String app_id = "2016092000554190";

	// 商户私钥，您的PKCS8格式RSA2私钥
	public static String merchant_private_key = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDfS56hi4wNun/U00TBqPVPWIi6v6cvCDMHX9jdCX2fKeRuypw2bCPGE45ioYZ42TXaBsD5lt/qUR56ODV2HEKD6kdsDh6P6fo5hKXqDaBvNBTpP0xIqiA+4ziQbvGl+ZU2R3KMPvfNCH/REUWYrfL7ugLeNpgJSTr39zCXcS3FWP6/ihOBohTh8kHIlzIzV9aCklHkihtkRAXXtSih2R0UYeUJEScS+kWKBBnDbFD64TGJguJg9RNfO2XocTN5oGcCPe9yRV/aUraKeWZa7LyOLD725sMhPEYHzO2Eq4RIKV//jjVGEz6BQbGvhDWCFvOOeuqI0p8E9lyMu7+2y6FNAgMBAAECggEAF3/MzPFzpso2w74MIVlXn6cQlJ6wyHlTbXY/y1tBVivBd4tpDy0oQqMyO90F2tf5G93UFzAylmU9EN2zep+UvG8+XvfJbZnRFJaKI2yVsr5HaRiZPZhx1kxxWnnignYWpuS0AGBsEa7QTL9FlMDRrn4v6RTTwT5sr0XbXkSx7W+y4/HcbXQxs+JJB3WN3xJvXRs4jTDWhr6PU1uLzYvvXbz9h4MxahOTOZdxCI21ljdUzHosdfOqVb7U6/Nu9bEjnJu/1EfeD4KDghSmLhxUfJZvguAHNMylbuCWBj/s6R9ts+ZfuK3oIzCTIn1t9lV9cZSkTROTr5lRQjW26DMk/QKBgQDx0+uQVFMut6Em0tVhME8J+Vppn9vyWWE2U1WkOJMkI5IVyzMs4FxduHHTccyOqdeNakX8tKrbBUu230QEsqB7u/J0VUr1cJBNCvK3GnfIkZMlJDQmX/QcuJRsuwO2exmZgNxJ4kUwpdwKur1quobtW/ewTrRTDcjQ/AaEihftZwKBgQDsYamPRABHOZKVfqAG9SQZ8XXCBZpjHCR4EjRE78wU3X9UJB4oBycLOP5NAAyVJa/25r71oQBh8eO1BRSTu4bUh+fETtafJKmK60XLGVDjxPYwKALSBm/62x2+tLkYQdIqqb+hQ5nDIr9tdWe3mTkaYvwpM1SSS/3RNyTp4+mXKwKBgDAhsd0EK5o8ykJj7Dxv1QtzKd+kBa7ywzqA06bf68lhmw3jZ8giz7+HmoAvQjuS6mgnIs7tVqRJJYy8W1lkBDUcb2FgKI85XD85Up3HEthEwTi/P/HzXy7oPgJ2JoMHdUzsLOw3sPXBzU4FZPX9MiXEDER3JcTyFWoi/1kZ8zE5AoGBAIRg1TJ0ZKt+pIawGTKMSqF0xfvYY3OpqQrNNxR8LpBo0wvuBi1ohAcW7eN1aZaFOpxM8RhiB/LSIjGYHMP3OzT+v18Cv9L0FzbDN5zcGp7UCQNDBQFVtZSI7YmFcDsTcX2O/tBzKUNMzGOiYmlBl+cdFBaGtpAPmp92AGgzyPdtAoGBANixuh5h9VZwqrvU4GI7npsb7soGKA4m0Rl8n6ClL/ZDtLEqve1av7Vk4GV42VTdEC4KCM4D80ofn1XDwZeR0xA0sV2cOM9ElCEJ1TiArfnFFM4VAKiPnuVLeVfI/ojoNJjg82y5vr48fbXXEhZYhUeWhWMvTuhkhcAoTMkziaJ3";
	// 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm
	// 对应APPID下的支付宝公钥。
	public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA30ueoYuMDbp/1NNEwaj1T1iIur+nLwgzB1/Y3Ql9nynkbsqcNmwjxhOOYqGGeNk12gbA+Zbf6lEeejg1dhxCg+pHbA4ej+n6OYSl6g2gbzQU6T9MSKogPuM4kG7xpfmVNkdyjD73zQh/0RFFmK3y+7oC3jaYCUk69/cwl3EtxVj+v4oTgaIU4fJByJcyM1fWgpJR5IobZEQF17UoodkdFGHlCREnEvpFigQZw2xQ+uExiYLiYPUTXztl6HEzeaBnAj3vckVf2lK2inlmWuy8jiw+9ubDITxGB8zthKuESClf/441RhM+gUGxr4Q1ghbzjnrqiNKfBPZcjLu/tsuhTQIDAQAB";

	// 服务器异步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = " http://f83d2430.ngrok.io/alibaba/callBack/notifyUrl";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url = " http://f83d2430.ngrok.io/alibaba/callBack/returnUrl";

	// 签名方式
	public static String sign_type = "RSA2";

	// 字符编码格式
	public static String charset = "utf-8";

	// 支付宝网关
	public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

	// 支付宝网关
	public static String log_path = "C:\\";

	// ↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

	/**
	 * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
	 * 
	 * @param sWord
	 *            要写入日志里的文本内容
	 */
	public static void logResult(String sWord) {
		FileWriter writer = null;
		try {
			writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis() + ".txt");
			writer.write(sWord);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
