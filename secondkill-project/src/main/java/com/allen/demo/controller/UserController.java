package com.allen.demo.controller;

import com.alibaba.druid.util.StringUtils;
import com.allen.demo.controller.viewobject.UserVO;
import com.allen.demo.error.BusinessException;
import com.allen.demo.error.EmBusinessError;
import com.allen.demo.response.CommonReturnType;
import com.allen.demo.service.UserService;
import com.allen.demo.service.model.UserModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;


/**
 * @author allen
 * @date 2021/5/13 20:54
 */
@Controller("user")
@RequestMapping("/user")
@CrossOrigin(allowCredentials = "true",originPatterns = "*",allowedHeaders = "*")
public class UserController extends BaseController{

    @Autowired
    private UserService userService;

    @Autowired
    private HttpServletRequest httpServletRequest;

    /**
     * 登陆方法
     * @param telphone 手机号
     * @param password 密码
     * @return 登陆结果
     */
    @RequestMapping(value = "/login",method = {RequestMethod.POST},consumes = {"application/x-www-form-urlencoded"})
    @ResponseBody
    public CommonReturnType login(@RequestParam(name = "telphone") String telphone,
                                  @RequestParam(name = "password") String password) throws BusinessException, UnsupportedEncodingException, NoSuchAlgorithmException {
        //入参校验
        if(StringUtils.isEmpty(telphone)|| StringUtils.isEmpty(password)){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,"手机号或密码不能为空");
        }
        //登陆服务，用来校验用户登陆是否合法
        UserModel userModel = userService.validateLogin(telphone, this.encodeByMd5(password));
        //加入到用户登陆成功的session内
        this.httpServletRequest.getSession().setAttribute("IS_LOGIN",true);
        this.httpServletRequest.getSession().setAttribute("LOGIN_USER",userModel);
        return CommonReturnType.create(null);
    }
    /**
     * 用户注册接口
     * @param telphone 手机号
     * @param otpCode 通过本手机号得到的otpCode
     * @param name 姓名
     * @param gender
     * @param age 年龄
     * @return
     */
    @RequestMapping(value = "/register",method = {RequestMethod.POST},consumes = {"application/x-www-form-urlencoded"})
    @ResponseBody
    public CommonReturnType register(@RequestParam(name = "telphone") String telphone,
                                     @RequestParam(name = "otpCode") String otpCode,
                                     @RequestParam(name = "name") String name,
                                     @RequestParam(name = "gender") Integer gender,
                                     @RequestParam(name = "age") Integer age,
                                     @RequestParam(name = "password") String password) throws BusinessException, UnsupportedEncodingException, NoSuchAlgorithmException {
        //验证手机号与对应的otpCode是否符合
        String inSessionOtpCode = (String)this.httpServletRequest.getSession().getAttribute(telphone);
        if(!StringUtils.equals(otpCode,inSessionOtpCode)){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,"短信验证码错误");
        }
        //用户的注册流程
        UserModel userModel = new UserModel();
        userModel.setName(name);
        userModel.setGender(gender.byteValue());
        userModel.setAge(age);
        userModel.setTelphone(telphone);
        userModel.setRegisterMode("byphone");
        userModel.setEncrptPassword(this.encodeByMd5(password));
        userService.register(userModel);
        return CommonReturnType.create(null);
    }

    /**
     * 密码加密
     * @param str
     * @return
     * @throws UnsupportedEncodingException
     * @throws NoSuchAlgorithmException
     */
    public String encodeByMd5(String str) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        //确定计算方法
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        BASE64Encoder base64en = new BASE64Encoder();
        //加密字符串
        String newstr = base64en.encode(md5.digest(str.getBytes("utf-8")));
        return newstr;
    }
    /**
     * 用户获取otp短信接口
     * @param telphone
     * @return
     */
    @RequestMapping(value = "/getotp" ,method = {RequestMethod.POST},consumes = {"application/x-www-form-urlencoded"})
    @ResponseBody
    public CommonReturnType getOtp(@RequestParam(name = "telphone") String telphone){
        //需要按照一定的规则生产OTP验证码
        int randomInt = new Random().nextInt(99999);
        randomInt += 10000;
        String otpCode = String.valueOf(randomInt);
        //将OTP验证码同对应用户手机号关联,使用httpsession的方式绑定它的手机号与OTPCode
        //这里使用Rides其实更好，因为Rides本来就是以键值对的方式进行存储，不断调用方法，会不断将otpCode进行覆盖
        httpServletRequest.getSession().setAttribute(telphone,otpCode);
        //将OTP验证码通过短信通道发送给用户,这里直接将信息打印到控制台上
        System.out.println("telphone = " +telphone + " & otpCode = " + otpCode);
        return CommonReturnType.create(null);
    }

    @RequestMapping("/get")
    @ResponseBody
    public CommonReturnType getUser(@RequestParam(name = "id") Integer id) throws BusinessException {
        //调用service服务获取对应id的用户对象并返回给前端
        UserModel userModel = userService.getUserById(id);
        //若用户信息不存在
        if(null == userModel){
            throw new BusinessException(EmBusinessError.USER_NOT_EXIST);
        }
        //将核心领域实体数据转化为可提供给前端的vo数据
        UserVO userVO = convertFromModel(userModel);
        //返回通用对象
        return CommonReturnType.create(userVO);

    }

    /**
     * 将核心数据对象转化为可传给客户端查看使用的方法
     * @param userModel
     * @return
     */
    private UserVO convertFromModel(UserModel userModel){
        //判断userModel是否存在
        if(null == userModel){
            return  null;
        }
        //还是通过BeanUtils把model对象中的属性拷贝到VO对象中
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(userModel,userVO);
        return userVO;
    }
}
