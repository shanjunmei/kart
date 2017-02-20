package com.ffzx.kart.api;

import com.pingplusplus.Pingpp;
import com.pingplusplus.model.Customs;

import java.math.BigInteger;
import java.security.SecureRandom;

/**
 * Created by Afon on 16/4/26.
 */
public class PingPPConfig {

    /**
     * Pingpp 管理平台对应的 API Key，api_key 获取方式：登录 [Dashboard](https://dashboard.pingxx.com)->点击管理平台右上角公司名称->开发信息-> Secret Key
     */
    private final static String apiKey = "sk_live_ivTijLn1iXD0yLCev548mP0K";

    /**
     * Pingpp 管理平台对应的应用 ID，app_id 获取方式：登录 [Dashboard](https://dashboard.pingxx.com)->点击你创建的应用->应用首页->应用 ID(App ID)
     */
    private final static String appId = "app_1Gqj58ynP0mHeX1q";

    /**
   * 设置请求签名密钥，密钥对需要你自己用 openssl 工具生成，如何生成可以参考帮助中心：https://help.pingxx.com/article/123161；
   * 生成密钥后，需要在代码中设置请求签名的私钥(rsa_private_key.pem)；
   * 然后登录 [Dashboard](https://dashboard.pingxx.com)->点击右上角公司名称->开发信息->商户公钥（用于商户身份验证）
   * 将你的公钥复制粘贴进去并且保存->先启用 Test 模式进行测试->测试通过后启用 Live 模式
   */

    // 你生成的私钥路径
    private final static String privateKeyFilePath = "res/your_rsa_private_key_pkcs8.pem";

    public static void main(String[] args) throws Exception {

        // 设置 API Key
        Pingpp.apiKey = apiKey;

        // 设置私钥路径，用于请求签名
        Pingpp.privateKeyPath = privateKeyFilePath;

        /**
         * 或者直接设置私钥内容
         Pingpp.privateKey = "-----BEGIN RSA PRIVATE KEY-----\n" +
         "... 私钥内容字符串 ...\n" +
         "-----END RSA PRIVATE KEY-----\n";
         */


        // Charge 示例
       /* ChargeExample.runDemos(appId);

        // Refund 示例
        RefundExample.runDemos();

        // RedEnvelope 示例
        RedEnvelopeExample.runDemos(appId);

        // Transfer 示例
        TransferExample.runDemos(appId);

        // Event 示例
        EventExample.runDemos();

        // Webhooks 验证示例
        WebhooksVerifyExample.runDemos();

        // 微信公众号 openid 相关示例
        WxPubOAuthExample.runDemos(appId);

        // 身份证银行卡信息认证接口
        IdentificationExample.runDemos(appId);

        // 批量付款示例
        BatchTransferExample.runDemos(appId);

        // 报关
        CustomsExample.runDemos(appId);*/
    }

    private static SecureRandom random = new SecureRandom();

    public static String randomString(int length) {
        String str = new BigInteger(130, random).toString(32);
        return str.substring(0, length);
    }

    public static int currentTimeSeconds() {
        return (int)(System.currentTimeMillis() / 1000);
    }
    static {
        Pingpp.apiKey = apiKey;
        Pingpp.privateKey="-----BEGIN PUBLIC KEY-----\n" +
                "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAuxpMIqv3WjOanOEviLgX\n" +
                "wUKuVExniHAjdwUGaDiv6f6SfPf+r8GEV4jfcxdZ0ttcjCWHXSeNiQ5RnUJ7gs5E\n" +
                "hdcjiKHP9fBGzCBCmRivSqoC20c6h9LvKzefMnaiThVJeQb73aOSCysqKYWr8ldq\n" +
                "o4ZvwP+urRvPl3DiXlahR2bTOuHUGA1kRQXZcgUtY3Sxi3SwftqzIwK2c240Hu/f\n" +
                "A0b0+Y65LYMPTxTWsuSrTS4TPX8RIbl3pHfeRFnPFvZ3q2SPtCwf5dPlivN7vgFG\n" +
                "g1+/7lNUk2fqILzzGtXUhvqi1oii5WqMlSIXBVjmzgKrwXra+ruc6QN+KIiZojNS\n" +
                "bQIDAQAB\n" +
                "-----END PUBLIC KEY-----\n";
    }
}
