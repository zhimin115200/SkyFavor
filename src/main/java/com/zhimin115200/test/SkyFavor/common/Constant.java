package com.zhimin115200.test.SkyFavor.common;

public class Constant {

    //ERROR 常量
    public final static String CREATE_ERROR= "创建失败";
    public final static String ACCOUNT_IS_EXIST = "账号已存在";
    public final static String ACCOUNT_NOT_EXIST = "账号不存在";
    public final static String PARAM_ERROR= "参数不能为空";
    public final static String ACCOUNT_PASS_ERROR = "账号或密码错误";
    public final static String VERIFY_ERROR = "验证码错误";
    public final static String SEND_ERROR = "发送失败";
    public final static String SEND_INTERVAL_NOT_ALLOW = "发送间隔不能小于30秒";

    //通知主题
    public final static String Mail_Subject ="【SkyFavor】通知";
    //通知模板
    public final static String Mail_Verify_Template = "【SkyFavor】尊敬的用户，您的验证码为%s，请及时验证。";
    //通知模板
    public final static String Mail_ForgetPass_Template = "【SkyFavor】尊敬的用户，您的密码为%s。";


}
